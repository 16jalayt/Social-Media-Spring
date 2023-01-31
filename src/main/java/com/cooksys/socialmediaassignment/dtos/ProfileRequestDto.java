package com.cooksys.socialmediaassignment.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProfileRequestDto {

	private String firstName;

	private String lastName;

	private String email;

	private String phone;

}
