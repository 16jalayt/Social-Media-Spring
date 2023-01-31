package com.cooksys.socialmediaassignment.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	List<UserResponseDto> entitiesToUserResponseDtos(List<User> users);

}
