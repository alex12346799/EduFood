package com.example.edufood.config;

import com.example.edufood.utils.RedirectHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final RedirectHelper redirectHelper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .successHandler((request, response, authentication) -> {
                            redirectHelper.redirectByRole(authentication.getAuthorities(), response);
                        })
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .authorizeHttpRequests(
                        authorize -> authorize
//                                .requestMatchers(HttpMethod.GET, "/vacancies").hasAnyAuthority("EMPLOYEE", "APPLICANT")
//                                .requestMatchers("/vacancies/create").hasAuthority("EMPLOYEE")
//                                .requestMatchers(HttpMethod.GET, "/resumes").hasAnyAuthority("EMPLOYEE", "APPLICANT")
//                                .requestMatchers("/resumes/create").hasAuthority("APPLICANT")
                                .anyRequest().permitAll()
                );
        return http.build();
    }
}
