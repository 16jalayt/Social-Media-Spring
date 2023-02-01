package com.cooksys.socialmediaassignment.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.services.HashtagService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {


    private final HashtagService hashtagService;

    @GetMapping
    public List<HashtagResponseDto> getAllTags() {
        return hashtagService.getAllTags();
    }

    //TODO:throw error if not found
    @RequestMapping(value = "/{label}")
    @GetMapping
    public List<TweetResponseDto> getTweetsWithTag(@PathVariable("label") String label) {
        return hashtagService.getTweetsWithTag(label);
    }

}
