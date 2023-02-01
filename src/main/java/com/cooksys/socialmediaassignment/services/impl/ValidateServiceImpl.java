package com.cooksys.socialmediaassignment.services.impl;

import java.util.Optional;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.mappers.UserMapper;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import com.cooksys.socialmediaassignment.services.ValidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final HashtagRepository hashtagRepository;

	private boolean checkUsername(String username) {
		Optional<User> optionalUser = userRepository.findUserByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkUserByUsername(String username) {

		return checkUsername(username);
	}

	@Override
	public boolean checkUsernameAvailability(String username) {

		return !(checkUsername(username));
	}

	@Override
	public boolean validateTagExists(String label) {
		if( hashtagRepository.findByLabel(label)!=null)
			return true;
		else
			return false;
	}
}
