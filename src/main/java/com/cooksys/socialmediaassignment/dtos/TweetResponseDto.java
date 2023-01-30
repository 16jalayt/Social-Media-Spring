package com.cooksys.socialmediaassignment.dtos;

import java.util.Date;

import com.cooksys.socialmediaassignment.entities.Hashtag;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {
    private Long id;
    private UserResponseDto author;
    private String content;
    private Date posted;
    private Tweet inReplyTo;
    private Tweet repostOf;
}
