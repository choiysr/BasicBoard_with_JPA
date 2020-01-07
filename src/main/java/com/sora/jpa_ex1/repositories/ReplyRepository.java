package com.sora.jpa_ex1.repositories;

import com.sora.jpa_ex1.domain.Reply;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ReplyRepository
 */
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    
}