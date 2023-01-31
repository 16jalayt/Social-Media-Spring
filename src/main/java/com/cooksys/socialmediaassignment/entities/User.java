package com.cooksys.socialmediaassignment.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
	private long joined;

	@Embedded
	private Credential credential;

	@Embedded
	private Profile profile;

	private boolean deleted;

//	@OneToMany(mappedBy = "tweet")
//	private List<Tweet> tweets;

	@ManyToOne
	private User follower;

	@OneToMany(mappedBy = "follower")
	private List<User> following;

//
//	@OneToMany(mappedBy = "tweet")
//	private List<Tweet> userLikes;
//	
//	@OneToMany(mappedBy = "tweet")
//	private List<Tweet> userMantions;

}
