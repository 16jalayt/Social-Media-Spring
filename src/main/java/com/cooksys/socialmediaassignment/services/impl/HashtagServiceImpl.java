package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;

import com.cooksys.socialmediaassignment.entities.Hashtag;
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
		Hashtag result = hashtagRepository.findByLabel(label);
		//TODO:Error handling
		if(result == null)
			return null;
		else
			return tweetMapper.entitiesToDtos(result.getTweetsWithHashtag());
	}
}
