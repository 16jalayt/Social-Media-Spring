package com.cooksys.socialmediaassignment.services.impl;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.entities.Hashtag;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.mappers.HashtagMapper;
import com.cooksys.socialmediaassignment.mappers.TweetMapper;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    private final HashtagRepository hashtagRepository;
    private final HashtagMapper hashtagMapper;
    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;

    @Override
    public HashtagResponseDto validateTagExists(String label)
    {
        return hashtagMapper.entityToDto(hashtagRepository.findByLabel(label));
    }

    @Override
    public List<HashtagResponseDto> getAllTags()
    {
        return hashtagMapper.entitiesToDtos(hashtagRepository.findAll());
    }

    @Override
    public List<TweetResponseDto> getTweetsWithTag(String label)
    {
        //List<Tweet> tweets = tweetRepository.findTweetsbyHashtag(label);
        //List<Tweet> tweets = tweetRepository.findAll();
        //List<Tweet> results = new ArrayList<>();



        //return tweetMapper.entitiesToDtos(results);
        return null;
    }
}
