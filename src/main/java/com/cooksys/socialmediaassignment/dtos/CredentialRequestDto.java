package com.cooksys.socialmediaassignment.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CredentialRequestDto {
	
	private String username;
	private String password;

}
