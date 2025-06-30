package com.pedroribeiro.helpaai.infra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private  CustomCorsConfiguration customCorsConfiguration;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> http
                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/tickets","/api/sectors","/api/categories").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.PUT,"/api/tickets").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST,"/api/tickets").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.POST,"/api/categories","/api/sectors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/categories","/api/sectors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/categories","/api/sectors").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/sectors/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/categories/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/users/me").authenticated()
                        .requestMatchers(HttpMethod.PUT,"/api/users/me").authenticated()
                        .anyRequest().authenticated()
                )
                .cors(c -> c.configurationSource(customCorsConfiguration))
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
