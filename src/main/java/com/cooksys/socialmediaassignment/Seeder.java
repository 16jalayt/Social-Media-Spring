package com.cooksys.socialmediaassignment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.socialmediaassignment.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

	private final UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

//		Credentials credential1 = new Credentials();
//		credential1.setUsername("jDoe");
//		credential1.setPassword("1234");
//		Profile profile1 = new Profile();
//		profile1.setEmail("jdow@gmail.com");
//		profile1.setFirstName("Jane");
//		profile1.setLastName("Doe");
//		profile1.setPhone("1231231234");
//		User user1 = new User();
//		user1.setCredential(credential1);
//		user1.setProfile(profile1);
//		user1.setDeleted(false);
//		user1.setJoined(Timestamp.valueOf(LocalDateTime.now()));
//		userRepository.saveAndFlush(user1);
//
//		Credentials credential2 = new Credentials();
//		credential2.setUsername("joDoe");
//		credential2.setPassword("5555");
//		Profile profile2 = new Profile();
//		profile2.setEmail("jdoe@gmail.com");
//		profile2.setFirstName("John");
//		profile2.setLastName("Doe");
//		profile2.setPhone("5555555555");
//		User user2 = new User();
//		user2.setCredential(credential2);
//		user2.setProfile(profile2);
//		user2.setDeleted(false);
//		user2.setJoined(Timestamp.valueOf(LocalDateTime.now()));
//		userRepository.saveAndFlush(user2);

	}

}
