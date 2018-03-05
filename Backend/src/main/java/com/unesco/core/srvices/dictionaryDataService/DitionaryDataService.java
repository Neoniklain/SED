package com.unesco.core.srvices.dictionaryDataService;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.Group;
import com.unesco.core.entities.Institute;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.models.DepartmentModel;
import com.unesco.core.models.DisciplineModel;
import com.unesco.core.models.GroupModel;
import com.unesco.core.models.InstituteModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.plan.DepartmentRepository;
import com.unesco.core.repositories.plan.DisciplineRepository;
import com.unesco.core.repositories.plan.GroupRepository;
import com.unesco.core.repositories.plan.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

@Service
public class DitionaryDataService implements IDitionaryDataService {

   @Autowired
   private InstituteRepository instituteRepository;
   @Autowired
   private DepartmentRepository departmentRepository;
   @Autowired
   private GroupRepository groupRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private DisciplineRepository disciplineRepository;
   @Autowired
   private RoleRepository roleRepository;

   DitionaryDataService() {

   }

   public List<InstituteModel> getInstitutes() {
      List<InstituteModel> institutes = new ArrayList<InstituteModel>();
      for (Institute institute : instituteRepository.findAll()) {
         institutes.add(new InstituteModel(institute));
      }
      return institutes;
   }
   public List<DepartmentModel> getDepartments() {
      List<DepartmentModel> departments = new ArrayList<DepartmentModel>();
      for (Department department : departmentRepository.findAll()) {
         departments.add(new DepartmentModel(department));
      }
      return departments;
   }
   public List<GroupModel> getGroups() {
      List<GroupModel> groups = new ArrayList<GroupModel>();
      for (Group group : groupRepository.findAll()) {
         groups.add(new GroupModel(group));
      }
      return groups;
   }
   public List<DisciplineModel> getDisciplines() {
      List<DisciplineModel> groups = new ArrayList<DisciplineModel>();
      for (Discipline discipline : disciplineRepository.findAll()) {
         groups.add(new DisciplineModel(discipline));
      }
      return groups;
   }
   public List<UserModel> getUsers() {
      List<UserModel> users = new ArrayList<UserModel>();
      for (User user : userRepository.findAll()) {
         users.add(new UserModel(user));
      }
      return users;
   }
   public List<RoleModel> getRoles() {
      List<RoleModel> users = new ArrayList<RoleModel>();
      for (Role role : roleRepository.findAll()) {
         users.add(new RoleModel(role));
      }
      return users;
   }

   public PageResult<InstituteModel> getInstitutePage(FilterQuery filter) {
      List<InstituteModel> instituteModel = new ArrayList<>();
      int rows = filter.getRows() > 0 ? filter.getRows() : 10;
      Page<Institute> page = instituteRepository.findAll(new PageRequest(filter.getFirst() / rows, rows));
      for (Institute institute : page.getContent()) {
         instituteModel.add(new InstituteModel(institute));
      }
      PageResult<InstituteModel> result = new PageResult<InstituteModel>(instituteModel, instituteRepository.count());
      return result;
   }
   public PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter) {
      List<DepartmentModel> departmentModel = new ArrayList<>();
      int rows = filter.getRows() > 0 ? filter.getRows() : 10;
      Page<Department> page = departmentRepository.findAll(new PageRequest(filter.getFirst() / rows, rows));
      for (Department Department : page.getContent()) {
         departmentModel.add(new DepartmentModel(Department));
      }
      PageResult<DepartmentModel> result = new PageResult<DepartmentModel>(departmentModel, departmentRepository.count());
      return result;
   }
   public PageResult<GroupModel> getGroupPage(FilterQuery filter) {
      List<GroupModel> groupModel = new ArrayList<>();
      int rows = filter.getRows() > 0 ? filter.getRows() : 10;
      Page<Group> page = groupRepository.findAll(new PageRequest(filter.getFirst() / rows, rows));
      for (Group group : page.getContent()) {
         groupModel.add(new GroupModel(group));
      }
      PageResult<GroupModel> result = new PageResult<GroupModel>(groupModel, groupRepository.count());
      return result;
   }
   public PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter) {
      List<DisciplineModel> disciplineModel = new ArrayList<>();
      int rows = filter.getRows() > 0 ? filter.getRows() : 10;
      Page<Discipline> page = disciplineRepository.findAll(new PageRequest(filter.getFirst() / rows, rows));
         for(
      Discipline d:page.getContent())

      {
         disciplineModel.add(new DisciplineModel(d));
      }

      PageResult<DisciplineModel> result = new PageResult<DisciplineModel>(disciplineModel, disciplineRepository.count());
         return result;
   }
   public PageResult<UserModel> getUserPage(FilterQuery filter) {
      List<UserModel> usersViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<User> page = userRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (User u: page.getContent()) {
         usersViewModel.add(new UserModel(u));
      }
      PageResult<UserModel> result = new PageResult<UserModel>(usersViewModel, userRepository.count());
      return result;
   }
   public PageResult<RoleModel> getRolePage(FilterQuery filter) {
      List<RoleModel> rolesViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : 10;
      Page<Role> page = roleRepository.findAll(new PageRequest(filter.getFirst()/rows, rows));
      for (Role r: page.getContent()) {
         rolesViewModel.add(new RoleModel(r));
      }
      PageResult<RoleModel> result = new PageResult<RoleModel>(rolesViewModel, roleRepository.count());
      return result;
   }

}















