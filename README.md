### BasicBoard_with_JPA : JPA를 이용한 간단한 게시판 구현

------------------------------------------------------------------------------------------------
   
## devLog --- 

### 2020.01.07
[Board,Reply 관계설정]
: Board와 Reply는 OneToMany관계로 Reply에서 Board를 참조할 필요가 없으니(Reply로 Board정보를 가져올 일이 없으니) 단방향으로 맺어주었다.
```
    @Test
    @Transactional
    void getOneBoardWithReplyTest() {
        Board board = brepo.boardWithReply(1);
        System.out.println(board.getReplies());
    }
```





