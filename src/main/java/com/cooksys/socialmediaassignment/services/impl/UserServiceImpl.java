package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.UserRequestDto;
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

	// Get the user information by username
	private User getUser(String username) {

		Optional<User> optionalUser = userRepository.findUserByCredentialUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			// throw new NotFoundException("No user found with username: " + username );
		}
		return optionalUser.get();
	}

	// Validate user information
	private void validateUserRequest(UserRequestDto userRequestDto) {
		if ((userRequestDto.getCredentials() == null) || (userRequestDto.getProfile() == null)) {
			// throw new BadRequestException("All fields are required on a user request
			// dto");
		}
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
	public UserResponseDto createUser(UserRequestDto userRequestDto) {
		validateUserRequest(userRequestDto);
		System.out.println(userRequestDto);
		// Map the user information
		User userToSave = userMapper.userRequestDtoToEntity(userRequestDto);
		userToSave.setDeleted(false);
		System.out.println(userToSave);
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToSave));
	}

}
