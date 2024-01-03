package fr.eni.bonapp.configuration;

import fr.eni.bonapp.bll.UtilisateurService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

  private UtilisateurService utilisateurService;

  public WebSecurityConfig(UtilisateurService utilisateurService) {
    this.utilisateurService = utilisateurService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/css/**", "/images/**", "/js/**", "/external-images/**")
                    .permitAll()
                    .requestMatchers("/", "/accueil")
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
        .formLogin((form) -> form.loginPage("/login").permitAll())
        .userDetailsService(utilisateurService)
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
    return new BCryptPasswordEncoder();
  }
} // END
