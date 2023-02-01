package com.cooksys.socialmediaassignment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;

    private String label;

    private Timestamp firstUsed;
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags")
    private List<Tweet> tweetsWithHashtag;

}
