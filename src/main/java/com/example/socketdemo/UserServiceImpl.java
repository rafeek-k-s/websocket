package com.example.socketdemo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * List all standards
 *
 * 
 * @return the persisted entity.
 */

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));

	}

	private Set getAuthority(User user) {
		Set authorities = new HashSet<>();

		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});

		return authorities;

	}


	

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.eduhex.service.UserService#savePassword(com.eduhex.response.PasswordDTO)
	 */

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eduhex.service.UserService#findById(java.lang.Long)
	 */
	@Override
	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		User user = optionalUser.isPresent() ? optionalUser.get() : null;
		return user;

	}

	

	/**
	 * Get all the users with eager load of many-to-many relationships.
	 *
	 * @return the list of entities.
	 */
	

	/**
	 * Get one user by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	

	
	
	


	

	
	

	
	
	

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.eduhex.service.UserService#findOneEagerData(java.lang.String)
	 */
	

	





	
	

	
	
	
	
	
	
	
	
	
}
