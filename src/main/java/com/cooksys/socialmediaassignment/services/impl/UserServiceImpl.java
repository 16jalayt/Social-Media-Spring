package com.cooksys.socialmediaassignment.services.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.CredentialsDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.dtos.UserRequestDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.exceptions.BadRequestException;
import com.cooksys.socialmediaassignment.exceptions.NotFoundException;
import com.cooksys.socialmediaassignment.exceptions.UnauthorizedException;
import com.cooksys.socialmediaassignment.mappers.TweetMapper;
import com.cooksys.socialmediaassignment.mappers.UserMapper;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final TweetRepository tweetRepository;
	private final TweetMapper tweetMapper;

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
		} else if (userRequestDto.getProfile().getFirstName() == null) {
			throw new BadRequestException("First name is required");
		} else if (userRequestDto.getProfile().getLastName() == null) {
			throw new BadRequestException("Last name is required");
		} else if (userRequestDto.getProfile().getPhone() == null) {
			throw new BadRequestException("Phone number is required");
		} else if ((userRequestDto.getCredentials().getPassword() == null)
				|| (userRequestDto.getCredentials().getUsername() == null)) {
			throw new BadRequestException("Username and Password are required");
		}
	}

	// Validate credentials informaion
	private void validateCredentialsDto(CredentialsDto credentialDto) {
		User user = getUser(credentialDto.getUsername());
		if ((credentialDto.getPassword() == null) || (credentialDto.getUsername() == null)) {
			throw new BadRequestException("Username and password are required");
		} else if (!user.getCredentials().getUsername().equals(credentialDto.getUsername())) {
			throw new UnauthorizedException("username is not correct.");
		} else if (!user.getCredentials().getPassword().equals(credentialDto.getPassword())) {
			throw new UnauthorizedException("Password is not correct.");
		}
	}
	
	//Validate user's credential
	private void validateCredential(User userToCheck) {
		User user = getUser(userToCheck.getCredentials().getUsername());
		if ((userToCheck.getCredentials().getPassword() == null) || (userToCheck.getCredentials().getUsername() == null)) {
			throw new BadRequestException("Username and password are required");
		} else if (!user.getCredentials().getUsername().equals(userToCheck.getCredentials().getUsername())) {
			throw new UnauthorizedException("username is not correct.");
		} else if (!user.getCredentials().getPassword().equals(userToCheck.getCredentials().getPassword())) {
			throw new UnauthorizedException("username is not correct.");
		}
	}

	// Validate follower/following information
	private void validateFollow(User makiko, User helena) {
		if (helena.getFollowers().contains(makiko)) {
			throw new BadRequestException("The user: " + makiko.getCredentials().getUsername()
					+ " is already following the user: " + helena.getCredentials().getUsername());
		}

		helena.getFollowers().add(makiko);
		userRepository.saveAndFlush(helena);

	}

	// Unfollow user
	private void unFollowUser(User helena, User makiko) {
		if (!helena.getFollowers().contains(makiko)) {
			throw new BadRequestException("The user: " + makiko.getCredentials().getUsername()
					+ " is not following the user: " + helena.getCredentials().getUsername());
		}

		helena.getFollowers().remove(makiko);
		userRepository.saveAndFlush(helena);

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
		userToDelete.setDeleted(true);
		return userMapper.entityToUserResponseDto(userRepository.saveAndFlush(userToDelete));
	}

	@Override
	public UserResponseDto updateUser(UserRequestDto userRequestDto, String username) {
		User userToUpdate = getUser(username);
		User user = userMapper.userRequestDtoToEntity(userRequestDto);
		validateCredential(user);

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
		validateCredentialsDto(credentialsDto);
		User following = getUser(username);
		User follower = getUser(credentialsDto.getUsername());
		validateFollow(follower, following);
	}

	@Override
	public void createUnFollower(String username, CredentialsDto credentialsDto) {
		validateCredentialsDto(credentialsDto);
		User user = getUser(username);
		User follower = getUser(credentialsDto.getUsername());
		unFollowUser(user, follower);
	}

	@Override
	public List<UserResponseDto> getFollowersByUsername(String username) {
		User user = getUser(username);
		return userMapper.entitiesToUserResponseDtos(user.getFollowers());
	}

	@Override
	public List<UserResponseDto> getFollowingByUsername(String username) {
		User user = getUser(username);
		return userMapper.entitiesToUserResponseDtos(user.getFollowing());
	}

	@Override
	public List<TweetResponseDto> getTweetByUsername(String username) {
		User user = getUser(username);
		Long author = user.getId();
		System.out.println("author" + author);
		List<Tweet> tweets = tweetRepository.findAllByAuthorAndDeletedFalseOrderByPostedDesc(author);
		System.out.println("tweets" + tweets.get(0));
//		return tweetMapper.entitiesToDtos(tweets);
		return null;
	}

	@Override
	public List<TweetResponseDto> getMentions(String username)
	{
		User user = getUser(username);
		if(user==null)
			throw new NotFoundException("Username not found: "+username);

		return tweetMapper.entitiesToDtos(user.getMentions());
	}

	@Override
	public List<TweetResponseDto> getFeed(String username)
	{
		User user = getUser(username);
		if(user==null)
			throw new NotFoundException("Username not found: "+username);

		List<Tweet> resultTweets = tweetRepository.findAllByAuthorAndDeletedFalseOrderByPostedDesc(user.getId());
		for(User followee: user.getFollowing())
		{
			resultTweets.addAll(tweetRepository.findAllByAuthorAndDeletedFalseOrderByPostedDesc(followee.getId()));
		}

		resultTweets.sort(Comparator.comparing(Tweet::getPosted));

		return null;
	}

}
