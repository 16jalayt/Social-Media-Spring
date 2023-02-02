package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;

    private String label;

    @CreationTimestamp
    private Timestamp firstUsed;
    
    @UpdateTimestamp
    private Timestamp lastUsed;

    @ManyToMany(mappedBy = "hashtags", cascade=CascadeType.ALL)
    private List<Tweet> tweetsWithHashtag;

    public void addTweet(Tweet tweet) {
        this.tweetsWithHashtag.add(tweet);
        tweet.getHashtags().add(this);
    }

    public void removeTweet(Tweet tweet) {
        this.tweetsWithHashtag.remove(tweet);
        tweet.getHashtags().remove(this);
    }

	public List<Tweet> getTweets() {
		// TODO Auto-generated method stub
		return null;
	}

}
