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
        // Инициализация ролей
        if(_RoleRepository.findByRole("ADMIN") == null)
            _RoleRepository.save(new Role("ADMIN"));
        if(_RoleRepository.findByRole("MANAGER") == null)
            _RoleRepository.save(new Role("MANAGER"));
        if(_RoleRepository.findByRole("USER") == null)
            _RoleRepository.save(new Role("USER"));
        // Инициализация Тестового пользователя.
        if(_UserRepository.findByUsername("admin") == null)
        {
            Set<Role> role = new HashSet<Role>();
            Role userRole = _RoleRepository.findByRole("ADMIN");
            role.add(userRole);
            User testUser = new User("admin", "admin@mail.com", "12345");
            testUser.setRoles(role);
            _UserRepository.save(testUser);
        }
    }
}
