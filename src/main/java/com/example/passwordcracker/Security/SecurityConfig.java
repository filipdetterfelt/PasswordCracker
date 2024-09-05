package com.example.passwordcracker.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.SecurityFilterChain;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {return new UserDetailServiceIMPL();}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/", "/css/**", "/js/**", "/index" ,"/oauth2/**").permitAll()
                        .requestMatchers("/users","/cracker", "/success").hasAnyAuthority("Admin","Client")
                        .anyRequest().permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")
                        .defaultSuccessUrl("/cracker", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userAuthoritiesMapper(userAuthoritiesMapper()))
                )

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/cracker", true)
                        .failureUrl("/index?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutSuccessUrl("/")
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .csrf(AbstractHttpConfigurer::disable);
                return http.build();

    }



    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return authorities -> {
            List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
            authorities.forEach(authority -> {

                if (authority instanceof OAuth2UserAuthority oAuth2UserAuthority) {
                    System.out.println("In");
                    Map<String, Object> userAttributes = oAuth2UserAuthority.getAttributes();

                    if(userAttributes.containsKey("login")) {
                        Object loginObj = userAttributes.get("login");
                        if (loginObj != null) {
                            String login = userAttributes.get("login").toString();
                            if (login.equals("filipdetterfelt")) {
                                authoritiesList.add(new SimpleGrantedAuthority("Admin"));
                            }
                        }
                    }



                    else if (userAttributes.containsKey("email")){
                        Object emailObj = userAttributes.get("email");
                        if(emailObj != null) {
                            String email = userAttributes.get("email").toString();
                            if (email.equals("fillip.detterfelt@gmail.com")) {
                                authoritiesList.add(new SimpleGrantedAuthority("Admin"));
                            }
                        }
                    }



                }
            });
            return authoritiesList;
        };
    }
}
