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
public class ValidateHashtagController {

    private final HashtagService hashtagService;

    @RequestMapping(value = "/tag/exists/{label}")
    @GetMapping
    public HashtagResponseDto validateTagExists(@PathVariable("label") String label) {
        return hashtagService.validateTagExists(label);
    }
}
