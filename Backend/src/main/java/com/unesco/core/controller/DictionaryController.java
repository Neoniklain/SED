package com.unesco.core.controller;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.userManager.interfaces.userList.IUserListManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.departmentList.IDepartmentListManager;
import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.managers.schedule.disciplineManager.interfaces.disciplineList.IDisciplineListManager;
import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledge.IFieldOfKnowledgeManager;
import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledgeList.IFieldOfKnowledgeListManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.groupList.IGroupListManager;
import com.unesco.core.managers.schedule.instituteManager.interfaces.institute.IInstituteManager;
import com.unesco.core.managers.schedule.instituteManager.interfaces.instituteList.IInstituteListManager;
import com.unesco.core.managers.schedule.roomManager.interfaces.room.IRoomManager;
import com.unesco.core.managers.schedule.roomManager.interfaces.roomList.IRoomListManager;
import com.unesco.core.services.dataService.account.professorService.IProfessorDataService;
import com.unesco.core.services.dataService.account.roleService.IRoleDataService;
import com.unesco.core.services.dataService.account.userService.IUserDataService;
import com.unesco.core.services.dataService.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.dataService.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.dataService.schedule.disciplineService.IDisciplineDataService;
import com.unesco.core.services.dataService.schedule.fieldOfKnowledgeService.IFieldOfKnowledgeDataService;
import com.unesco.core.services.dataService.schedule.groupService.IGroupDataService;
import com.unesco.core.services.dataService.schedule.instituteService.IInstituteDataService;
import com.unesco.core.services.dataService.schedule.pairTypeService.IPairTypeDataService;
import com.unesco.core.services.dataService.schedule.roomService.IRoomDataService;
import com.unesco.core.utils.StatusTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryController {

   @Autowired
   private IDisciplineDataService disciplineDataService;
   @Autowired
   private IUserDataService userDataService;
   @Autowired
   private IInstituteDataService instituteDataService;
   @Autowired
   private IDepartmentDataService departmentDataService;
   @Autowired
   private IGroupDataService groupDataService;
   @Autowired
   private IRoleDataService roleDataService;
   @Autowired
   private IFieldOfKnowledgeDataService fieldOfKnowledgeDataService;
   @Autowired
   private IRoomDataService roomDataService;
   @Autowired
   private IProfessorDataService professorDataService;
   @Autowired
   private IPointTypeDataService pointTypeDataService;
   @Autowired
   private IPairTypeDataService pairTypeDataService;

   @Autowired
   private IDisciplineManager disciplineManager;
   @Autowired
   private IDisciplineListManager disciplineListManager;

   @Autowired
   private IUserListManager userListManager;

   @Autowired
   private IInstituteManager instituteManager;
   @Autowired
   private IInstituteListManager instituteListManager;

   @Autowired
   private IDepartmentManager departmentManager;
   @Autowired
   private IDepartmentListManager departmentListManager;

   @Autowired
   private IGroupManager groupManager;
   @Autowired
   private IGroupListManager groupListManager;

   @Autowired
   private IRoleManager roleManager;
   @Autowired
   private IRoleListManager roleListManager;

   @Autowired
   private IFieldOfKnowledgeManager fieldOfKnowledgeManager;
   @Autowired
   private IFieldOfKnowledgeListManager fieldOfKnowledgeListManager;

   @Autowired
   private IRoomManager roomManager;
   @Autowired
   private IRoomListManager roomListManager;
   @Autowired
   private IProfessorListManager professorListManager;

   public PageResultDTO<ProfessorDTO> getProfessorList(FilterQueryDTO filter) {
      professorListManager.init(professorDataService.getPage(filter));
      PageResultDTO<ProfessorDTO> result = new PageResultDTO(professorListManager.getAll(), professorListManager.getAll().size());
      return result;
   }
   public PageResultDTO<UserDTO> getUserList(FilterQueryDTO filter) {
      userListManager.init(userDataService.getPage(filter));
      PageResultDTO<UserDTO> result = new PageResultDTO(userListManager.getAll(), userListManager.getAll().size());
      return result;
   }

   public PageResultDTO<RoomDTO> getRoomList(FilterQueryDTO filter) {
      roomListManager.init(roomDataService.getPage(filter));
      PageResultDTO<RoomDTO> result = new PageResultDTO(roomListManager.getAll(), roomListManager.getAll().size());
      return result;
   }
   public ResponseStatusDTO addRoom(RoomDTO room) {
      roomManager.init(room);
      ResponseStatusDTO response = roomManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roomDataService.save(roomManager.get());
         response.addMessage("Кабинет сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кабинета.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   public ResponseStatusDTO deleteRoom(long id) {
      roomManager.init(roomDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         roomDataService.delete(id);
         response.addMessage("Кабинет удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удаленни кабинета.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<FieldOfKnowledgeDTO> getFieldOfKnowledgeList(FilterQueryDTO filter) {
      fieldOfKnowledgeListManager.init(fieldOfKnowledgeDataService.getPage(filter));
      PageResultDTO<FieldOfKnowledgeDTO> result = new PageResultDTO(fieldOfKnowledgeListManager.getAll(), fieldOfKnowledgeListManager.getAll().size());
      return result;
   }
   
   public ResponseStatusDTO addFieldOfKnowledge(FieldOfKnowledgeDTO fieldOfKnowledge) {
      fieldOfKnowledgeManager.init(fieldOfKnowledge);
      ResponseStatusDTO response = fieldOfKnowledgeManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         fieldOfKnowledgeDataService.save(fieldOfKnowledgeManager.get());
         response.addMessage("Раздел знаний сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении раздела знаний.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO deleteFieldOfKnowledge(long id) {
      fieldOfKnowledgeManager.init(fieldOfKnowledgeDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         fieldOfKnowledgeDataService.delete(id);
         response.addMessage("Раздел знаний удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении раздела знаний.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<RoleDTO> getRoleList(FilterQueryDTO filter) {
      roleListManager.init(roleDataService.getPage(filter));
      PageResultDTO<RoleDTO> result = new PageResultDTO(roleListManager.getAll(), roleListManager.getAll().size());
      return result;
   }
   
   public ResponseStatusDTO addRole(RoleDTO role) {
      roleManager.init(role);
      ResponseStatusDTO response = roleManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roleDataService.save(roleManager.get());
         response.addMessage("Роль сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении роли.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO deleteRole(long id) {
      roleManager.init(roleDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         roleDataService.delete(id);
         response.addMessage("Роль удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении роли.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<GroupDTO> getGroupList(FilterQueryDTO filter) {
      groupListManager.init(groupDataService.getPage(filter));
      PageResultDTO<GroupDTO> result = new PageResultDTO(groupListManager.getAll(), groupListManager.getAll().size());
      return result;
   }
   
   public ResponseStatusDTO addGroup(GroupDTO group) {
      groupManager.init(group);
      ResponseStatusDTO response = groupManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         groupDataService.save(groupManager.get());
         response.addMessage("Группа сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении группы.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO deleteGroup(long id) {
      groupManager.init(groupDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         groupDataService.delete(id);
         response.addMessage("Группа удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении группы.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<DepartmentDTO> getDepartmentList(FilterQueryDTO filter) {
      departmentListManager.init(departmentDataService.getPage(filter));
      PageResultDTO<DepartmentDTO> result = new PageResultDTO(departmentListManager.getAll(), departmentListManager.getAll().size());
      return result;
   }
   
   public ResponseStatusDTO addDepartment(DepartmentDTO department) {
      departmentManager.init(department);
      ResponseStatusDTO response = departmentManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         departmentDataService.save(departmentManager.get());
         response.addMessage("Кафедра сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кафедры.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO deleteDepartment(long id) {
      departmentManager.init(departmentDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         departmentDataService.delete(id);
         response.addMessage("Кафедра удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении кафедры.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<InstituteDTO> getInstituteList(FilterQueryDTO filter) {
      instituteListManager.init(instituteDataService.getPage(filter));
      PageResultDTO<InstituteDTO> result = new PageResultDTO(instituteListManager.getAll(), instituteListManager.getAll().size());
      return result;
   }

   public ResponseStatusDTO addInstitute(InstituteDTO institute) {
      instituteManager.init(institute);
      ResponseStatusDTO response = instituteManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         instituteDataService.save(instituteManager.get());
         response.addMessage("Институт сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении института.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public ResponseStatusDTO deleteInstitute(long id) {
      instituteManager.init(instituteDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         instituteDataService.delete(id);
         response.addMessage("Институт удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении института .");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<DisciplineDTO> getDisciplineList(FilterQueryDTO filter) {
      disciplineListManager.init(disciplineDataService.getPage(filter));
      PageResultDTO<DisciplineDTO> result = new PageResultDTO(disciplineListManager.getAll(), disciplineListManager.getAll().size());
      return result;
   }
   
   public ResponseStatusDTO addDiscipline(DisciplineDTO discipline) {
      disciplineManager.init(discipline);
      ResponseStatusDTO response = disciplineManager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         disciplineDataService.save(disciplineManager.get());
         response.addMessage("Дисциплна сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении дисциплны.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO deleteDiscipline(long id) {
      disciplineManager.init(disciplineDataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         disciplineDataService.delete(id);
         response.addMessage("Дисциплна удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении дисциплины.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<PointTypeDTO> getPointTypeList(FilterQueryDTO filter) {
      List<PointTypeDTO> pointTypeModels = pointTypeDataService.getAll();
      PageResultDTO<PointTypeDTO> result = new PageResultDTO(pointTypeModels, pointTypeModels.size());
      return result;
   }

   public PageResultDTO<PairTypeDTO> getPairTypeList(FilterQueryDTO filter) {
      List<PairTypeDTO> pairTypeModels = pairTypeDataService.getPage(filter);
      PageResultDTO<PairTypeDTO> result = new PageResultDTO(pairTypeModels, pairTypeModels.size());
      return result;
   }

}
