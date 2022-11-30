package com.ask.boot.config;

import com.ask.boot.user.User;
import com.ask.boot.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // @EnableGlobalMethodSecurity 에서 변경됨
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers
            .frameOptions(FrameOptionsConfig::sameOrigin))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user/**").hasRole("USER")
            .anyRequest().authenticated())
        .formLogin(Customizer.withDefaults())
        .build();
  }

  @Bean
  public UserDetailsService userDetailsService(UserRepository userRepository) {
    return username -> {
      User user = userRepository.findByName(username)
          .orElseThrow(() -> new UsernameNotFoundException("not found, id: " + username));
      return org.springframework.security.core.userdetails.User.builder()
          .username(user.getName())
          .password(user.getPassword())
          .roles("USER")
          .build();
    };
  }

}
