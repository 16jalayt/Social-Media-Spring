package com.cooksys.socialmediaassignment.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    TweetResponseDto entityToDto(Tweet entity);

    List<TweetResponseDto> entitiesToDtos (List<Tweet> entities);

    static String credentialToUsername(Credentials credential) {
        return credential.getUsername();
    }
}
