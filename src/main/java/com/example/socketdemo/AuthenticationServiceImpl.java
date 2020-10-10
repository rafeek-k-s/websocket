package com.example.socketdemo;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Service(value = "authService")

public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider jwtTokenUtil;

  

    
    @Autowired
    private UserRepository userRepository;
    
 

	@Override
	public AuthToken generateToken(LoginUser loginUser) /*throws UnAuthorisedException*/ {
		
		System.out.println("+++++++++++++++++socialloginpassword"+loginUser.getPassword());
		final Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("ttttttttttttttttttttttttttttt"+authentication.toString());
		final String token = jwtTokenUtil.generateToken(authentication);
		User user = userRepository.findByUsername(loginUser.getUsername());
		/*if(!(user.isStatus()))
			throw new UnAuthorisedException("The user has been deactivated, contact Administrator");*/

			return new AuthToken(token, user);
	}

	
    
        
    
    
}
