package com.tui.proof.service;

import com.tui.proof.model.entity.User;
import com.tui.proof.model.security.UserPrincipal;
import com.tui.proof.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(loadUser -> new UserPrincipal(loadUser))
            .orElseThrow(()-> new UsernameNotFoundException(String.format("Username[%s] not found")));
    }
}
