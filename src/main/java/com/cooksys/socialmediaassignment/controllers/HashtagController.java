package com.cooksys.socialmediaassignment.controllers;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.services.HashtagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {

    private final HashtagService hashtagService;

    @GetMapping
    public List<HashtagResponseDto> getAllTags() {
        return hashtagService.getAllTags();
    }
    @RequestMapping(value = "/{label}")
    @GetMapping
    public List<TweetResponseDto> getTweetsWithTag(@PathVariable("label") String label) {
        return hashtagService.getTweetsWithTag(label);
    }




}
