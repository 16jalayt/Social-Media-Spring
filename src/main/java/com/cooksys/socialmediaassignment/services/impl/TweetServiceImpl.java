package com.cooksys.socialmediaassignment.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.socialmediaassignment.dtos.ContextResponseDto;
import com.cooksys.socialmediaassignment.dtos.HashtagResponseDto;
import com.cooksys.socialmediaassignment.dtos.TweetRequestDto;
import com.cooksys.socialmediaassignment.dtos.TweetResponseDto;
import com.cooksys.socialmediaassignment.dtos.UserResponseDto;
import com.cooksys.socialmediaassignment.entities.Hashtag;
import com.cooksys.socialmediaassignment.entities.Tweet;
import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;
import com.cooksys.socialmediaassignment.exceptions.BadRequestException;
import com.cooksys.socialmediaassignment.exceptions.NotFoundException;
import com.cooksys.socialmediaassignment.exceptions.UnauthorizedException;
import com.cooksys.socialmediaassignment.mappers.TweetMapper;
import com.cooksys.socialmediaassignment.mappers.UserMapper;
import com.cooksys.socialmediaassignment.repositories.HashtagRepository;
import com.cooksys.socialmediaassignment.repositories.TweetRepository;
import com.cooksys.socialmediaassignment.repositories.UserRepository;
import com.cooksys.socialmediaassignment.services.TweetService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService {
	
    private final TweetMapper tweetMapper;
    private final TweetRepository tweetRepository;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final HashtagRepository hashtagRepository;

    @Override
    public List<TweetResponseDto> getActiveTweets() {
        return tweetMapper.entitiesToDtos(tweetRepository.findAll().stream().filter(tweet -> !tweet.isDeleted()).collect(Collectors.toList()));
    }

    @Override
    public TweetResponseDto getTweetById(Long id) {
        return tweetMapper.entityToDto(_getActiveTweetById(id));
    }

    @Override
    public TweetResponseDto createTweet(TweetRequestDto tweetRequestDto) {
        Long author = _authorizeCredential(tweetRequestDto.getCredentials());
        Tweet tweet = new Tweet();
        tweet.setAuthor(author);
        tweet.setContent(tweetRequestDto.getContent());
        _processTweetContent(tweet);
        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweet));
    }

    @Override
    public void likeTweetById(Long id, Credentials credentials) {
//        User user = _authorizeCredential(credentials);
//        Tweet tweet = _getActiveTweetById(id);
//        List<Tweet> likedTweets = user.getLikedTweets();
//        likedTweets.add(tweet);
//        user.setLikedTweets(likedTweets);
//        userRepository.saveAndFlush(user);
    }

    @Override
    public ContextResponseDto getContextForTweet(Long id) {
        Tweet tweet = _getActiveTweetById(id);
        ContextResponseDto responseDto = new ContextResponseDto();
        responseDto.setTarget(tweetMapper.entityToDto(tweet));
        responseDto.setBefore(tweetMapper.entitiesToDtos(_getTweetsBefore(tweet)));
        responseDto.setAfter(tweetMapper.entitiesToDtos(_getTweetsAfter(tweet)));
        return responseDto;
    }

    @Override
    public TweetResponseDto deleteTweetById(Long id, Credentials credentials) {
        Tweet tweet = _getActiveTweetById(id);
        Long user = _authorizeCredential(credentials);
        if (user != tweet.getAuthor())
            throw new UnauthorizedException("Bad credentials");
        tweet.setDeleted(true);

        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweet));
    }

    @Override
    public TweetResponseDto repostTweetById(Long id, Credentials credentials) {
    	Long user = _authorizeCredential(credentials);
        Tweet originalTweet = _getActiveTweetById(id);
        Tweet repostTweet = new Tweet();
        repostTweet.setAuthor(user);
        repostTweet.setRepostOf(originalTweet);
        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(repostTweet));
    }

    @Override
    public List<TweetResponseDto> getRepostOfTweetById(Long id) {
        Tweet tweet = _getActiveTweetById(id);
        return tweetMapper.entitiesToDtos(_activeTweets(tweet.getReposts()));
    }

    @Override
    public TweetResponseDto replyTweetById(Long id, TweetRequestDto tweetRequestDto) {
        Tweet tweetToReply = _getActiveTweetById(id);
        Long author = _authorizeCredential((Credentials) tweetRequestDto.getCredentials());

        Tweet tweet = new Tweet();
        tweet.setInReplyTo(tweetToReply);
        tweet.setContent(tweetRequestDto.getContent());
        tweet.setAuthor(author);
        _processTweetContent(tweet);
        return tweetMapper.entityToDto(tweetRepository.saveAndFlush(tweet));
    }

    @Override
    public List<TweetResponseDto> getRepliesToTweetById(Long id) {
        Tweet tweet = _getActiveTweetById(id);
        return tweetMapper.entitiesToDtos(_activeTweets(tweet.getReplies()));
    }

    @Override
    public List<UserResponseDto> getMentionInTweetById(Long id) {
        Tweet tweet = _getActiveTweetById(id);
        return userMapper.entitiesToUserResponseDtos(tweet.getMentions().stream().filter(u -> !u.isDeleted()).collect(Collectors.toList()));
    }

    @Override
    public List<UserResponseDto> getLikeForTweet(Long id) {
        Tweet tweet = _getActiveTweetById(id);
        return userMapper.entitiesToUserResponseDtos(tweet.getLikedByUsers().stream().filter(u -> !u.isDeleted()).collect(Collectors.toList()));
    }

    @Override
    public List<TweetResponseDto> getUserTweets(String username) {
        User user = _getUserByUsername(username);
        if (user == null || user.isDeleted())
            throw new NotFoundException("User not found");

        List<Tweet> response = user.getTweets().stream().filter(t -> !t.isDeleted()).collect(Collectors.toList());
        response.sort(Comparator.comparing(Tweet::getPosted));
        Collections.reverse(response);
        return tweetMapper.entitiesToDtos(response);
    }

    @Override
    public List<TweetResponseDto> getTweetsByMention(String username) {
        User user = _getUserByUsername(username);
        if (user == null || user.isDeleted())
            throw new NotFoundException("User not found");

        List<Tweet> response = user.getMentions().stream().filter(t -> !t.isDeleted()).collect(Collectors.toList());
        response.sort(Comparator.comparing(Tweet::getPosted));
        Collections.reverse(response);
        return tweetMapper.entitiesToDtos(response);
    }

    @Override
    public List<TweetResponseDto> getUserFeed(String username) {
        User user = _getUserByUsername(username);
        if (user == null || user.isDeleted())
            throw new NotFoundException("User not found");

        Queue<User> userQueue = new LinkedList<>();

        userQueue.add(user);
        userQueue.addAll(user.getFollowing());
        Set<Tweet> tweetSet = new HashSet<>();

        while(!userQueue.isEmpty()) {
            User author = userQueue.poll();
            if (!author.isDeleted()) {
                tweetSet.addAll(author.getTweets().stream().filter(t -> !t.isDeleted()).collect(Collectors.toList()));
            }
        }

        List<Tweet> response = new ArrayList<>(tweetSet);
        response.sort(Comparator.comparing(Tweet::getPosted));
        Collections.reverse(response);
        return tweetMapper.entitiesToDtos(response);
    }
    
	

	
    /*
     * Methods to call in TweetServiceImpl methods
     */
    private Long _authorizeCredential(Credentials credentials) {
        Optional<User> userOptional = userRepository.findUserByCredentialsUsername(credentials.getUsername());
        if (userOptional.isEmpty() || userOptional.get().isDeleted())
            throw new UnauthorizedException("Bad credentials");
        return userOptional.get().getId();
    }

    private Tweet _getActiveTweetById(Long id) {
        Optional<Tweet> tweetOptional = tweetRepository.findById(id);
        if (tweetOptional.isEmpty() || tweetOptional.get().isDeleted())
            throw new BadRequestException("Tweet not found");
        return tweetOptional.get();
    }

    private List<Tweet> _getTweetsBefore(Tweet tweet) {
        List<Tweet> tweetList = new ArrayList<>();
        Tweet actualTweet = tweet;
        while (actualTweet.getInReplyTo() != null) {
            actualTweet = actualTweet.getInReplyTo();
            if (!actualTweet.isDeleted())
                tweetList.add(actualTweet);
        }
        return tweetList;
    }

    private List<Tweet> _getTweetsAfter(Tweet tweet) {
        List<Tweet> tweetList = new ArrayList<>();
        Queue<Tweet> tweetQueue = new LinkedList<>();
        tweetQueue.addAll(tweet.getReplies());

        while (tweetQueue.size() > 0) {
            Tweet actualTweet = tweetQueue.poll();
            if (!actualTweet.isDeleted())
                tweetList.add(actualTweet);
            tweetQueue.addAll(actualTweet.getReplies());
        }
        return tweetList;
    }

    private void _processTweetContent (Tweet tweet) {
        String content = tweet.getContent();

        List<String> mentions = _getMatches(content, "@");
        List<String> tagLabels = _getMatches(content, "#");

        for (String tagLabel : tagLabels) {
            Optional<Hashtag> hashtagOptional = Optional.of(hashtagRepository.findByLabel(tagLabel));
            Hashtag hashtag;
            if (hashtagOptional.isEmpty()) {
                hashtag = new Hashtag();
                hashtag.setLabel(tagLabel);
            } else {
                hashtag = hashtagOptional.get();
            }
            hashtag.setLastUsed((Timestamp) new Timestamp(System.currentTimeMillis()));
            hashtag = hashtagRepository.saveAndFlush(hashtag);
            tweet.addHashtag(hashtag);
        }

        Set<Hashtag> hashtagSet = new HashSet<>(tweet.getHashtags());
        for (Hashtag hashtag : hashtagSet) {
            if (!tagLabels.contains(hashtag.getLabel()))
                tweet.removeHashtag(hashtag);
        }

        for (String username : mentions) {
            User user = _getUserByUsername(username);
            if (user == null)
                continue;
            tweet.addMentionedUser(user);
        }

        Set<User> mentionSet = new HashSet<>(tweet.getMentions());
        for (User mention : mentionSet) {
            if (!mentions.contains(mention.getCredentials().getUsername()))
                tweet.removeMentionedUser(mention);
        }
    }

    private List<String> _getMatches(String text, String matchBegin) {
        Set<Character> specialChars = Set.of('@', ' ', '#');

        int pointer = 0;
        List<String> matchList = new ArrayList<>();

        while (text.indexOf(matchBegin, pointer) >= 0) {
            pointer = text.indexOf(matchBegin, pointer);
            String username = "";
            int auxPointer = pointer + 1;
            while (auxPointer < text.length() && !specialChars.contains(text.charAt(auxPointer)))
                username = username + text.charAt(auxPointer++);
            matchList.add(username);
            pointer = auxPointer;
        }

        return  matchList;
    }

    private User _getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findUserByCredentialsUsername(username);
        if (userOptional.isEmpty())
            return null;
        return userOptional.get();
    }

    private List<Tweet> _activeTweets (Collection<Tweet> tweets) {
        return tweets.stream().filter(t -> !t.isDeleted()).collect(Collectors.toList());
    }

	@Override
	public List<HashtagResponseDto> getHashtagsbyTweetById(Long id) {
	
		return null;
	}
}
