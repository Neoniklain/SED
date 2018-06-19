package com.unesco.core.controller;

import com.unesco.core.managers.account.professorManager.interfaces.professor.IProfessorManager;
import com.unesco.core.managers.account.professorManager.interfaces.professorList.IProfessorListManager;
import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.managers.account.roleManager.interfaces.roleList.IRoleListManager;
import com.unesco.core.managers.account.userManager.interfaces.user.IUserManager;
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
import com.unesco.core.models.account.ProfessorModel;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.additional.ResponseStatus;
import com.unesco.core.models.journal.PointTypeModel;
import com.unesco.core.models.plan.DepartmentModel;
import com.unesco.core.models.shedule.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/dictionary")
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
   private IUserManager userManager;
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
   private IProfessorManager professorManager;
   @Autowired
   private IProfessorListManager professorListManager;

   @Autowired
   private IProfessorManager pointTypeManager;

   @RequestMapping(method = RequestMethod.POST, value = "professor")
   public PageResult<ProfessorModel> GetProfessorList(@RequestBody FilterQuery filter) {
      professorListManager.Init(professorDataService.GetPage(filter));
      PageResult<ProfessorModel> result = new PageResult(professorListManager.GetAll(), professorListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.POST, value = "user")
   public PageResult<UserModel> GetUserList(@RequestBody FilterQuery filter) {
      userListManager.Init(userDataService.GetPage(filter));
      PageResult<UserModel> result = new PageResult(userListManager.GetAll(), userListManager.GetAll().size());
      return result;
   }

   @RequestMapping(method = RequestMethod.POST, value = "room")
   public PageResult<RoomModel> GetRoomList(@RequestBody FilterQuery filter) {
      roomListManager.Init(roomDataService.GetPage(filter));
      PageResult<RoomModel> result = new PageResult(roomListManager.GetAll(), roomListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "room")
   public ResponseStatus AddRoom(@RequestBody RoomModel room) {
      roomManager.Init(room);
      ResponseStatus response = roomManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roomDataService.Save(roomManager.Get());
         response.addMessage("Кабинет сохранен.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кабинета.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "room/{id}")
   public ResponseStatus DeleteRoom(@PathVariable("id") long id) {
      roomManager.Init(roomDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         roomDataService.Delete(id);
         response.addMessage("Кабинет удален.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Оошибка при удаленни кабинета.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "fieldOfKnowledge")
   public PageResult<FieldOfKnowledgeModel> GetFieldOfKnowledgeList(@RequestBody FilterQuery filter) {
      fieldOfKnowledgeListManager.Init(fieldOfKnowledgeDataService.GetPage(filter));
      PageResult<FieldOfKnowledgeModel> result = new PageResult(fieldOfKnowledgeListManager.GetAll(), fieldOfKnowledgeListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "fieldOfKnowledge")
   public ResponseStatus AddFieldOfKnowledge(@RequestBody FieldOfKnowledgeModel fieldOfKnowledge) {
      fieldOfKnowledgeManager.Init(fieldOfKnowledge);
      ResponseStatus response = fieldOfKnowledgeManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         fieldOfKnowledgeDataService.Save(fieldOfKnowledgeManager.Get());
         response.addMessage("Раздел знаний сохранен.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении раздела знаний.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "fieldOfKnowledge/{id}")
   public ResponseStatus DeleteFieldOfKnowledge(@PathVariable("id") long id) {
      fieldOfKnowledgeManager.Init(fieldOfKnowledgeDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         fieldOfKnowledgeDataService.Delete(id);
         response.addMessage("Раздел знаний удален.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении раздела знаний.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "role")
   public PageResult<RoleModel> GetRoleList(@RequestBody FilterQuery filter) {
      roleListManager.Init(roleDataService.GetPage(filter));
      PageResult<RoleModel> result = new PageResult(roleListManager.GetAll(), roleListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "role")
   public ResponseStatus AddRole(@RequestBody RoleModel role) {
      roleManager.Init(role);
      ResponseStatus response = roleManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         roleDataService.Save(roleManager.Get());
         response.addMessage("Роль сохранена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении роли.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "role/{id}")
   public ResponseStatus DeleteRole(@PathVariable("id") long id) {
      roleManager.Init(roleDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         roleDataService.Delete(id);
         response.addMessage("Роль удалена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении роли.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "group")
   public PageResult<GroupModel> GetGroupList(@RequestBody FilterQuery filter) {
      groupListManager.Init(groupDataService.GetPage(filter));
      PageResult<GroupModel> result = new PageResult(groupListManager.GetAll(), groupListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "group")
   public ResponseStatus AddGroup(@RequestBody GroupModel group) {
      groupManager.Init(group);
      ResponseStatus response = groupManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         groupDataService.Save(groupManager.Get());
         response.addMessage("Группа сохранена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении группы.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "group/{id}")
   public ResponseStatus DeleteGroup(@PathVariable("id") long id) {
      groupManager.Init(groupDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         groupDataService.Delete(id);
         response.addMessage("Группа удалена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении группы.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "department")
   public PageResult<DepartmentModel> GetDepartmentList(@RequestBody FilterQuery filter) {
      departmentListManager.Init(departmentDataService.GetPage(filter));
      PageResult<DepartmentModel> result = new PageResult(departmentListManager.GetAll(), departmentListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "department")
   public ResponseStatus AddDepartment(@RequestBody DepartmentModel department) {
      departmentManager.Init(department);
      ResponseStatus response = departmentManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         departmentDataService.Save(departmentManager.Get());
         response.addMessage("Кафедра сохранена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении кафедры.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "department/{id}")
   public ResponseStatus DeleteDepartment(@PathVariable("id") long id) {
      departmentManager.Init(departmentDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         departmentDataService.Delete(id);
         response.addMessage("Кафедра удалена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении кафедры.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "institute")
   public PageResult<InstituteModel> GetInstituteList(@RequestBody FilterQuery filter) {
      instituteListManager.Init(instituteDataService.GetPage(filter));
      PageResult<InstituteModel> result = new PageResult(instituteListManager.GetAll(), instituteListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "institute")
   public ResponseStatus AddInstitute(@RequestBody InstituteModel institute) {
      instituteManager.Init(institute);
      ResponseStatus response = instituteManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         instituteDataService.Save(instituteManager.Get());
         response.addMessage("Институт сохранен.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении института.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "institute/{id}")
   public ResponseStatus DeleteInstitute(@PathVariable("id") long id) {
      instituteManager.Init(instituteDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         instituteDataService.Delete(id);
         response.addMessage("Институт удален.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении института .");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "discipline")
   public PageResult<DisciplineModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      disciplineListManager.Init(disciplineDataService.GetPage(filter));
      PageResult<DisciplineModel> result = new PageResult(disciplineListManager.GetAll(), disciplineListManager.GetAll().size());
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "discipline")
   public ResponseStatus AddDiscipline(@RequestBody DisciplineModel discipline) {
      disciplineManager.Init(discipline);
      ResponseStatus response = disciplineManager.Validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      try {
         disciplineDataService.Save(disciplineManager.Get());
         response.addMessage("Дисциплна сохранена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при сохранении дисциплны.");
         response.addMessage(e.getMessage());
         return response;
      }
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "discipline/{id}")
   public ResponseStatus DeleteDiscipline(@PathVariable("id") long id) {
      disciplineManager.Init(disciplineDataService.Get(id));
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         disciplineDataService.Delete(id);
         response.addMessage("Дисциплна удалена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении дисциплины.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

   @RequestMapping(method = RequestMethod.POST, value = "pointType")
   public PageResult<PointTypeModel> GetPointTypeList(@RequestBody FilterQuery filter) {
      List<PointTypeModel> pointTypeModels = pointTypeDataService.GetAll();
      PageResult<PointTypeModel> result = new PageResult(pointTypeModels, pointTypeModels.size());
      return result;
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "pointType/{id}")
   public ResponseStatus DeletePointType(@PathVariable("id") long id) {
      ResponseStatus response = new ResponseStatus();
      response.setStatus(StatusTypes.OK);
      try {
         pointTypeDataService.Delete(id);
         response.addMessage("Дисциплна удалена.");
         return response;
      }
      catch (Exception e) {
         response.setStatus(StatusTypes.ERROR);
         response.addMessage("Ошибка при удалении дисциплины.");
         response.addMessage(e.getMessage());
         return response;
      }
   }

}
