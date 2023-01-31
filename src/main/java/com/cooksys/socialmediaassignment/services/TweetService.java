package com.cooksys.socialmediaassignment.services;

import java.util.List;

import com.cooksys.socialmediaassignment.dtos.ContextResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetRequestDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.embeddable.Credential;

public interface TweetService {
    List<TweetResponseDto> getActiveTweets();

    TweetResponseDto getTweetById(Long id);

    TweetResponseDto createTweet(TweetRequestDto tweetRequestDto);

    void likeTweetById(Long id, Credential credential);

    ContextResponseDto getContextForTweet(Long id);

    TweetResponseDto deleteTweetById(Long id, Credential credential);

    TweetResponseDto repostTweetById(Long id, Credential credential);

    List<TweetResponseDto> getRepostOfTweetById(Long id);

    TweetResponseDto replyTweetById(Long id, TweetRequestDto tweetRequestDto);

    List<TweetResponseDto> getRepliesToTweetById(Long id);

    List<UserResponseDto> getMentionInTweetById(Long id);

    List<UserResponseDto> getLikeForTweet(Long id);

    List<TweetResponseDto> getUserTweets(String username);

    List<TweetResponseDto> getTweetsByMention(String username);

    List<TweetResponseDto> getUserFeed(String username);
}
