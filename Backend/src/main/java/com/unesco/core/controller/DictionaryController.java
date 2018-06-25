package com.unesco.core.controller;

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
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.services.account.professorService.IProfessorDataService;
import com.unesco.core.services.account.roleService.IRoleDataService;
import com.unesco.core.services.account.userService.IUserDataService;
import com.unesco.core.services.journal.pointType.IPointTypeDataService;
import com.unesco.core.services.schedule.departmentService.IDepartmentDataService;
import com.unesco.core.services.schedule.disciplineService.IDisciplineDataService;
import com.unesco.core.services.schedule.fieldOfKnowledgeService.IFieldOfKnowledgeDataService;
import com.unesco.core.services.schedule.groupService.IGroupDataService;
import com.unesco.core.services.schedule.instituteService.IInstituteDataService;
import com.unesco.core.services.schedule.roomService.IRoomDataService;
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

   public PageResultDTO<ProfessorDTO> GetProfessorList(FilterQueryDTO filter) {
      professorListManager.Init(professorDataService.GetPage(filter));
      PageResultDTO<ProfessorDTO> result = new PageResultDTO(professorListManager.GetAll(), professorListManager.GetAll().size());
      return result;
   }
   public PageResultDTO<UserDTO> GetUserList(FilterQueryDTO filter) {
      userListManager.Init(userDataService.GetPage(filter));
      PageResultDTO<UserDTO> result = new PageResultDTO(userListManager.GetAll(), userListManager.GetAll().size());
      return result;
   }

   public PageResultDTO<RoomDTO> GetRoomList(FilterQueryDTO filter) {
      roomListManager.Init(roomDataService.GetPage(filter));
      PageResultDTO<RoomDTO> result = new PageResultDTO(roomListManager.GetAll(), roomListManager.GetAll().size());
      return result;
   }
   public ResponseStatusDTO AddRoom(RoomDTO room) {
      roomManager.Init(room);
      ResponseStatusDTO response = roomManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roomDataService.Save(roomManager.Get());
         response.addMessage("Кабинет сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кабинета.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   public ResponseStatusDTO DeleteRoom(long id) {
      roomManager.Init(roomDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         roomDataService.Delete(id);
         response.addMessage("Кабинет удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Оошибка при удаленни кабинета.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<FieldOfKnowledgeDTO> GetFieldOfKnowledgeList(FilterQueryDTO filter) {
      fieldOfKnowledgeListManager.Init(fieldOfKnowledgeDataService.GetPage(filter));
      PageResultDTO<FieldOfKnowledgeDTO> result = new PageResultDTO(fieldOfKnowledgeListManager.GetAll(), fieldOfKnowledgeListManager.GetAll().size());
      return result;
   }
   
   public ResponseStatusDTO AddFieldOfKnowledge(FieldOfKnowledgeDTO fieldOfKnowledge) {
      fieldOfKnowledgeManager.Init(fieldOfKnowledge);
      ResponseStatusDTO response = fieldOfKnowledgeManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         fieldOfKnowledgeDataService.Save(fieldOfKnowledgeManager.Get());
         response.addMessage("Раздел знаний сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении раздела знаний.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO DeleteFieldOfKnowledge(long id) {
      fieldOfKnowledgeManager.Init(fieldOfKnowledgeDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         fieldOfKnowledgeDataService.Delete(id);
         response.addMessage("Раздел знаний удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении раздела знаний.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<RoleDTO> GetRoleList(FilterQueryDTO filter) {
      roleListManager.Init(roleDataService.GetPage(filter));
      PageResultDTO<RoleDTO> result = new PageResultDTO(roleListManager.GetAll(), roleListManager.GetAll().size());
      return result;
   }
   
   public ResponseStatusDTO AddRole(RoleDTO role) {
      roleManager.Init(role);
      ResponseStatusDTO response = roleManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roleDataService.Save(roleManager.Get());
         response.addMessage("Роль сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении роли.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO DeleteRole(long id) {
      roleManager.Init(roleDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         roleDataService.Delete(id);
         response.addMessage("Роль удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении роли.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<GroupDTO> GetGroupList(FilterQueryDTO filter) {
      groupListManager.Init(groupDataService.GetPage(filter));
      PageResultDTO<GroupDTO> result = new PageResultDTO(groupListManager.GetAll(), groupListManager.GetAll().size());
      return result;
   }
   
   public ResponseStatusDTO AddGroup(GroupDTO group) {
      groupManager.Init(group);
      ResponseStatusDTO response = groupManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         groupDataService.Save(groupManager.Get());
         response.addMessage("Группа сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении группы.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO DeleteGroup(long id) {
      groupManager.Init(groupDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         groupDataService.Delete(id);
         response.addMessage("Группа удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении группы.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<DepartmentDTO> GetDepartmentList(FilterQueryDTO filter) {
      departmentListManager.Init(departmentDataService.GetPage(filter));
      PageResultDTO<DepartmentDTO> result = new PageResultDTO(departmentListManager.GetAll(), departmentListManager.GetAll().size());
      return result;
   }
   
   public ResponseStatusDTO AddDepartment(DepartmentDTO department) {
      departmentManager.Init(department);
      ResponseStatusDTO response = departmentManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         departmentDataService.Save(departmentManager.Get());
         response.addMessage("Кафедра сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кафедры.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO DeleteDepartment(long id) {
      departmentManager.Init(departmentDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         departmentDataService.Delete(id);
         response.addMessage("Кафедра удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении кафедры.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<InstituteDTO> GetInstituteList(FilterQueryDTO filter) {
      instituteListManager.Init(instituteDataService.GetPage(filter));
      PageResultDTO<InstituteDTO> result = new PageResultDTO(instituteListManager.GetAll(), instituteListManager.GetAll().size());
      return result;
   }

   public ResponseStatusDTO AddInstitute(InstituteDTO institute) {
      instituteManager.Init(institute);
      ResponseStatusDTO response = instituteManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         instituteDataService.Save(instituteManager.Get());
         response.addMessage("Институт сохранен.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении института.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public ResponseStatusDTO DeleteInstitute(long id) {
      instituteManager.Init(instituteDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         instituteDataService.Delete(id);
         response.addMessage("Институт удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении института .");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<DisciplineDTO> GetDisciplineList(FilterQueryDTO filter) {
      disciplineListManager.Init(disciplineDataService.GetPage(filter));
      PageResultDTO<DisciplineDTO> result = new PageResultDTO(disciplineListManager.GetAll(), disciplineListManager.GetAll().size());
      return result;
   }
   
   public ResponseStatusDTO AddDiscipline(DisciplineDTO discipline) {
      disciplineManager.Init(discipline);
      ResponseStatusDTO response = disciplineManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         disciplineDataService.Save(disciplineManager.Get());
         response.addMessage("Дисциплна сохранена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении дисциплны.");
         response.addMessage(e.getMessage());
      }
      return response;
   }
   
   public ResponseStatusDTO DeleteDiscipline(long id) {
      disciplineManager.Init(disciplineDataService.Get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         disciplineDataService.Delete(id);
         response.addMessage("Дисциплна удалена.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении дисциплины.");
         response.addMessage(e.getMessage());
      }
      return response;
   }

   public PageResultDTO<PointTypeDTO> GetPointTypeList(FilterQueryDTO filter) {
      List<PointTypeDTO> pointTypeModels = pointTypeDataService.GetAll();
      PageResultDTO<PointTypeDTO> result = new PageResultDTO(pointTypeModels, pointTypeModels.size());
      return result;
   }
   
   public ResponseStatusDTO DeletePointType(long id) {
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      try {
         pointTypeDataService.Delete(id);
         response.addMessage("Типа занятия удален.");
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении типа занятия.");
         response.addMessage(e.getMessage());
      }
      return response;

   }

}
