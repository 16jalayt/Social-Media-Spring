package com.cooksys.socialmediaassignment.services;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;

import java.util.List;

public interface HashtagService {

    HashtagResponseDto validateTagExists(String label);

    List<HashtagResponseDto> getAllTags();

    List<TweetResponseDto> getTweetsWithTag(String label);
}
