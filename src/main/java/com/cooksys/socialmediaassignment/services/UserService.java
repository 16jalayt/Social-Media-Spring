package com.cooksys.socialmediaassignment.services;

import java.util.List;

import com.cooksys.socialmediaassignment.dtos.CredentialsDto;
import com.cooksys.socialmediaassignment.dtos.UserRequestDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;

public interface UserService {

	List<UserResponseDto> getAllUsers();

	UserResponseDto getUserByUsername(String username);

	UserResponseDto createUser(UserRequestDto userRequestDto);

	UserResponseDto deleteUser(CredentialsDto credentialDto, String username);

	UserResponseDto updateUser(UserRequestDto userRequestDto, String username);

}
