package edu.opl.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("api/course/courses").permitAll()
                        .requestMatchers("api/instructor/instructors").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(
                                "api/admin/update",
                                "api/assignment/**",
                                "api/student/**",
                                "api/course/**",
                                "api/instructor/**"
                        ).hasRole("ADMIN")
                        .requestMatchers(
                                "api/assignment/byId/",
                                "api/assignment/assignments"
                        ).hasRole("STUDENT")
                        .requestMatchers(
                                "api/instructor/update",
                                "api/assignment/**"
                        ).hasRole("INSTRUCTOR")
                        .requestMatchers("/**").hasRole("MANAGER")
                        .anyRequest().authenticated()
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())
                ).sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
