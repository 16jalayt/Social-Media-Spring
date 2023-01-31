package com.cooksys.socialmediaassignment.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassignment.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateUserController {

	private final UserService userService;

	@GetMapping("/username/exists/@{username}")
	public boolean checkUserByUsername(@PathVariable String username) {
		return userService.checkUserByUsername(username);
	}

}
