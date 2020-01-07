package com.sora.jpa_ex1.repositories;

import com.sora.jpa_ex1.domain.Board;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BoardRepository
 */
public interface BoardRepository extends JpaRepository<Board,Integer>{

    
}