package com.fernando.zeus.zeusapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("fernando").password("1234").roles("CLIENTE");
    }

    //protected void configure(HttpSecurity http){
    //    http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
    //}
}
