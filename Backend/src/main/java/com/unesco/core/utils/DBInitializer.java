package com.unesco.core.utils;

import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.entities.journal.PointType;
import com.unesco.core.entities.schedule.*;
import com.unesco.core.repositories.RoomRepository;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.journal.LessonEventRepository;
import com.unesco.core.repositories.journal.PointTypeRepository;
import com.unesco.core.repositories.plan.DepartmentRepository;
import com.unesco.core.repositories.plan.FieldOfKnowledgeRepository;
import com.unesco.core.repositories.plan.GroupRepository;
import com.unesco.core.repositories.plan.InstituteRepository;
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
    @Autowired
    private RoomRepository _RoomRepository;
    @Autowired
    private GroupRepository _GroupRepository;
    @Autowired
    private FieldOfKnowledgeRepository _FieldOfKnowledgeRepository;
    @Autowired
    private InstituteRepository _InstituteRepository;
    @Autowired
    private DepartmentRepository _DepartmentRepository;
    @Autowired
    private PointTypeRepository _PointTypeRepository;
    @Autowired
    private LessonEventRepository _LessonEventRepository;

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
        if(_RoleRepository.findByRole("GUEST") == null)
            _RoleRepository.save(new Role("GUEST"));
        // Инициализация Тестового пользователя.
        if(_UserRepository.findByUsername("admin") == null)
        {
            Set<Role> role = new HashSet<Role>();
            Role userRole = _RoleRepository.findByRole("ADMIN");
            role.add(userRole);
            User testUser = new User("admin","Администратор", "admin@mail.com", "$2a$10$Z3MfoVmt1CNwETyjTEtL9.MTbmc8AuX.c3rpBh097D6rSZ64v3rD.");
            testUser.setRoles(role);
            _UserRepository.save(testUser);
        }
        // Инициализация Типов отметок
        if(_PointTypeRepository.findByName("Посещение") == null)
        {
            PointType p = new PointType();
            p.setName("Посещение");
            _PointTypeRepository.save(p);
        }
        // Инициализация раздела знаний
        if(_FieldOfKnowledgeRepository.findByName("Тестовый") == null)
        {
            FieldOfKnowledge t = new FieldOfKnowledge();
            t.setName("Тестовый");
            _FieldOfKnowledgeRepository.save(t);
        }
        Institute t = new Institute();
        // Инициализация Institute
        if(_InstituteRepository.findByName("Институт Фундаментальных наук") == null)
        {
            t = new Institute();
            t.setName("Институт Фундаментальных наук");
            _InstituteRepository.save(t);
        }
        Department d = new Department();
        // Инициализация Institute
        if(_DepartmentRepository.findByName("Юнеско") == null)
        {
            d = new Department();
            d.setName("Юнеско");
            d.setInstitute(t);
            _DepartmentRepository.save(d);
        }
        Group g;
        if(_GroupRepository.findByName("М-178") == null)
        {
            g = new Group();
            g.setName("М-178");
            g.setDepartment(d);
            _GroupRepository.save(g);
        }
        // Инициализация PointType
        PointType p = new PointType();
        if(_PointTypeRepository.findByName("Посещение") == null)
        {
            p.setName("Посещение");
            _PointTypeRepository.save(p);
        }
        PointType p2 = new PointType();
        if(_PointTypeRepository.findByName("Лабораторная") == null)
        {
            p2.setName("Лабораторная");
            _PointTypeRepository.save(p2);
        }
        PointType p3 = new PointType();
        if(_PointTypeRepository.findByName("Семестровая") == null)
        {
            p3.setName("Семестровая");
            _PointTypeRepository.save(p3);
        }
        PointType p4 = new PointType();
        if(_PointTypeRepository.findByName("Контрольная работа") == null)
        {
            p4.setName("Контрольная работа");
            _PointTypeRepository.save(p4);
        }
        PointType p5 = new PointType();
        if(_PointTypeRepository.findByName("Отчет") == null)
        {
            p5.setName("Отчет");
            _PointTypeRepository.save(p5);
        }


    }
}

















