package com.cooksys.socialmediaassignment.services.impl;

import java.util.Optional;

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

	@Override
	public boolean checkUserByUsername(String username) {
		Optional<User> optionalUser = userRepository.findUserByCredentialUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			return false;
		}
		return true;
	}

}
