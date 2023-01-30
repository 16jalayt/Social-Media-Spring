package com.cooksys.socialmediaassignment.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Hashtag {
    @Id
    @GeneratedValue
    private Long id;

}
