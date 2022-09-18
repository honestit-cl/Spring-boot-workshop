package pl.coderslab.springbootworkshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private UserAuthenticationService authenticationService;

  @Autowired
  public void setAuthenticationService(UserAuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // Hasła na bazie w formacie: {encoderPrefix}password
    // np:
    // {bcrypt}980ug9jalkjtq09a8g0a9jtalgia90g8a09
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        // wybierana jest pierwsza pasująca konfiguracja od góry
        // zasada: od szczegółu do ogółu
        .antMatchers(HttpMethod.GET, "/api/**")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/api/**")
        .hasAnyRole("USER", "ADMIN")
        .antMatchers(HttpMethod.DELETE, "/api/**")
        .hasRole("ADMIN")
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .formLogin()
        .and()
        .csrf()
        .disable();
  }
}
