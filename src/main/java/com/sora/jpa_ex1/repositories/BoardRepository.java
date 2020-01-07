package com.sora.jpa_ex1.repositories;

import com.sora.jpa_ex1.domain.Board;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/** 
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board,Integer>{
    @EntityGraph(attributePaths={"replies"})
    @Query("select b from Board b where b.bno = :bno")
    public Board boardWithReply(Integer bno);
}