package com.cooksys.socialmediaassignment.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.CredentialsDto;
import com.cooksys.socialmediaassignment.dtos.UserRequestDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;
import com.cooksys.socialmediaassignment.entities.embeddable.Profile;
import com.cooksys.socialmediaassignment.exceptions.BadRequestException;
import com.cooksys.socialmediaassignment.exceptions.NotFoundException;
import com.cooksys.socialmediaassignment.exceptions.UnauthorizedException;
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

	// Get deleted user
	private User getDeletedUser(String username) {
		Optional<User> optionalUser = userRepository.findUserByCredentialsUsername(username);
		return optionalUser.get();
	}

	// Check if the user is deleted in the past
	private boolean validateDuplicateUser(String username) {

		Optional<User> optionalUser = userRepository.findUserByCredentialsUsername(username);
		if (optionalUser.isPresent() && optionalUser.get().isDeleted()) {
			return true;
		} else if (optionalUser.isPresent() && !optionalUser.get().isDeleted()) {
			throw new BadRequestException("User(" + username + ") is already exists");
		}
		return false;
	}

	// Validate user information
	private void validateUserRequest(UserRequestDto userRequestDto) {
		if ((userRequestDto.getCredentials() == null) || (userRequestDto.getProfile() == null)) {
			throw new BadRequestException("All fields are required on a user request dto");
		} else if (userRequestDto.getProfile().getEmail() == null) {
			throw new BadRequestException("Email is required");
		} else if ((userRequestDto.getCredentials().getPassword() == null)
				|| (userRequestDto.getCredentials().getUsername() == null)) {
			throw new BadRequestException("Username and Password are required");
		}
	}

	// Validate credentials informaion
	private void validateCredentialsDto(CredentialsDto credentialDto) {
		if ((credentialDto.getPassword() == null) || (credentialDto.getUsername() == null)) {
			throw new BadRequestException("All fields are required on a credentials dto");
		}
	}

	// Validate follower/following information
	private void validateFollow(User follower, User following) {
		if (follower.getFollowers().contains(following)) {
			throw new BadRequestException("The user: " + following.getCredentials().getUsername()
					+ " is already ;following the user: " + follower.getCredentials().getUsername());
		}
		Set<User> followerSet = follower.getFollowers();
		followerSet.add(following);
		follower.setFollowers(followerSet);
		Set<User> followingSet = following.getFollowing();
		followingSet.add(follower);
		following.setFollowing(followingSet);

	}

	private void validateAuthentication(User user, CredentialsDto credentialDto) {
		if ((!user.getCredentials().getUsername().equals(credentialDto.getUsername()))
				|| (!user.getCredentials().getPassword().equals(credentialDto.getPassword()))) {
	
			throw new UnauthorizedException("Do not match username or/and password");
		}
	}
	
	private void validateUserAuthentication(User user1, User user2) {
		if ((!user1.getCredentials().getUsername().equals(user2.getCredentials().getUsername()))
				|| (!user1.getCredentials().getPassword().equals(user2.getCredentials().getPassword()))) {
	
			throw new UnauthorizedException("Do not match username or/and password");
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
		// Map the user information
		User userToSave = userMapper.userRequestDtoToEntity(userRequestDto);
		if (validateDuplicateUser(userToSave.getCredentials().getUsername())) {
			userToSave = getDeletedUser(userToSave.getCredentials().getUsername());
			userToSave.setDeleted(false);
		}
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToSave));
	}

	@Override
	public UserResponseDto deleteUser(CredentialsDto credentialDto, String username) {
		validateCredentialsDto(credentialDto);
		User userToDelete = getUser(username);
		validateAuthentication(userToDelete, credentialDto);
		userToDelete.setDeleted(true);
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToDelete));
	}

	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDto, String username) {
		validateUserRequest(userRequestDto);
		User userToUpdate = getUser(username);
		User user = userMapper.userRequestDtoToEntity(userRequestDto);
		validateUserAuthentication(userToUpdate, user);
		
//		if (user.getCredentials().getPassword() != null) {
//			userToUpdate.getCredentials().setPassword(user.getCredentials().getPassword());
//		}
		if (user.getProfile().getEmail() != null) {
			userToUpdate.getProfile().setEmail(user.getProfile().getEmail());
		}
		if (user.getProfile().getFirstName() != null) {
			userToUpdate.getProfile().setFirstName(user.getProfile().getFirstName());
		}
		if (user.getProfile().getLastName() != null) {
			userToUpdate.getProfile().setLastName(user.getProfile().getLastName());
		}
		if (user.getProfile().getPhone() != null) {
			userToUpdate.getProfile().setPhone(user.getProfile().getPhone());
		}

		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToUpdate));
	}

	@Override
	public void createFollower(String username, CredentialsDto credentialsDto) {
		User follower = getUser(username);
		User following = getUser(credentialsDto.getUsername());
		System.out.println(follower);
		validateFollow(follower, following);
		userRepository.saveAndFlush(follower);
		userRepository.saveAndFlush(following);
	}

}
