package com.cooksys.socialmediaassignment.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassignment.services.ValidateService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/validate")
public class ValidateController {

	private final ValidateService validateService;

	@GetMapping("/username/exists/@{username}")
	public boolean checkUserByUsername(@PathVariable String username) {
		return validateService.checkUserByUsername(username);
	}
	
	@GetMapping("/username/available/@{username}")
	public boolean checkUsernameAvailability(@PathVariable String username) {
		return validateService.checkUsernameAvailability(username);
	}

}
