package com.cooksys.socialmediaassignment.dtos;

import java.sql.Timestamp;

import com.cooksys.socialmediaassignment.entities.Tweet;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {

    private Long id;
    private String author;
    private String content;
    private Timestamp posted;
    private Tweet inReplyTo;
    private Tweet repostOf;
}
