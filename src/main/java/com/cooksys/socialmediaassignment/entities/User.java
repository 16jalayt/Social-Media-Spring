package com.cooksys.socialmediaassignment.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
	private Timestamp joined = Timestamp.valueOf(LocalDateTime.now());

	@Embedded
	private Credential credential;

	@Embedded
	private Profile profile;

	private boolean deleted;

	@OneToMany(mappedBy = "tweet")
	private Set<Tweet> tweets;

	@ManyToMany
	private Set<User> followers;

	@ManyToMany(mappedBy = "followers")
	private Set<User> following;


	@ManyToMany(mappedBy = "tweetlikes")
	private Set<Tweet> userLikes;

	@ManyToMany(mappedBy = "mentions")
	private Set<Tweet> userMentions;

}
