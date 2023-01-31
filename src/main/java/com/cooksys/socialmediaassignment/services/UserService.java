package com.cooksys.socialmediaassignment.services;

import java.util.List;

import com.cooksys.socialmediaassignment.dtos.UserResponseDto;

public interface UserService {

	List<UserResponseDto> getAllUsers();

//	boolean checkUser(String username);

}
