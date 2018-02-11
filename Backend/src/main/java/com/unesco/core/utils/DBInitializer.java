package com.unesco.core.utils;

import com.unesco.core.entities.Role;
import com.unesco.core.entities.User;
import com.unesco.core.repositories.RoleRepository;
import com.unesco.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitializer implements ApplicationRunner {

    @Autowired
    private UserRepository _UserRepository;
    @Autowired
    private RoleRepository _RoleRepository;

    public void run(ApplicationArguments args) {
        // Инициализация Тестового пользователя.
        User findUser = _UserRepository.findByUsername("test");
        if(findUser == null)
        {
            Set<Role> role = new HashSet<Role>();
            Role userRole = _RoleRepository.findByRole("ADMIN");
            role.add(userRole);
            User testUser = new User("test", "test@mail.com", "12345");
            testUser.setRoles(role);
            _UserRepository.save(testUser);
        }
    }
}
