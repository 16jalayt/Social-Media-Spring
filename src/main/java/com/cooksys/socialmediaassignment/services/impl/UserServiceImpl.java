package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.mappers.UserMapper;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	private User getUser(String username) {

		Optional<User> optionalUser = userRepository.findUserByCredentialUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			// throw new NotFoundException("No user found with username: " + username );
		}
		return optionalUser.get();
	}

	@Override
	public List<UserResponseDto> getAllUsers() {
		return userMapper.entitiesToUserResponseDtos(userRepository.findAllByDeletedFalse());
	}

	@Override
	public UserResponseDto getUserByUsername(String username) {
		return userMapper.entityToUserResponseDto(getUser(username));

	}

	@Override
	public boolean checkUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findUserByCredentialUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			return false;
		}
		return true;
	}

}
