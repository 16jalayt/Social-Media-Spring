package com.cooksys.socialmediaassignment.entities.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
public class Profile {
    private String firstName;
    private String lastName;
    private String email;
    private String phone; 
}
