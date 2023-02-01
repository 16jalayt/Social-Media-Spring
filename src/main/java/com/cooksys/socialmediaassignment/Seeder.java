package com.cooksys.socialmediaassignment;


import com.cooksys.socialmediaassignment.entities.Hashtag;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.repositories.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {


	private final UserRepository userRepository;
	private final TweetRepository tweetRepository;
	private final HashtagRepository hashtagRepository;


	@Override
	public void run(String... args) throws Exception {

		Credentials credential1 = new Credentials();
		credential1.setUsername("jDoe");
		credential1.setPassword("1234");
		Profile profile1 = new Profile();
		profile1.setEmail("jdow@gmail.com");
		profile1.setFirstName("Jane");
		profile1.setLastName("Doe");
		profile1.setPhone("1231231234");
		User user1 = new User();
		user1.setCredentials(credential1);
		user1.setProfile(profile1);
		user1.setDeleted(false);
		userRepository.saveAndFlush(user1);

		Credentials credential2 = new Credentials();
		credential2.setUsername("joDoe");
		credential2.setPassword("5555");
		Profile profile2 = new Profile();
		profile2.setEmail("jdoe@gmail.com");
		profile2.setFirstName("John");
		profile2.setLastName("Doe");
		profile2.setPhone("5555555555");
		User user2 = new User();
		user2.setCredentials(credential2);
		user2.setProfile(profile2);
		user2.setDeleted(false);
		userRepository.saveAndFlush(user2);

		Tweet tweet = new Tweet();
		tweet.setDeleted(false);
		tweet.setAuthor(user1.getId());
		tweet.setContent("I'm on vaction today. #test");
		List<User> likedBy = new ArrayList<>();
		likedBy.add(user2);
        tweet.setLikedByUsers(likedBy);
		tweet.setPosted(new Timestamp(System.currentTimeMillis()));
		Set<User> mentioned = new HashSet<>();
		mentioned.add(user2);
		tweet.setMentions(mentioned);

		Tweet tweet2 = new Tweet();
		tweet2.setDeleted(false);
		tweet2.setAuthor(user2.getId());
		tweet2.setContent("That is so cool! #test");
        List<User> likedBy2 = new ArrayList<>();
		likedBy2.add(user1);
		likedBy2.add(user2);
		tweet2.setLikedByUsers(likedBy2);
		tweet2.setPosted(new Timestamp(System.currentTimeMillis()));
		Set<User> mentioned2 = new HashSet<>();
		mentioned2.add(user1);
		tweet2.setMentions(mentioned2);
		tweet2.setInReplyTo(tweet2);

		tweetRepository.saveAndFlush(tweet);
		tweetRepository.saveAndFlush(tweet2);

//		List<Tweet> replies = new ArrayList<>();
//		replies.add(tweet);
//		tweet.setReplies(replies);

		/*Hashtag hashtag = new Hashtag();
		hashtag.setLabel("#test");
		hashtagRepository.saveAndFlush(hashtag);
		List<Tweet> tweetsWithHashtag = new ArrayList<>();
		tweetsWithHashtag.add(tweet);
		tweetsWithHashtag.add(tweet2);
		hashtag.setTweetsWithHashtag(tweetsWithHashtag);
		hashtagRepository.saveAndFlush(hashtag);*/

        //List<Hashtag> hashtagList = new ArrayList<>();
		//hashtagList.add(hashtag);
		//tweet.setHashtags(hashtagList);
//		tweet2.setHashtags(hashtagList);

		//tweetRepository.saveAndFlush(tweet);
		//tweetRepository.saveAndFlush(tweet2);


	}

}
