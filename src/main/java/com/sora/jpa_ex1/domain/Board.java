package com.sora.jpa_ex1.domain;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * board
 */
@Entity
@Table(name = "tbl_board")
@Builder
@Getter
@AllArgsConstructor
public class Board {
    
    private Integer bno;
    private String writer;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalTime regdate;
    @UpdateTimestamp
    private LocalTime updateddate;
    
}