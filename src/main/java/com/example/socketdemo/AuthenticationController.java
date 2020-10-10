package com.example.socketdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authService;

	@RequestMapping(value = "/generate-token", method = RequestMethod.POST)
	public ResponseEntity<AuthToken> register(@RequestBody LoginUser loginUser)
			throws AuthenticationException{

		AuthToken authToken = authService.generateToken(loginUser);

		
		return ResponseEntity.ok().body(authToken);
	}

	
	

}
