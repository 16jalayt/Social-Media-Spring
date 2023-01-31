package com.cooksys.socialmediaassignment.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	private CredentialResponseDto username;
	private ProfileResponseDto profile;
	private long joined;

}
