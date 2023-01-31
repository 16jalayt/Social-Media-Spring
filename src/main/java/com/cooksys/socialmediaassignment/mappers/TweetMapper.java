package com.cooksys.socialmediaassignment.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.embeddable.Credential;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    @Mapping(source = "author.credential", target = "author.username")
    TweetResponseDto entityToDto(Tweet entity);

    List<TweetResponseDto> entitiesToDtos (List<Tweet> entities);

    static String credentialToUsername(Credential credential) {
        return credential.getUsername();
    }
}
