package com.maximaLibri.maximaLibriV2;

import com.maximaLibri.maximaLibriV2.model.RoleName;
import com.maximaLibri.maximaLibriV2.model.User;
import com.maximaLibri.maximaLibriV2.repository.RoleRepository;
import com.maximaLibri.maximaLibriV2.repository.UserRepository;
import com.maximaLibri.maximaLibriV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AddAdmin {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void AddAdmin() {
        User admin = userService.findByEmail("alexa.murgoci@gmail.com");
        if(admin==null) {
            admin = new User();
            admin.setUsername("admin");
            admin.setEmail("alexa.murgoci@gmail.com");
            admin.setPassword(userService.encodePassword("secret"));
            admin.setEnabled(true);
            admin.setRoles(Collections.singletonList(roleRepository.findByName(RoleName.ROLE_ADMIN)));
            userRepository.save(admin);
        }
    }
}
