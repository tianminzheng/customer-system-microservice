package org.geekbang.projects.cs.security.service;

import org.geekbang.projects.cs.security.domain.User;
import org.geekbang.projects.cs.security.model.CustomUserDetails;
import org.geekbang.projects.cs.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException("Username" + username + "is invalid!");

        User u = userRepository.findUserByUsername(username).orElseThrow(s);

        return new CustomUserDetails(u);
    }
}
