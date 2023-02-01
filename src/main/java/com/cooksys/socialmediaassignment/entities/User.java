package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@CreationTimestamp
	private Timestamp joined;

	@Embedded
	private Credentials credentials;

	@Embedded
	private Profile profile;

	private boolean deleted = false;

	@OneToMany(mappedBy = "author")
	private Set<Tweet> tweets;

	@ManyToMany
	@JoinTable(name = "followers_following")
	private Set<User> followers;

	@ManyToMany(mappedBy = "followers")
	private Set<User> following;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "tweet_liked_mapping", joinColumns = { @JoinColumn(name = "tweet_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") })
	private Set<Tweet> likedTweets;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Set<Tweet> mentions;

////	@ManyToMany(mappedBy = "likes")
//	@ManyToMany
//	@JoinColumn(name = "tweeter_id")
//	private Set<Tweet> likedTweets;
//	
////	@ManyToMany(mappedBy = "usersMentioned")
//	@ManyToMany
//	@JoinColumn(name = "tweeter_id")
//	private Set<Tweet> mentions;

}
