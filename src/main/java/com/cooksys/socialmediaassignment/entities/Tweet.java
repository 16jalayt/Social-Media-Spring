package com.cooksys.socialmediaassignment.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"reposts", "repostOf", "hashtags", "author", "usersMentioned", "likes", "replies", "inReplyTo"})
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "tweet_hashtag_mapping",
            joinColumns = {@JoinColumn(name = "hashtag_id")},
            inverseJoinColumns = {@JoinColumn(name = "tweet_id")}
    )

    @OneToMany(mappedBy = "repostOf")
    private Set<Tweet> reposts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "repost_id")
    private Tweet repostOf;

    @OneToMany(mappedBy = "inReplyTo")
    private Set<Tweet> replies = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "reply_to_id")
    private Tweet inReplyTo;

    private String content;

    private boolean deleted;

    @Column(name = "created_on")
    @CreationTimestamp
    private Date posted;

}
