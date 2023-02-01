package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.CredentialsDto;
import com.cooksys.socialmediaassignment.dtos.UserRequestDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;
import com.cooksys.socialmediaassignment.exceptions.BadRequestException;
import com.cooksys.socialmediaassignment.exceptions.NotFoundException;
import com.cooksys.socialmediaassignment.mappers.CredentialsMapper;
import com.cooksys.socialmediaassignment.mappers.UserMapper;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final CredentialsMapper credentialsMapper;


	// Get the user information by username
	private User getUser(String username) {

		Optional<User> optionalUser = userRepository.findUserByCredentialsUsernameAndDeletedFalse(username);

		if (optionalUser.isEmpty()) {
			throw new NotFoundException("No user found with username: " + username);
		}
		return optionalUser.get();
	}

	// Validate user information
	private void validateUserRequest(UserRequestDto userRequestDto) {
		if ((userRequestDto.getCredentials() == null) || (userRequestDto.getProfile() == null)) {
			throw new BadRequestException("All fields are required on a user request dto");
		} else if(userRequestDto.getProfile().getEmail() == null) {
			throw new BadRequestException("Email is required");
		} else if((userRequestDto.getCredentials().getPassword() == null) || (userRequestDto.getCredentials().getUsername() == null)) {
			throw new BadRequestException("Username and Password are required");
		}
	}

	// Validate credentials informaion
	private void validateCredentialsDto(CredentialsDto credentialDto) {
		if ((credentialDto.getPassword() == null) || (credentialDto.getUsername() == null)) {
			throw new BadRequestException("All fields are required on a credentials dto");
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
		Credentials credentialsToUpdate = credentialsMapper.credentialsDtoToEntity(userRequestDto.getCredentials());
		userToSave.setDeleted(false);
		userToSave.setCredentials(credentialsToUpdate);
		System.out.println(userToSave);
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToSave));
	}

	@Override
	public UserResponseDto deleteUser(CredentialsDto credentialDto, String username) {
		validateCredentialsDto(credentialDto);
		User userToDelete = getUser(credentialDto.getUsername());

		if (credentialDto.getUsername().equals(username)) {
			userToDelete.setDeleted(true);
		} else {
			throw new NotFoundException("Username does not match " + username + " and " + credentialDto.getUsername());
		}
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToDelete));
	}

	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDto, String username) {
		User userToUpdate = getUser(username);
		Credentials credentialsToUpdate = userToUpdate.getCredentials();
		Profile profileToUpdate = userToUpdate.getProfile();
		User user = userMapper.userRequestDtoToEntity(userRequestDto);
		Credentials credential = credentialsMapper.credentialsDtoToEntity(userRequestDto.getCredentials());
		if(credential.getPassword() != null) {
			credentialsToUpdate.setPassword(credential.getPassword());
		} 
		if (credential.getUsername() != null) {
			credentialsToUpdate.setUsername(credential.getUsername());
		} 
		if(user.getProfile().getEmail() != null) {
			profileToUpdate.setEmail(user.getProfile().getEmail());
		}
		if(user.getProfile().getFirstName() != null) {
			profileToUpdate.setFirstName(user.getProfile().getFirstName());
		}
		if(user.getProfile().getLastName() != null) {
			profileToUpdate.setLastName(user.getProfile().getLastName());
		}
		if(user.getProfile().getPhone() != null) {
			profileToUpdate.setPhone(user.getProfile().getPhone());
		}
		userToUpdate.setCredentials(credentialsToUpdate);
		userToUpdate.setProfile(profileToUpdate);
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToUpdate));
	}

}
