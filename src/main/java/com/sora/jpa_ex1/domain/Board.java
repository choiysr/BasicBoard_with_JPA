package com.sora.jpa_ex1.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * board
 */
@Entity
@Table(name = "tbl_board")
@Builder
@Getter
@AllArgsConstructor
@ToString
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    private String writer;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalTime regdate;
    @UpdateTimestamp
    private LocalTime updateddate;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bno")
    private List<Reply> replies;

    public Board() {
      
    } 
    
}