package com.unesco.core.services.dictionaryDataService;

import com.unesco.core.entities.*;
import com.unesco.core.entities.account.Role;
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
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
   private IMapperService mapperService;


   @Autowired
   private FieldOfKnowledgeRepository fieldOfKnowledgeRepository;

   DitionaryDataService() {

   }

   public List<InstituteModel> getInstitutes() {
      return genericList(instituteRepository);
   }
   public List<DepartmentModel> getDepartments() {
      return genericList(departmentRepository);
   }
   public List<GroupModel> getGroups() {
      return genericList(groupRepository);
   }
   public List<DisciplineModel> getDisciplines() {
      return genericList(disciplineRepository);
   }
   public List<UserModel> getUsers() {
      return genericList(userRepository);
   }
   public List<RoleModel> getRoles() {
      return genericList(roleRepository);
   }
   public List<FieldOfKnowledgeModel> getFieldOfKnowledges() {
      return genericList(fieldOfKnowledgeRepository);
   }

   public PageResult<InstituteModel> getInstitutePage(FilterQuery filter) {
      return genericPage(filter, instituteRepository);
   }
   public PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter) {
      return genericPage(filter, departmentRepository);
   }
   public PageResult<GroupModel> getGroupPage(FilterQuery filter) {
      return genericPage(filter, groupRepository);
   }
   public PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter) {
      return genericPage(filter, disciplineRepository);
   }
   public PageResult<UserModel> getUserPage(FilterQuery filter) {
      return genericPage(filter, userRepository);
   }
   public PageResult<RoleModel> getRolePage(FilterQuery filter) {
      return genericPage(filter, roleRepository);
   }
   public PageResult<FieldOfKnowledgeModel> getFieldOfKnowledgePage(FilterQuery filter) {
      return genericPage(filter, fieldOfKnowledgeRepository);
   }

   public String AddOrUpdateDiscipline(DisciplineModel discipline) {
         Discipline entity = (Discipline) mapperService.toEntity(discipline);
         entity.setDatecreate(new Date());
         entity.setFieldOfKnowledge(findInRepo(entity.getFieldOfKnowledge(), fieldOfKnowledgeRepository));

         try { disciplineRepository.save(entity); }
         catch (Exception e){ return JSONResponseStatus.ERROR; }

         return JSONResponseStatus.OK;
   }
   public String AddOrUpdateInstitute(InstituteModel institute) {
      Institute entity = (Institute) mapperService.toEntity(institute);

      try { instituteRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }

      return JSONResponseStatus.OK;
   }
   public String AddOrUpdateDepartment(DepartmentModel department) {
      Department entity = (Department) mapperService.toEntity(department);

      try { departmentRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }

      return JSONResponseStatus.OK;
   }
   public String AddOrUpdateGroup(GroupModel group) {
      Group entity = (Group) mapperService.toEntity(group);

      try { groupRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }

      return JSONResponseStatus.OK;
   }
   public String AddOrUpdateRole(RoleModel role) {
      Role entity = (Role) mapperService.toEntity(role);

      try { roleRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }

      return JSONResponseStatus.OK;
   }
   public String AddOrUpdateFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge) {
      FieldOfKnowledge entity = (FieldOfKnowledge) mapperService.toEntity(fieldOfKnowledge);

      try { fieldOfKnowledgeRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }

      return JSONResponseStatus.OK;
   }

   public String DeleteDiscipline(int id) {
      try { disciplineRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }
   public String DeleteInstitute(int id) {
      try { instituteRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }
   public String DeleteDepartment(int id) {
      try { departmentRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }
   public String DeleteGroup(int id) {
      try { groupRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }
   public String DeleteRole(int id) {
      try { roleRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }
   public String DeleteFieldOfKnowledge(int id) {
      try { fieldOfKnowledgeRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR; }
      return JSONResponseStatus.OK;
   }

   private <T, E> PageResult<T> genericPage(FilterQuery filter, CrudPagableRepository repository) {
      List<T> FieldOfKnowledgeViewModel= new ArrayList<>();
      int rows = filter.getRows()>0? filter.getRows() : (int) repository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      Page<E> page;
      try {
         page = repository.findAll(new PageRequest(start, rows));
         for (E entity: page.getContent()) {
            try{
               T model = (T) mapperService.toModel(entity);
               FieldOfKnowledgeViewModel.add(model);
            } catch (Exception e) { }
         }
      } catch (Exception e) {}

      PageResult<T> result = new PageResult<T>(FieldOfKnowledgeViewModel, repository.count());
      return result;
   }
   private <T, E> List<T> genericList(CrudPagableRepository repository) {
      List<T> result = new ArrayList<T>();
      for (Object entity : repository.findAll()) {
         try {
            T model = (T) mapperService.toModel(entity);
            result.add(model);
         } catch (Exception e){};
      }
      return result;
   }

   public <E extends LongId> E findInRepo(E model, CrudPagableRepository repository)
   {
      return (E) repository.findOne(model.getId());
   }

}















