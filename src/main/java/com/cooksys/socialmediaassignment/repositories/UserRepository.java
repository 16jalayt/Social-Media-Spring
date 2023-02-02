package com.cooksys.socialmediaassignment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.socialmediaassignment.entities.User;
import com.cooksys.socialmediaassignment.entities.embeddable.Credentials;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByDeletedFalse();

//	Optional<User> findByUsernameByDeletedFalse(String username);


	Optional<User> findUserByCredentialsUsernameAndDeletedFalse(String username);

	Optional<User> findByCredentialUsername(String username);

	void saveAllAndFlush(User user);

	Optional<User> findOneByCredentials(Credentials credentials);


}
