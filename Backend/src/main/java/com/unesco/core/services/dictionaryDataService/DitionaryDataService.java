package com.unesco.core.services.dictionaryDataService;

import com.unesco.core.entities.*;
import com.unesco.core.entities.account.Role;
import com.unesco.core.entities.account.User;
import com.unesco.core.entities.schedule.Room;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.enums.RoleType;
import com.unesco.core.repositories.RoomRepository;
import com.unesco.core.repositories.account.ProfessorRepository;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.plan.*;
import com.unesco.core.repositories.utils.CrudPagableRepository;
import com.unesco.core.services.mapperService.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
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
   @Autowired
   private RoomRepository roomRepository;
   @Autowired
   private ProfessorRepository professorRepository;

   DitionaryDataService() {

   }

   public InstituteModel getInstitute(int id) {
      return (InstituteModel) mapperService.toModel(instituteRepository.findOne((long) id));
   }
   public DepartmentModel getDepartment(int id) {
      return (DepartmentModel) mapperService.toModel(departmentRepository.findOne((long) id));
   }
   public GroupModel getGroup(int id) {
      return (GroupModel) mapperService.toModel(groupRepository.findOne((long) id));
   }
   public DisciplineModel getDiscipline(int id) {
      return (DisciplineModel) mapperService.toModel(disciplineRepository.findOne((long) id));
   }
   public UserModel getUser(int id) {
      return (UserModel) mapperService.toModel(userRepository.findOne((long) id));
   }
   public RoleModel getRole(int id) {
      return (RoleModel) mapperService.toModel(roleRepository.findOne((long) id));
   }
   public RoleModel getRole(RoleType roleType) {
      return (RoleModel) mapperService.toModel(roleRepository.findByRole(roleType.toString()));
   }
   public FieldOfKnowledgeModel getFieldOfKnowledges(int id) {
      return (FieldOfKnowledgeModel) mapperService.toModel(fieldOfKnowledgeRepository.findOne((long) id));
   }
   public RoomModel getRooms(int id) {
      return (RoomModel) mapperService.toModel(roomRepository.findOne((long) id));
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
   public List<RoomModel> getRooms() {
      return genericList(roomRepository);
   }

   public PageResult<InstituteModel> getInstitutePage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) instituteRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Institute> entitys = instituteRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<InstituteModel> list = new ArrayList<InstituteModel>();
      for (Institute e: entitys) {
         list.add((InstituteModel) mapperService.toModel(e));
      }
      PageResult<InstituteModel> result = new PageResult<InstituteModel>(list, list.size());
      return result;
   }
   public PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) departmentRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Department> entitys = departmentRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<DepartmentModel> list = new ArrayList<DepartmentModel>();
      for (Department e: entitys) {
         list.add((DepartmentModel) mapperService.toModel(e));
      }
      PageResult<DepartmentModel> result = new PageResult<DepartmentModel>(list, list.size());
      return result;
   }
   public PageResult<GroupModel> getGroupPage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) groupRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Group> entitys = groupRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<GroupModel> list = new ArrayList<GroupModel>();
      for (Group e: entitys) {
         list.add((GroupModel) mapperService.toModel(e));
      }
      PageResult<GroupModel> result = new PageResult<GroupModel>(list, list.size());
      return result;
   }
   public PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) disciplineRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Discipline> entitys = disciplineRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<DisciplineModel> list = new ArrayList<DisciplineModel>();
      for (Discipline e: entitys) {
         list.add((DisciplineModel) mapperService.toModel(e));
      }
      PageResult<DisciplineModel> result = new PageResult<DisciplineModel>(list, list.size());
      return result;
   }
   public PageResult<UserModel> getUserPage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) userRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<User> entitys = userRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<UserModel> list = new ArrayList<UserModel>();
      for (User e: entitys) {
         list.add((UserModel) mapperService.toModel(e));
      }
      PageResult<UserModel> result = new PageResult<UserModel>(list, list.size());
      return result;
   }
   public PageResult<RoleModel> getRolePage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) roleRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Role> entitys = roleRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<RoleModel> list = new ArrayList<RoleModel>();
      for (Role e: entitys) {
         list.add((RoleModel) mapperService.toModel(e));
      }
      PageResult<RoleModel> result = new PageResult<RoleModel>(list, list.size());
      return result;
   }
   public PageResult<FieldOfKnowledgeModel> getFieldOfKnowledgePage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) fieldOfKnowledgeRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<FieldOfKnowledge> entitys = fieldOfKnowledgeRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<FieldOfKnowledgeModel> list = new ArrayList<FieldOfKnowledgeModel>();
      for (FieldOfKnowledge e: entitys) {
         list.add((FieldOfKnowledgeModel) mapperService.toModel(e));
      }
      PageResult<FieldOfKnowledgeModel> result = new PageResult<FieldOfKnowledgeModel>(list, list.size());
      return result;
   }
   public PageResult<RoomModel> getRoomPage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) roomRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Room> entitys = roomRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<RoomModel> list = new ArrayList<RoomModel>();
      for (Room e: entitys) {
         list.add((RoomModel) mapperService.toModel(e));
      }
      PageResult<RoomModel> result = new PageResult<RoomModel>(list, list.size());
      return result;
   }
   public PageResult<ProfessorModel> getProfessorPage(FilterQuery filter) {
      int rows = filter.getRows()>0? filter.getRows() : (int) professorRepository.count();
      int start = rows>0 ? filter.getFirst()/rows: 1;
      List<Professor> entitys = professorRepository.findWithFilter(new PageRequest(start, rows), filter.getGlobalFilter());
      List<ProfessorModel> list = new ArrayList<ProfessorModel>();
      for (Professor e: entitys) {
         list.add((ProfessorModel) mapperService.toModel(e));
      }
      PageResult<ProfessorModel> result = new PageResult<ProfessorModel>(list, list.size());
      return result;
   }
   public PageResult<String> getWeekTypePage(FilterQuery filter) {
      List<String> weekTypeList = new ArrayList<>();
      weekTypeList.add("Все");
      weekTypeList.add("Нечет");
      weekTypeList.add("Чет");
      if(filter.getGlobalFilter()!="") {
         List<String> result = new ArrayList<>();
         for (String item : weekTypeList) {
            if(item.toLowerCase().contains(filter.getGlobalFilter().toLowerCase())) {
               result.add(item);
            }
         }
         weekTypeList = result;
      }
      PageResult<String> result = new PageResult<String>(weekTypeList, weekTypeList.size());
      return result;
   }
   public PageResult<String> getDayOfWeekPage(FilterQuery filter) {
      List<String> dayOfWeekModelList = new ArrayList<>();
      dayOfWeekModelList.add("Понедельник");
      dayOfWeekModelList.add("Вторник");
      dayOfWeekModelList.add("Среда");
      dayOfWeekModelList.add("Четверг");
      dayOfWeekModelList.add("Пятница");
      dayOfWeekModelList.add("Суббота");
      dayOfWeekModelList.add("Воскресенье");
      if(filter.getGlobalFilter()!="") {
         List<String> result = new ArrayList<>();
         for (String item : dayOfWeekModelList) {
            if(item.toLowerCase().contains(filter.getGlobalFilter().toLowerCase())) {
               result.add(item);
            }
         }
         dayOfWeekModelList = result;
      }
      PageResult<String> result = new PageResult<String>(dayOfWeekModelList, dayOfWeekModelList.size());
      return result;
   }

   public JSONResponseStatus AddOrUpdateDiscipline(DisciplineModel discipline) {
         Discipline entity = (Discipline) mapperService.toEntity(discipline);
         entity.setFieldOfKnowledge(findInRepo(entity.getFieldOfKnowledge(), fieldOfKnowledgeRepository));

         try { disciplineRepository.save(entity); }
         catch (Exception e){ return JSONResponseStatus.ERROR(); }

         return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateInstitute(InstituteModel institute) {
      Institute entity = (Institute) mapperService.toEntity(institute);

      try { instituteRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateDepartment(DepartmentModel department) {
      Department entity = (Department) mapperService.toEntity(department);

      try { departmentRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateGroup(GroupModel group) {
      Group entity = (Group) mapperService.toEntity(group);

      try { groupRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateRole(RoleModel role) {
      Role entity = (Role) mapperService.toEntity(role);

      try { roleRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge) {
      FieldOfKnowledge entity = (FieldOfKnowledge) mapperService.toEntity(fieldOfKnowledge);

      try { fieldOfKnowledgeRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus AddOrUpdateRoom(RoomModel room) {
      Room entity = (Room) mapperService.toEntity(room);

      try { roomRepository.save(entity); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }

      return JSONResponseStatus.OK();
   }

   public JSONResponseStatus DeleteDiscipline(int id) {
      try { disciplineRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteInstitute(int id) {
      try { instituteRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteDepartment(int id) {
      try { departmentRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteGroup(int id) {
      try { groupRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteRole(int id) {
      try { roleRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteFieldOfKnowledge(int id) {
      try { fieldOfKnowledgeRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
   }
   public JSONResponseStatus DeleteRoom(int id) {
      try { roomRepository.delete((long)id); }
      catch (Exception e){ return JSONResponseStatus.ERROR(); }
      return JSONResponseStatus.OK();
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















