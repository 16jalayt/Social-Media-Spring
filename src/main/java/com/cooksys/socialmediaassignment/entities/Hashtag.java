package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "hashtag")
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;

    private String label;

    @CreationTimestamp
    private Timestamp firstUsed;
    
    @UpdateTimestamp
    private Timestamp lastUsed;

    @ManyToMany
    //@ManyToMany(mappedBy = "hashtags", cascade=CascadeType.ALL)
    private List<Tweet> tweetsWithHashtag;

}
