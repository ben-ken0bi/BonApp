package fr.eni.bonapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (requests) ->
                                requests
                                        .requestMatchers("/css/**", "/images/**", "/js/**", "/external-images/**")
                                        .permitAll()
                                        .requestMatchers("/", "/accueil","/inscription")
                                        .permitAll()
                                        .requestMatchers("/recettes", "/recettes/**")
                                        .permitAll()
                                        .requestMatchers("/recette", "/recette/**")
                                        .permitAll()
                                        .requestMatchers("/admin/**")
                                        .hasRole("ADMIN")
                                        .anyRequest()
                                        .authenticated())
                .exceptionHandling(
                        (exceptionHandling) -> exceptionHandling.accessDeniedPage("/access-denied"))
                .formLogin((form) -> form.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout(
                        (logout) ->
                                logout
                                        .logoutUrl("/logout")
                                        .logoutSuccessUrl("/login?logout")
                                        .invalidateHttpSession(true)
                                        .deleteCookies("JSESSIONID")
                                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //TODO à changer quand on utilisera des mdp pas en clair
        return NoOpPasswordEncoder.getInstance();
    }
}
