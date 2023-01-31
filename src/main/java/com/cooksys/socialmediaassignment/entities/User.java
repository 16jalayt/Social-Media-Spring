package com.cooksys.socialmediaassignment.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
	private Long joined;

	@Embedded
	private Credential credential;

	@Embedded
	private Profile profile;

	private boolean deleted;

//	@OneToMany(mappedBy = "tweet")
//	private List<Tweet> tweets;

	@ManyToMany
	private Set<User> followerUsers;

	@ManyToMany
	private Set<User> followingUsers;

//	@ManyToOne
//	private User follower;
//	
//	@OneToMany(mappedBy="follower")
//	private Set<User> followers;
//
//	@OneToMany(mappedBy = "followingUser")
//	private Set<User> following;
//	
//	@ManyToOne
//	private User followingUser;

//
//	@ManyToMany
//	private List<Tweet> userLikes;
//	
//	@ManyToMany
//	private List<Tweet> userMantions;

}
