package com.cooksys.socialmediaassignment.services;

import java.util.List;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;

public interface HashtagService {

	HashtagResponseDto validateTagExists(String label);

	List<HashtagResponseDto> getAllTags();

	List<TweetResponseDto> getTweetsWithTag(String label);
}
