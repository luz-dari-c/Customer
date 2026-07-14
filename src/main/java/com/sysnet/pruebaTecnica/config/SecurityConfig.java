package com.sysnet.pruebaTecnica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails usuario = User.builder().username("administrador").password(passwordEncoder.encode("admi123")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(usuario);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers("/static/css/**", "/static/js/**", "/img/**").permitAll().requestMatchers("/login").permitAll().anyRequest().authenticated()).formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/dashboard", true).permitAll()).logout(logout -> logout.logoutSuccessUrl("/login?logout")
.permitAll());
        return http.build();
    }
}