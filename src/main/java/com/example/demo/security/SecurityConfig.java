package com.example.demo.security;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}123").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}123").roles("ADMIN","USER");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select login as principal,password as credentials ,active as enabled from users where login=?")
                .authoritiesByUsernameQuery("select login as pricipal , role from users_roles where login=?")
                .rolePrefix("ROLE_");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/user/403");

     }
}
