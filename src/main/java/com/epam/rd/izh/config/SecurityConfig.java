package com.epam.rd.izh.config;

import com.epam.rd.izh.service.UserDetailsServiceMapper;
import com.epam.rd.izh.util.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceMapper userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/registration").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/new_tour").hasRole(Role.ADMIN.name())
                .antMatchers("/tours/edit_tour").hasRole(Role.ADMIN.name())
                .antMatchers("/tours/delete/**").hasRole(Role.ADMIN.name())
                .antMatchers("/trips").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/trips/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/new_trip").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/new_trip/**").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/trips/edit").hasAnyRole(Role.ADMIN.name())
                .antMatchers("/trips/delete/**").hasAnyRole(Role.ADMIN.name())

                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()

                .anyRequest().authenticated()
                .and()

                .httpBasic()
                .and()

                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
                .usernameParameter("login")
                .passwordParameter("password")

                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")


                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) {
        authentication.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
