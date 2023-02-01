package com.cooksys.socialmediaassignment;

import com.cooksys.socialmediaassignment.entities.Hashtag;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credential;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

	private final UserRepository userRepository;
	private final TweetRepository tweetRepository;
	private final HashtagRepository hashtagRepository;

	@Override
	public void run(String... args) throws Exception {

		Credential credential1 = new Credential();
		credential1.setUsername("jDoe");
		credential1.setPassword("1234");
		Profile profile1 = new Profile();
		profile1.setEmail("jdow@gmail.com");
		profile1.setFirstName("Jane");
		profile1.setLastName("Doe");
		profile1.setPhone("1231231234");
		User user1 = new User();
		user1.setCredential(credential1);
		user1.setProfile(profile1);
		user1.setDeleted(false);
		userRepository.saveAndFlush(user1);

		Credential credential2 = new Credential();
		credential2.setUsername("joDoe");
		credential2.setPassword("5555");
		Profile profile2 = new Profile();
		profile2.setEmail("jdoe@gmail.com");
		profile2.setFirstName("John");
		profile2.setLastName("Doe");
		profile2.setPhone("5555555555");
		User user2 = new User();
		user2.setCredential(credential2);
		user2.setProfile(profile2);
		user2.setDeleted(false);
		userRepository.saveAndFlush(user2);

		Hashtag hashtag = new Hashtag();
		hashtag.setLabel("#test");
		hashtagRepository.saveAndFlush(hashtag);
	}

}
