package com.dev.fullstackdemo;

import com.dev.fullstackdemo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // We are using this while we build the frontend, no security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth ->
                auth.anyRequest().permitAll())
                .cors(cors -> corsConfigurationSource());
        return httpSecurity.build();
    }

/*    //We are keeping this as our final bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests.requestMatchers("/api/**").hasRole("ADMIN")
//                        .requestMatchers("/v1/**").hasRole("USER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .securityMatchers(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider());
        return http.build();
    }*/


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public UserDetailsService userService() {
        return new UserServiceImpl();
    }

    //TODO: create a custom JWT token based authentication filter to validate the JWT token
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost:3000"));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.applyPermitDefaultValues();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000/"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Requestor-Type"));
//        configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin:*", "Access-Control-Allow-Credentials:*", "X-Get-Header"));
//        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
