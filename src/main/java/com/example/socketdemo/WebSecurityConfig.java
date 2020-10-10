package com.example.socketdemo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/**
 * 
 * @author Abdul Rafeek k s <abdul.rafeek@ndimension.com>
08-Jun-2020
TODO
 *
 */
//in order to use method level security, we need to enable this in the security configuration using @EnableGlobalMethodSecurity:
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
 //   Spring Security automatically prefixes any role with ROLE_.

   // The hasRole expression is used here to check if the currently authenticated principal has the specified authority.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().headers()
        .frameOptions()
        .disable().and().
                authorizeRequests()
                // allow swagger resources
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/v2/api-docs",           // swagger
                        "/webjars/**",            // swagger-ui webjars
                        "/swagger-resources/**",  // swagger-ui resources
                        "/configuration/**",      // swagger configuration
                        "/swagger-ui.html",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()



            


                 
                .antMatchers("/api/users/signup","/api/token/generate-token","/api/token/social-login","/api/token/social-signup","/api/users/save-password","/api/users/forgot-password","/api/download-file/{fileName:.+}","/ws/**","/ws/info","/ws/info/**").permitAll()
               
              //delete me after testing
                //,"/app/chat.sendMessage","/app/chat.addUser","/ws/**","/ws/info","/ws/info/**"
               //  .antMatchers("/api/users/signup","/api/token/generate-token","/api/token/social-login","/api/users/save-password","/api/users/forgot-password","/api/users","/api/roles","/api/lessons","/api/courses","/api/lesson-plans/*","/api/classes","/api/teachers/courses").permitAll()
             //   .antMatchers("/api/users/signup","/api/token/generate-token","/api/token/social-login","/api/users/save-password","/api/users/forgot-password","/api/users","/api/roles").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
