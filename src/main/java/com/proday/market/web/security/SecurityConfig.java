package com.proday.market.web.security;

import com.proday.market.domain.service.SpringUserDetailsService;

import com.proday.market.web.security.filter.JwtFilterRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Ruben Malaga
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringUserDetailsService userDetailService;
    @Autowired
    private JwtFilterRequest jwtFilterRequest;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
            .antMatchers("/**/authenticate").permitAll()
            .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        ;

        http.addFilterBefore(
            jwtFilterRequest, UsernamePasswordAuthenticationFilter.class
        );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Para seguir visualizando swagger
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/v2/api-docs"
            , "/configuration/ui"
            , "/swagger-resources/**"
            , "/configuration/security"
            , "/swagger-ui.html"
            , "/webjars/**"
        );
    }
}
