package com.cooksys.socialmediaassignment.mappers;

import org.mapstruct.Mapper;

import com.cooksys.socialmediaassignment.dtos.CredentialRequestDto;
import com.cooksys.socialmediaassignment.entities.embeddable.Credential;

@Mapper(componentModel = "spring")
public interface CredentialMapper {

	Credential CredentialRequestDtoToEntity(CredentialRequestDto credentialRequestDto);

}
