package ro.adela.proiect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                    try {
                        authz
                                .anyRequest().permitAll()
                                .and()
                                .formLogin()
                                .usernameParameter("username")
                                .defaultSuccessUrl("/dogs/")
                                .permitAll()
                                .and()
                                .logout().logoutSuccessUrl("/login").permitAll();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                })
                .csrf().disable();
        // .httpBasic(withDefaults());
        return http.build();
    }
}
