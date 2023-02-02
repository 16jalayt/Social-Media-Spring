package com.cooksys.socialmediaassignment.controllers;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.services.HashtagService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.cooksys.socialmediaassignment.services.ValidateService;
import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

	private final ValidateService validateService;
	private final HashtagService hashtagService;

	@GetMapping("/username/exists/@{username}")
	@ResponseStatus(HttpStatus.FOUND)
	public boolean checkUserByUsername(@PathVariable String username) {
		return validateService.checkUserByUsername(username);
	}
	
	@GetMapping("/username/available/@{username}")
	@ResponseStatus(HttpStatus.FOUND)
	public boolean checkUsernameAvailability(@PathVariable String username)
	{
		return validateService.checkUsernameAvailability(username);
	}

	@RequestMapping(value = "/tag/exists/{label}")
	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public boolean validateTagExists(@PathVariable("label") String label) {
		return validateService.validateTagExists(label);
	}

}
