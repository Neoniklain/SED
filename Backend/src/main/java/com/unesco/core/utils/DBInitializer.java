package com.unesco.core.utils;

import com.unesco.core.entities.account.RoleEntity;
import com.unesco.core.entities.account.UserEntity;
import com.unesco.core.entities.journal.PointTypeEntity;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DBInitializer implements ApplicationRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;
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
            _RoleRepository.save(new RoleEntity("ADMIN"));
        if(_RoleRepository.findByRole("STUDENT") == null)
            _RoleRepository.save(new RoleEntity("STUDENT"));
        if(_RoleRepository.findByRole("PROFESSOR") == null)
            _RoleRepository.save(new RoleEntity("PROFESSOR"));
        if(_RoleRepository.findByRole("ENGINEER") == null)
            _RoleRepository.save(new RoleEntity("ENGINEER"));
        if(_RoleRepository.findByRole("GUEST") == null)
            _RoleRepository.save(new RoleEntity("GUEST"));
        // Инициализация Тестового пользователя.
        if(_UserRepository.findByUsername("admin") == null)
        {
            Set<RoleEntity> roleEntity = new HashSet<RoleEntity>();
            RoleEntity userRoleEntity = _RoleRepository.findByRole("ADMIN");
            roleEntity.add(userRoleEntity);
            UserEntity testUserEntity = new UserEntity("admin","Администратор", "admin@mail.com", passwordEncoder.encode("12345"));
            testUserEntity.setRoleEntities(roleEntity);
            _UserRepository.save(testUserEntity);
        }
        // Инициализация Типов отметок
        if(_PointTypeRepository.findByName("Посещение") == null)
        {
            PointTypeEntity p = new PointTypeEntity();
            p.setName("Посещение");
            _PointTypeRepository.save(p);
        }
        // Инициализация раздела знаний
        if(_FieldOfKnowledgeRepository.findByName("Тестовый") == null)
        {
            FieldOfKnowledgeEntity t = new FieldOfKnowledgeEntity();
            t.setName("Тестовый");
            _FieldOfKnowledgeRepository.save(t);
        }
        InstituteEntity t = new InstituteEntity();
        // Инициализация InstituteEntity
        if(_InstituteRepository.findByName("Институт Фундаментальных наук") == null)
        {
            t = new InstituteEntity();
            t.setName("Институт Фундаментальных наук");
            _InstituteRepository.save(t);
        }
        DepartmentEntity d = new DepartmentEntity();
        // Инициализация InstituteEntity
        if(_DepartmentRepository.findByName("Юнеско") == null)
        {
            d = new DepartmentEntity();
            d.setName("Юнеско");
            d.setInstituteEntity(t);
            _DepartmentRepository.save(d);
        }
        GroupEntity g;
        if(_GroupRepository.findByName("М-178") == null)
        {
            g = new GroupEntity();
            g.setName("М-178");
            g.setDepartmentEntity(d);
            _GroupRepository.save(g);
        }
        // Инициализация PointTypeEntity
        PointTypeEntity p = new PointTypeEntity();
        if(_PointTypeRepository.findByName("Посещение") == null)
        {
            p.setName("Посещение");
            _PointTypeRepository.save(p);
        }
        PointTypeEntity p2 = new PointTypeEntity();
        if(_PointTypeRepository.findByName("Лабораторная") == null)
        {
            p2.setName("Лабораторная");
            _PointTypeRepository.save(p2);
        }
        PointTypeEntity p3 = new PointTypeEntity();
        if(_PointTypeRepository.findByName("Семестровая") == null)
        {
            p3.setName("Семестровая");
            _PointTypeRepository.save(p3);
        }
        PointTypeEntity p4 = new PointTypeEntity();
        if(_PointTypeRepository.findByName("Контрольная работа") == null)
        {
            p4.setName("Контрольная работа");
            _PointTypeRepository.save(p4);
        }
        PointTypeEntity p5 = new PointTypeEntity();
        if(_PointTypeRepository.findByName("Отчет") == null)
        {
            p5.setName("Отчет");
            _PointTypeRepository.save(p5);
        }


    }
}

















