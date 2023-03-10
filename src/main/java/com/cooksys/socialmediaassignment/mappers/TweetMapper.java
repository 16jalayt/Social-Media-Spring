package com.cooksys.socialmediaassignment.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface TweetMapper {

    //@Mapping(source = "user.credentials.username", target = "author")
    TweetResponseDto entityToDto(Tweet entity);

    //@Mapping(target = "author", source = "user.credentials.username")
    //@Mapping(source = "author.credential", target = "author.username")
    List<TweetResponseDto> entitiesToDtos (List<Tweet> entities);

    static String credentialToUsername(Credentials credential) {
        return credential.getUsername();
    }

	List<TweetResponseDto> entitiesToTweetResposeDtos(List<Tweet> tweets);
}