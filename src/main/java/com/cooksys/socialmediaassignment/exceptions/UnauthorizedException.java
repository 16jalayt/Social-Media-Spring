package com.cooksys.socialmediaassignment.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
}