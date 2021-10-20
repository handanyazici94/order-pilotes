package com.tui.proof.config;

import com.tui.proof.model.enm.Role;
import com.tui.proof.model.entity.User;
import com.tui.proof.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@Profile("dev")
public class UserInit {

    @Value("${spring.security.admin.username}")
    private String username;

    @Value("${spring.security.admin.password}")
    private String password;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void createTestUsers() {
        userRepository.findByUsername(username).orElseGet(()-> {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);

            return userRepository.save(user);
        });
    }
}
