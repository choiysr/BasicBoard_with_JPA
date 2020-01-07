package com.sora.jpa_ex1;

import com.sora.jpa_ex1.domain.Board;
import com.sora.jpa_ex1.repositories.BoardRepository;

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

    @Test
    void boardRepoTest() {
      

    }

}