package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;
    
    private Integer author; 
    
    private Timestamp posted; 
    
    private boolean deleted;
    
   // private Varchar content;
    
    private Integer inReplyTo;
    
    private Integer repostOf;
}
