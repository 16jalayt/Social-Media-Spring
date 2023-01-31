package com.cooksys.socialmediaassignment.dtos;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {

	private String username;
	private ProfileResponseDto profile;
	private Timestamp joined;

}
