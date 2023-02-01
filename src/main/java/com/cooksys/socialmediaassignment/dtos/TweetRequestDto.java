package com.cooksys.socialmediaassignment.dtos;

import com.cooksys.socialmediaassignment.entities.embeddable.Credential;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TweetRequestDto {
  //  private Varchar content;
    private Credential credentials;
}
