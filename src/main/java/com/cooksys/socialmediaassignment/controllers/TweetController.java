package com.cooksys.socialmediaassignment.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tweets")
public class TweetController {
//    private final TweetService tweetService;
//
//    @GetMapping
//    public List<TweetResponseDto> getAllTweets() {
//        return tweetService.getActiveTweets();
//    }
//
//    @GetMapping("/{id}")
//    public TweetResponseDto getTweetById(@PathVariable Long id) {
//        return tweetService.getTweetById(id);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public TweetResponseDto createTweet(@RequestBody TweetRequestDto tweetRequestDto) {
//        return tweetService.createTweet(tweetRequestDto);
//    }
//
//    @PostMapping("/{id}/like")
//    public void likeTweetById(@PathVariable Long id, @RequestBody Credential credential) {
//        tweetService.likeTweetById(id, credential);
//    }
//
//    @GetMapping("/{id}/likes")
//    public List<UserResponseDto> getLikeForTweet(@PathVariable Long id) {
//        return tweetService.getLikeForTweet(id);
//    }
//
//    @GetMapping("/{id}/context")
//    public ContextResponseDto getContextForTweet(@PathVariable Long id) {
//        return tweetService.getContextForTweet(id);
//    }
//
//    @DeleteMapping("/{id}")
//    public TweetResponseDto deleteTweetById(@PathVariable Long id, @RequestBody Credential credential) {
//        return tweetService.deleteTweetById(id, credential);
//    }
//
//    @PostMapping("/{id}/repost")
//    @ResponseStatus(HttpStatus.CREATED)
//    public TweetResponseDto repostTweetById(@PathVariable Long id, @RequestBody Credential credential) {
//        return tweetService.repostTweetById(id, credential);
//    }
//
//    @GetMapping("/{id}/reposts")
//    public List<TweetResponseDto> getRepostOfTweetById(@PathVariable Long id) {
//        return tweetService.getRepostOfTweetById(id);
//    }
//
//    @PostMapping("/{id}/reply")
//    @ResponseStatus(HttpStatus.CREATED)
//    public TweetResponseDto replyTweetById(@PathVariable Long id, @RequestBody TweetRequestDto tweetRequestDto) {
//        return tweetService.replyTweetById(id, tweetRequestDto);
//    }
//
//    @GetMapping("/{id}/replies")
//    public List<TweetResponseDto> getReplyToTweetById(@PathVariable Long id) {
//        return tweetService.getRepliesToTweetById(id);
//    }
//
//    @GetMapping("/{id}/mentions")
//    public List<UserResponseDto> getMentionInTweetById(@PathVariable Long id) {
//        return tweetService.getMentionInTweetById(id);
//    }
}
