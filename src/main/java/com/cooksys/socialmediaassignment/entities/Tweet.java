package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"reposts", "repostOf", "hashtags", "author", "usersMentioned", "likes", "replies", "inReplyTo"})

public class Tweet {

	@Id
    @GeneratedValue
    private Long id;
    
    private Long author;
    
    @CreationTimestamp
    private Timestamp posted; 
    
    private boolean deleted = false; 
    
    private String content;
    
    @ManyToOne
    private Tweet inReplyTo;
    
  
    @OneToMany(mappedBy = "inReplyTo")
    private List<Tweet> replies; 
    
    @ManyToOne
    private Tweet repostOf;
    
    @OneToMany(mappedBy = "repostOf")
    private List<Tweet> reposts; 
    
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
    	name = "tweet_hashtag",
    	joinColumns = @JoinColumn(name = "tweet_id"),
    	inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    	)
    private List<Hashtag> hashtags; 
    
    @ManyToMany(mappedBy = "likedTweets")
    private List<User> likedByUsers; 
    
    @ManyToMany
    @JoinTable(
    	name = "user_mentions",
    	joinColumns = @JoinColumn(name = "tweet_id"),
    	inverseJoinColumns = @JoinColumn(name = "user_id")
    	)

    private List<User> mentions; 
   
}