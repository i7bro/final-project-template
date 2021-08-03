package com.epam.rd.izh.service;

import com.epam.rd.izh.dao.impl.UserDaoImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceMapper implements UserDetailsService {

    private final UserDaoImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.epam.rd.izh.entity.User authorizedUser = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("No user with this login"));

        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + authorizedUser.getRole().name()));

        return new User(
                authorizedUser.getLogin(),
                authorizedUser.getPassword(),
                roles
        );
    }

}
