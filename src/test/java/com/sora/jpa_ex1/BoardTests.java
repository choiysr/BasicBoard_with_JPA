package com.sora.jpa_ex1;

import javax.transaction.Transactional;

import com.sora.jpa_ex1.domain.Board;
import com.sora.jpa_ex1.domain.Reply;
import com.sora.jpa_ex1.repositories.BoardRepository;
import com.sora.jpa_ex1.repositories.ReplyRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;

/**
 * BoardTests
 */
@SpringBootTest
public class BoardTests {

    @Setter(onMethod_ = { @Autowired })
    BoardRepository brepo;
    
    @Setter(onMethod_ = { @Autowired })
    ReplyRepository rrepo;

    @Test
    void boardRepoTest() {

        brepo.save( Board.builder()
        .writer("user1")
        .title("this is title")
        .content("this is content")
        .build());

    }

    @Test
    void replyTest() {
         for(int i=0;i<10;i++) {
            rrepo.save(Reply.builder().bno(1)
            .writer("user1")
            .content("reply..")
            .build());
        } 
    }

    @Test
    @Transactional
    void getOneBoardWithReplyTest() {
        System.out.println("boardWithReply사용");
        Board board = brepo.boardWithReply(1);
        System.out.println(board.getReplies());
    }

    @Test
    @Transactional
    void getOneBoardWithReplyTest2() {
        System.out.println("findById사용");
        Board board = brepo.findById(1).orElse(null);
        System.out.println("List가져오기");
        System.out.println(board.getReplies()); 
    }

}