package com.unesco.core.controller;

import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.enums.StatusTypes;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import com.unesco.core.managers.IManager;
import com.unesco.core.managers.account.roleManager.interfaces.role.IRoleManager;
import com.unesco.core.managers.schedule.departmentManager.interfaces.department.IDepartmentManager;
import com.unesco.core.managers.schedule.disciplineManager.interfaces.discipline.IDisciplineManager;
import com.unesco.core.managers.schedule.fieldofknowledgeManager.interfaces.fieldofknowledge.IFieldOfKnowledgeManager;
import com.unesco.core.managers.schedule.groupManager.interfaces.group.IGroupManager;
import com.unesco.core.managers.schedule.instituteManager.interfaces.institute.IInstituteManager;
import com.unesco.core.managers.schedule.roomManager.interfaces.room.IRoomManager;
import com.unesco.core.services.dataService.IDataService;
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
   private IInstituteManager instituteManager;

   @Autowired
   private IDepartmentManager departmentManager;

   @Autowired
   private IGroupManager groupManager;

   @Autowired
   private IRoleManager roleManager;

   @Autowired
   private IFieldOfKnowledgeManager fieldOfKnowledgeManager;

   @Autowired
   private IRoomManager roomManager;

   public PageResultDTO<ProfessorDTO> getProfessorList(FilterQueryDTO filter) {
      return professorDataService.getPage(filter);
   }
   public PageResultDTO<UserDTO> getUserList(FilterQueryDTO filter) {
      return userDataService.getPage(filter);
   }

   public PageResultDTO<RoomDTO> getRoomList(FilterQueryDTO filter) {
      return roomDataService.getPage(filter);
   }
   public ResponseStatusDTO addRoom(RoomDTO room) {
      return add(roomManager, roomDataService, room);
   }
   public ResponseStatusDTO deleteRoom(long id) {
      return delete(roomManager, roomDataService, id);
   }

   public PageResultDTO<FieldOfKnowledgeDTO> getFieldOfKnowledgeList(FilterQueryDTO filter) {
      return fieldOfKnowledgeDataService.getPage(filter);
   }
   public ResponseStatusDTO addFieldOfKnowledge(FieldOfKnowledgeDTO fieldOfKnowledge) {
      return add(fieldOfKnowledgeManager, fieldOfKnowledgeDataService, fieldOfKnowledge);
   }
   public ResponseStatusDTO deleteFieldOfKnowledge(long id) {
      return delete(fieldOfKnowledgeManager, fieldOfKnowledgeDataService, id);
   }

   public PageResultDTO<RoleDTO> getRoleList(FilterQueryDTO filter) {
      return roleDataService.getPage(filter);
   }
   public ResponseStatusDTO addRole(RoleDTO role) {
      return add(roleManager, roleDataService, role);
   }
   public ResponseStatusDTO deleteRole(long id) {
      return delete(roleManager, roleDataService, id);
   }

   public PageResultDTO<GroupDTO> getGroupList(FilterQueryDTO filter) {
      return groupDataService.getPage(filter);
   }
   public ResponseStatusDTO addGroup(GroupDTO group) {
      return add(groupManager, groupDataService, group);
   }
   public ResponseStatusDTO deleteGroup(long id) {
      return delete(groupManager, groupDataService, id);
   }

   public PageResultDTO<DepartmentDTO> getDepartmentList(FilterQueryDTO filter) {
      return departmentDataService.getPage(filter);
   }
   public ResponseStatusDTO addDepartment(DepartmentDTO department) {
      return add(departmentManager, departmentDataService, department);
   }
   public ResponseStatusDTO deleteDepartment(long id) {
      return delete(departmentManager, departmentDataService, id);
   }

   public PageResultDTO<InstituteDTO> getInstituteList(FilterQueryDTO filter) {
      return instituteDataService.getPage(filter);
   }
   public ResponseStatusDTO addInstitute(InstituteDTO institute) {
      return add(instituteManager, instituteDataService, institute);
   }
   public ResponseStatusDTO deleteInstitute(long id) {
      return delete(instituteManager, instituteDataService, id);
   }

   public PageResultDTO<DisciplineDTO> getDisciplineList(FilterQueryDTO filter) {
      return disciplineDataService.getPage(filter);
   }
   public ResponseStatusDTO addDiscipline(DisciplineDTO discipline) {
      return add(disciplineManager, disciplineDataService, discipline);
   }
   public ResponseStatusDTO deleteDiscipline(long id) {
      return delete(disciplineManager, disciplineDataService, id);
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

   private <T> ResponseStatusDTO add(IManager<T> manager, IDataService<T> dataService, T model) {
      manager.init(model);
      ResponseStatusDTO response = manager.validate();
      if(response.getStatus() != StatusTypes.OK) return response;
      response = dataService.save(manager.get());
      if(response.getStatus() != StatusTypes.OK) return response;
      response.setStatus(StatusTypes.OK);
      response.addMessage("Успешно сохранено.");
      return response;
   }

   private <T> ResponseStatusDTO delete(IManager<T> manager, IDataService<T> dataService, long id) {
      manager.init(dataService.get(id));
      ResponseStatusDTO response = new ResponseStatusDTO();
      response.setStatus(StatusTypes.OK);
      response = dataService.delete(id);
      if(response.getStatus() != StatusTypes.OK) return response;
      response.setStatus(StatusTypes.OK);
      response.addMessage("Успешно удалено.");
      return response;
   }

}
