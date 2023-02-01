package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "tweet")
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
    private Set<User> mentions; 
    
   
   
}
