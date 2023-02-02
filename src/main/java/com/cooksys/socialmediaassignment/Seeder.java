package com.cooksys.socialmediaassignment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

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
		List<User> follower = new ArrayList<User>();
		follower.add(user2);
		user1.setFollowers(follower);
		user1.setFollowing(follower);
		userRepository.saveAndFlush(user1);

//		Tweet tweet = new Tweet();
//		tweet.setDeleted(false);
//		tweet.setAuthor(user1);
//		tweet.setContent("I'm on vaction today. #test");
//		Set<User> likes = new HashSet<>();
//		likes.add(user2);
//		tweet.setLikes(likes);
//		tweet.setPosted(new Timestamp(System.currentTimeMillis()));
//		Set<User> mentioned = new HashSet<>();
//		mentioned.add(user2);
//		tweet.setUsersMentioned(mentioned);
//
//		Tweet tweet2 = new Tweet();
//		tweet2.setDeleted(false);
//		tweet2.setAuthor(user2);
//		tweet2.setContent("That is so cool! #test");
//		Set<User> likes2 = new HashSet<>();
//		likes.add(user1);
//		likes.add(user2);
//		tweet2.setLikes(likes);
//		tweet2.setPosted(new Timestamp(System.currentTimeMillis()));
//		Set<User> mentioned2 = new HashSet<>();
//		mentioned2.add(user1);
//		tweet2.setUsersMentioned(mentioned2);
//		tweet2.setInReplyTo(tweet2);
//
//		Set<Tweet> replies = new HashSet<>();
//		replies.add(tweet);
//		tweet.setReplies(replies);
//
//		Hashtag hashtag = new Hashtag();
//		hashtag.setLabel("#test");
//		hashtag.setFirstUsed(new Timestamp(System.currentTimeMillis()));
//		hashtag.setFirstUsed(new Timestamp(System.currentTimeMillis()));
//		List<Tweet> tweetsWithHashtag = new ArrayList<>();
//		tweetsWithHashtag.add(tweet);
//		tweetsWithHashtag.add(tweet2);
//		hashtag.setTweetsWithHashtag(tweetsWithHashtag);
//		hashtagRepository.saveAndFlush(hashtag);
//
//		Set<Hashtag> hashtagList = new HashSet<>();
//		hashtagList.add(hashtag);
//		tweet.setHashtags(hashtagList);
//		tweet2.setHashtags(hashtagList);
//
//		tweetRepository.saveAndFlush(tweet);
//		tweetRepository.saveAndFlush(tweet2);

	}

}
