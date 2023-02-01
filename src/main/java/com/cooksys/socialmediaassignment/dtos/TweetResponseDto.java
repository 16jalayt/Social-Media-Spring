package com.cooksys.socialmediaassignment.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetResponseDto {
    private Long id;
    private Integer author;
   // private Varchar content;
    private Timestamp posted;
    private Integer inReplyTo;
    private Integer repostOf;
}
