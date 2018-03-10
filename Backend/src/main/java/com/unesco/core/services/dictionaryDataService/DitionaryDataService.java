package com.unesco.core.services.dictionaryDataService;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.additional.EntityModel;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.plan.*;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
   @Autowired
   private FieldOfKnowledgeRepository fieldOfKnowledgeRepository;

   DitionaryDataService() {

   }

   public List<InstituteModel> getInstitutes() {
      return genericList(InstituteModel.class, instituteRepository);
   }
   public List<DepartmentModel> getDepartments() {
      return genericList(DepartmentModel.class, departmentRepository);
   }
   public List<GroupModel> getGroups() {
      return genericList(GroupModel.class, groupRepository);
   }
   public List<DisciplineModel> getDisciplines() {
      return genericList(DisciplineModel.class, disciplineRepository);
   }
   public List<UserModel> getUsers() {
      return genericList(UserModel.class, userRepository);
   }
   public List<RoleModel> getRoles() {
      return genericList(RoleModel.class, roleRepository);
   }
   public List<FieldOfKnowledgeModel> getFieldOfKnowledges() {
      return genericList(FieldOfKnowledgeModel.class, fieldOfKnowledgeRepository);
   }

   public PageResult<InstituteModel> getInstitutePage(FilterQuery filter) {
      return genericPage(InstituteModel.class, filter, instituteRepository);
   }
   public PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter) {
      return genericPage(DepartmentModel.class, filter, departmentRepository);
   }
   public PageResult<GroupModel> getGroupPage(FilterQuery filter) {
      return genericPage(GroupModel.class, filter, groupRepository);
   }
   public PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter) {
      return genericPage(DisciplineModel.class, filter, disciplineRepository);
   }
   public PageResult<UserModel> getUserPage(FilterQuery filter) {
      return genericPage(UserModel.class, filter, userRepository);
   }
   public PageResult<RoleModel> getRolePage(FilterQuery filter) {
      return genericPage(RoleModel.class, filter, roleRepository);
   }
   public PageResult<FieldOfKnowledgeModel> getFieldOfKnowledgePage(FilterQuery filter) {
      return genericPage(FieldOfKnowledgeModel.class, filter, fieldOfKnowledgeRepository);
   }

   public String AddOrUpdateDiscipline(DisciplineModel discipline) {
      try {
         Discipline disciplineCreate = new Discipline();
         disciplineCreate.setName(discipline.getName());
         disciplineCreate.setDatecreate(discipline.getDatecreate());
         if(discipline.getFieldOfKnowledge().getName() != ""){
            FieldOfKnowledge fieldOfKnowledge = fieldOfKnowledgeRepository.findByName(discipline.getFieldOfKnowledge().getName());
            disciplineCreate.setFieldOfKnowledge(fieldOfKnowledge);
         }
         if(discipline.getId()!=0) {
            disciplineCreate.setId(discipline.getId());
         }
         disciplineRepository.save(disciplineCreate);
      }
      catch (Exception e){
         return JSONResponseStatus.ERROR;
      }
      return JSONResponseStatus.OK;
   }

   public String DeleteDiscipline(int id) {
      try {
         disciplineRepository.delete((long)id);
      }
      catch (Exception e){
         return JSONResponseStatus.ERROR;
      }
      return JSONResponseStatus.OK;
   }

   private <T extends EntityModel, E> PageResult<T> genericPage(Class<T> c, FilterQuery filter, CrudPagableRepository repository) {
      List<T> FieldOfKnowledgeViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : (int) repository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      Page<E> page = repository.findAll(new PageRequest(start, rows));
      for (E entity: page.getContent()) {
         try{
            T model = c.newInstance();
            model.EntityToModel(entity);
            FieldOfKnowledgeViewModel.add(model);
         } catch (Exception e) { }
      }
      PageResult<T> result = new PageResult<T>(FieldOfKnowledgeViewModel, repository.count());
      return result;
   }
   private <T extends EntityModel, E> List<T> genericList(Class<T> c, CrudPagableRepository repository) {
      List<T> result = new ArrayList<T>();
      for (Object entity : repository.findAll()) {
         try {
            T model = c.newInstance();
            model.EntityToModel(entity);
            result.add(model);
         } catch (Exception e){};
      }
      return result;
   }

}















