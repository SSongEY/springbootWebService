package com.yym.webservice.config;

import com.yym.webservice.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  CustomAuthenticationProvider authProvider;

  @Bean
  public UserDetailsService userDetailsService(){
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

    /*
     * User.withDefaultPasswordEncoder() 메소드는 deprecated 되어 PasswordEncoder를 따로 호출함.
     */
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    String password = encoder.encode("password");

    manager.createUser(User.withUsername("user")
            .password(password)
            .roles("ADMIN")
            .build());

    return manager;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/webjars/**", "/js/**","/h2-console/**")
            .permitAll()
            .anyRequest()
            .authenticated()
          .and()
            .headers()
            .frameOptions()
            .sameOrigin()	// for h2 console
          .and()
            .formLogin()
            .loginPage("/index")
            .loginProcessingUrl("/loginProc")
            .defaultSuccessUrl("/mapPage")
            .permitAll()
          .and()
            .logout()
            .logoutUrl("/logoutProc")
            .logoutSuccessUrl("/index")
            .permitAll();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider);
  }
}
