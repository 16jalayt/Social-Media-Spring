package com.cooksys.socialmediaassignment.services;

public interface ValidateService {

	boolean checkUserByUsername(String username);

	boolean checkUsernameAvailability(String username);

}
