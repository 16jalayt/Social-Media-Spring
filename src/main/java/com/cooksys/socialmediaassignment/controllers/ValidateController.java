package com.cooksys.socialmediaassignment.controllers;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.services.HashtagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

	private final UserService userService;
	private final HashtagService hashtagService;

	@GetMapping("/username/exists/@{username}")
	public boolean checkUserByUsername(@PathVariable String username) {
		return userService.checkUserByUsername(username);
	}

	@GetMapping("/username/available/@{username}")
	public boolean checkUsernameAvailable(@PathVariable String username) {
		return false;
	}

	@RequestMapping(value = "/tag/exists/{label}")
	@GetMapping
	public HashtagResponseDto validateTagExists(@PathVariable("label") String label) {
		return hashtagService.validateTagExists(label);
	}

}
