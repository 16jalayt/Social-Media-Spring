package com.cooksys.socialmediaassignment.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserRequestDto {

	private CredentialRequestDto credential;
	private ProfileRequestDto profile;

}
