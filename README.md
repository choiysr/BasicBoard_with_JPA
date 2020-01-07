## BasicBoard_with_JPA : JPA를 이용한 간단한 게시판 구현 Devlog

------------------------------------------------------------------------------------------------

### 2020.01.07 : Board,Reply 관계설정/Fetch전략 이해 

in Board.java
```java
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private List<Reply> replies;
```
: Board와 Reply는 OneToMany관계로 Reply에서 Board를 참조할 필요가 없으니(Reply로 Board정보를 가져올 일이 없으니) 단방향으로 맺어주었다.(Reply객체에 ManyToOne이 필요 x)<br>
<br>
: @OneToMany의 기본 Fetch전략이 지연로딩이기 때문에 FetchType을 별도로 지정하지 않아도 지연로딩으로 실행한다. <br>
아래 테스트코드에서 findById를 이용해 board를 가져올때는 Lazy로딩으로 실행하여 reply정보를 가져오지 않는다. 바로 아래 getReplies로 실제 replies를 필요로 할때 select쿼리를 날리게 된다. <br>
```java
  @Test
    @Transactional
    void getOneBoardWithReplyTest2() {
        System.out.println("findById사용");
        Board board = brepo.findById(1).orElse(null);
        System.out.println("List가져오기");
        System.out.println(board.getReplies());
    }
```
: 예를 들어 게시판 리스트에서는 댓글이 일일이 뭐라 달렸는지 알 필요가 없으니 findById(후에는 findAll)로 가져와도 되지만, 실제로 게시글을 클릭해서 조회할때는 댓글리스트도 같이 필요하고, 그럴때 select쿼리가 두번 실행되는 것은 비효율적이다.<br>
이때 fetch전략을 EAGER로 설정해준다면 reply테이블을 join하여 아래와 같이 select쿼리문 한번에 해결 할 수 있다. <br>
```
   select
        board0_.bno as bno1_0_0_,
        board0_.content as content2_0_0_,
        board0_.regdate as regdate3_0_0_,
        board0_.title as title4_0_0_,
        board0_.updateddate as updatedd5_0_0_,
        board0_.writer as writer6_0_0_,
        replies1_.bno as bno2_1_1_,
        replies1_.rno as rno1_1_1_,
        replies1_.rno as rno1_1_2_,
        replies1_.bno as bno2_1_2_,
        replies1_.content as content3_1_2_,
        replies1_.writer as writer4_1_2_ 
    from
        tbl_board board0_ 
    left outer join
        tbl_reply replies1_ 
            on board0_.bno=replies1_.bno 
    where
        board0_.bno=?
```    
<br>
그렇지만 게시판 리스트를 조회할때 일일이 댓글리스트까지 join하여 가져오는 것 또한 비효율적이며(추후에 더 많은 관계매핑이 들어갔을때를 생각하면 더 비효율적) 필요에 따라 지연로딩과 즉시로딩을 적당히 사용할 수 있게끔 BoardRepository에 QueryDsl을 이용하여 boardWithReply메서드를 만들어 주었다. <br>

```java
public interface BoardRepository extends JpaRepository<Board,Integer>{
    @EntityGraph(attributePaths={"replies"})
    @Query("select b from Board b where b.bno = :bno")
    public Board boardWithReply(Integer bno);
}
```
<br>
: @EntityGraph 어노테이션은 attributePaths에 기재된 attribute를(쿼리수행 시 바로 가져올 필드) EAGER로딩 한다. left join(outer join)을 이용해서 쿼리를 작성해 줄 수 있지만(~~왜인지 안된다;;~~) 이 방법이 좀 더 직관적이고 편한 듯 하다.<br>
이렇게 설정해주면 게시판 리스트 띄울 때는 그냥 findAll을 써서 lazy로딩으로 board만 불러오고, 게시글을 클릭해서 댓글까지 같이 봐야하는 경우에는 boardWithReply메서드를 이용하여 select문 한번에 조회할 수 있게끔 할 수 있다. 
<


