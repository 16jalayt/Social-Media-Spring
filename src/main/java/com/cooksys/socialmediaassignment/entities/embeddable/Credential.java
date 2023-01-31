package com.cooksys.socialmediaassignment.entities.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Credential {

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;
}
