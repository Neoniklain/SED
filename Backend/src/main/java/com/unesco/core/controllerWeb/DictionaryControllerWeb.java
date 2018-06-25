package com.unesco.core.controllerWeb;

import com.unesco.core.controller.DictionaryController;
import com.unesco.core.dto.account.ProfessorDTO;
import com.unesco.core.dto.account.RoleDTO;
import com.unesco.core.dto.account.UserDTO;
import com.unesco.core.dto.additional.FilterQueryDTO;
import com.unesco.core.dto.additional.PageResultDTO;
import com.unesco.core.dto.additional.ResponseStatusDTO;
import com.unesco.core.dto.journal.PointTypeDTO;
import com.unesco.core.dto.plan.DepartmentDTO;
import com.unesco.core.dto.shedule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/dictionary")
public class DictionaryControllerWeb {

   @Autowired
   private DictionaryController dictionaryController;

   @RequestMapping(method = RequestMethod.POST, value = "professor")
   public PageResultDTO<ProfessorDTO> GetProfessorList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetProfessorList(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "user")
   public PageResultDTO<UserDTO> GetUserList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetUserList(filter);
   }

   @RequestMapping(method = RequestMethod.POST, value = "room")
   public PageResultDTO<RoomDTO> GetRoomList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetRoomList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "room")
   public ResponseStatusDTO AddRoom(@RequestBody RoomDTO room) {
      return dictionaryController.AddRoom(room);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "room/{id}")
   public ResponseStatusDTO DeleteRoom(@PathVariable("id") long id) {
      return dictionaryController.DeleteRoom(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "fieldOfKnowledge")
   public PageResultDTO<FieldOfKnowledgeDTO> GetFieldOfKnowledgeList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetFieldOfKnowledgeList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "fieldOfKnowledge")
   public ResponseStatusDTO AddFieldOfKnowledge(@RequestBody FieldOfKnowledgeDTO fieldOfKnowledge) {
      return dictionaryController.AddFieldOfKnowledge(fieldOfKnowledge);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "fieldOfKnowledge/{id}")
   public ResponseStatusDTO DeleteFieldOfKnowledge(@PathVariable("id") long id) {
      return dictionaryController.DeleteFieldOfKnowledge(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "role")
   public PageResultDTO<RoleDTO> GetRoleList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetRoleList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "role")
   public ResponseStatusDTO AddRole(@RequestBody RoleDTO role) {
      return dictionaryController.AddRole(role);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "role/{id}")
   public ResponseStatusDTO DeleteRole(@PathVariable("id") long id) {
      return dictionaryController.DeleteRole(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "group")
   public PageResultDTO<GroupDTO> GetGroupList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetGroupList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "group")
   public ResponseStatusDTO AddGroup(@RequestBody GroupDTO group) {
      return dictionaryController.AddGroup(group);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "group/{id}")
   public ResponseStatusDTO DeleteGroup(@PathVariable("id") long id) {
      return dictionaryController.DeleteGroup(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "department")
   public PageResultDTO<DepartmentDTO> GetDepartmentList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetDepartmentList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "department")
   public ResponseStatusDTO AddDepartment(@RequestBody DepartmentDTO department) {
      return dictionaryController.AddDepartment(department);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "department/{id}")
   public ResponseStatusDTO DeleteDepartment(@PathVariable("id") long id) {
      return dictionaryController.DeleteDepartment(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "institute")
   public PageResultDTO<InstituteDTO> GetInstituteList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetInstituteList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "institute")
   public ResponseStatusDTO AddInstitute(@RequestBody InstituteDTO institute) {
      return dictionaryController.AddInstitute(institute);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "institute/{id}")
   public ResponseStatusDTO DeleteInstitute(@PathVariable("id") long id) {
      return dictionaryController.DeleteInstitute(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "discipline")
   public PageResultDTO<DisciplineDTO> GetDisciplineList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetDisciplineList(filter);
}
   @RequestMapping(method = RequestMethod.PUT, value = "discipline")
   public ResponseStatusDTO AddDiscipline(@RequestBody DisciplineDTO discipline) {
      return dictionaryController.AddDiscipline(discipline);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "discipline/{id}")
   public ResponseStatusDTO DeleteDiscipline(@PathVariable("id") long id) {
      return dictionaryController.DeleteDiscipline(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "pointType")
   public PageResultDTO<PointTypeDTO> GetPointTypeList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.GetPointTypeList(filter);
   }

}
