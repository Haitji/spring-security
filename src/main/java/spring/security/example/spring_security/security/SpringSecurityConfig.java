package spring.security.example.spring_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)//For enable the annotation @PreAuthorize
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http.authorizeHttpRequests(re->re.requestMatchers(HttpMethod.GET,"/products").authenticated()//You need authenticate for view the products
        .requestMatchers(HttpMethod.POST,"/users/register").permitAll()//You can register without authenticate 
        .anyRequest().authenticated())//for the another method we going to use annotations in the productController
        .csrf(c->c.disable())
        .sessionManagement(mana->mana.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtUtil))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))
        .build();
    }

}
