package com.cooksys.socialmediaassignment.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target="username", source="credential.username")
	UserResponseDto entityToUserResponseDto(User user);

	List<UserResponseDto> entitiesToUserResponseDtos(List<User> users);


}
