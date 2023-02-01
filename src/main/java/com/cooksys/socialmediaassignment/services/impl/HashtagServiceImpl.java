package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.mappers.HashtagMapper;
import com.cooksys.socialmediaassignment.mappers.TweetMapper;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.services.HashtagService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

	private final HashtagRepository hashtagRepository;
	private final HashtagMapper hashtagMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;

	@Override
	public List<HashtagResponseDto> getAllTags() {
		return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
	}

	@Override
	public List<TweetResponseDto> getTweetsWithTag(String label) {
		// List<Tweet> tweets = tweetRepository.findTweetsbyHashtag(label);
		// List<Tweet> tweets = tweetRepository.findAll();
		// List<Tweet> results = new ArrayList<>();

		// return tweetMapper.entitiesToDtos(results);
		return null;
	}
}
