package com.example.socketdemo.websocket;

import static com.example.socketdemo.Constants.TOKEN_PREFIX;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.example.socketdemo.TokenProvider;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.springframework.http.server.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.*;

import org.springframework.messaging.support.MessageHeaderAccessor;
//import io.github.jhipster.sample.security.AuthoritiesConstants;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	public static final String IP_ADDRESS = "IP_ADDRESS";

	@Autowired
	private TokenProvider jwtTokenUtil;

	@Autowired

	private UserDetailsService userDetailsService;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// registry.addEndpoint("/ws").setHandshakeHandler(defaultHandshakeHandler()).setAllowedOrigins("*").withSockJS().setInterceptors(httpSessionHandshakeInterceptor());;
		registry.addEndpoint("/ws").setHandshakeHandler(defaultHandshakeHandler()).setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/user"); // Enables a simple in-memory broker

	}

	@Bean
	public HandshakeInterceptor httpSessionHandshakeInterceptor() {
		return new HandshakeInterceptor() {

			@Override
			public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
				if (request instanceof ServletServerHttpRequest) {
					ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
					attributes.put(IP_ADDRESS, servletRequest.getRemoteAddress());
				}
				return true;
			}

			@Override
			public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
					WebSocketHandler wsHandler, Exception exception) {

			}
		};
	}

	private DefaultHandshakeHandler defaultHandshakeHandler() {
		return new DefaultHandshakeHandler() {
			@Override
			protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
					Map<String, Object> attributes) {

				Principal p = super.determineUser(request, wsHandler, attributes);

				return p;
			}
		};
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {

		registration.interceptors(new ChannelInterceptorAdapter() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {

				StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

				System.out.println(
						"<<<<<<<<<<<<<<<<<<<<<<<<<<MessageHeaderAccessor.getAccessor<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
								+ accessor.getCommand());

				if (StompCommand.CONNECT.equals(accessor.getCommand())) {

					System.out.println(
							"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ENTER IN TO StompCommand.CONNECT >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

					System.out.println(
							" *********************************88NAtive Header**************************************88*"
									+ accessor.getFirstNativeHeader("X-Authorization"));

					String username = null;

					String accessToken = accessor.getFirstNativeHeader("X-Authorization");
					if (accessToken != null && accessToken.startsWith(TOKEN_PREFIX)) {
						accessToken = accessToken.replace(TOKEN_PREFIX, "");
						try {
							username = jwtTokenUtil.getUsernameFromToken(accessToken);
						} catch (IllegalArgumentException e) {
							System.out.println("an error occured during getting username from token" + e);
						} catch (ExpiredJwtException e) {
							System.out.println("the token is expired and not valid anymore" + e);
						} catch (SignatureException e) {
							System.out.println("Authentication Failed. Username or Password not valid.");
						}
					} else {
						System.out.println("couldn't find bearer string, will ignore the header");
					}

					System.out.println(
							"userrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrname"
									+ username);

					UserDetails userDetails = userDetailsService.loadUserByUsername(username);

					if (jwtTokenUtil.validateToken(accessToken, userDetails)) {

						UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthentication(accessToken,
								SecurityContextHolder.getContext().getAuthentication(), userDetails);
						System.out.println(
								"UsernamePasswordAuthenticationToken authentication " + authentication.toString());

						SecurityContextHolder.getContext().setAuthentication(authentication);

						accessor.setUser(authentication);
					}
				}

				return message;

			}

		});

	}

}
