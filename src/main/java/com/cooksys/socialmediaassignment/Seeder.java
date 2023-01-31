package com.cooksys.socialmediaassignment;

import java.time.Instant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.socialmediaassignment.entities.Credential;
import com.cooksys.socialmediaassignment.entities.Profile;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

	private final UserRepository userRepository;

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
		user1.setJoined(Instant.now().getEpochSecond());
		userRepository.saveAndFlush(user1);
		
		Credential credential2 = new Credential();
		credential2.setUsername("joDoe");
		credential2.setPassword("5555");
		Profile profile2 = new Profile();
		profile2.setEmail("jdoe@gmail.com");
		profile2.setFirstName("John");
		profile2.setLastName("Doe");
		profile2.setPhone("5555555555");
		User user2= new User();
		user2.setCredential(credential2);
		user2.setProfile(profile2);
		user2.setDeleted(false);
		user2.setJoined(Instant.now().getEpochSecond());
		user2.setFollower(user1);
		userRepository.saveAndFlush(user2);

	}

}
