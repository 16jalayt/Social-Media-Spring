package com.cooksys.socialmediaassignment.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Credential {
	private String username;
	
	private String password; 

}
