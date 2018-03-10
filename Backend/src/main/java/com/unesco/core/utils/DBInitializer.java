package com.unesco.core.utils;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
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
        if(_RoleRepository.findByRole("STUDENT") == null)
            _RoleRepository.save(new Role("STUDENT"));
        if(_RoleRepository.findByRole("PROFESSOR") == null)
            _RoleRepository.save(new Role("PROFESSOR"));
        if(_RoleRepository.findByRole("ENGINEER") == null)
            _RoleRepository.save(new Role("ENGINEER"));
        // Инициализация Тестового пользователя.
        if(_UserRepository.findByUsername("admin") == null)
        {
            Set<Role> role = new HashSet<Role>();
            Role userRole = _RoleRepository.findByRole("ADMIN");
            role.add(userRole);
            User testUser = new User("admin","Администратор", "admin@mail.com", "12345");
            testUser.setRoles(role);
            _UserRepository.save(testUser);
        }
    }
}
