package com.epam.rd.izh.config;

import com.epam.rd.izh.entity.User;
import com.epam.rd.izh.service.UserDetailsServiceMapper;
import com.epam.rd.izh.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceMapper userDetailsService;

    /**
     * configure методы определяют настройку Spring Security.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/registration").permitAll()
                .antMatchers("/registration/**").permitAll()
//                .antMatchers("/tours/edit_tour").hasRole(Role.ADMIN.name())
//                .antMatchers("/tours/delete/*").hasRole(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/new_tour/").hasRole(Role.ADMIN.name())


                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/img/**").permitAll()

                .anyRequest().authenticated()
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
//
//                /**
//                 * Включение функции выхода из текущей сессии.
//                 */
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) {
        authentication.authenticationProvider(authProvider());
    }

    /**
     * Класс, обеспечивающий механизм авторизации. Принимает в себя реализацию сервиса авторизации UserDetailsService
     * и механизм шифрования пароля (реализацию PasswordEncoder).
     * Итоговый бин DaoAuthenticationProvider добавляется в контекст приложения и обеспечивает основную
     * логику Spring Security.
     */
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Механизм шифрования пароля, реализующий интерфейс PasswordEncoder. В данном примере использован
     * BCryptPasswordEncoder, можно написать свою реализацию, создав собственный класс шифрования.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
