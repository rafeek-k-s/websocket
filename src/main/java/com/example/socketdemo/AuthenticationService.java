package com.example.socketdemo;

import javax.validation.Valid;


public interface AuthenticationService {

	AuthToken generateToken(LoginUser loginUser) ;

	

}
