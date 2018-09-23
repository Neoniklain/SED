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
   public PageResultDTO<ProfessorDTO> getProfessorList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getProfessorList(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "user")
   public PageResultDTO<UserDTO> getUserList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getUserList(filter);
   }

   @RequestMapping(method = RequestMethod.POST, value = "room")
   public PageResultDTO<RoomDTO> getRoomList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getRoomList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "room")
   public ResponseStatusDTO addRoom(@RequestBody RoomDTO room) {
      return dictionaryController.addRoom(room);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "room/{id}")
   public ResponseStatusDTO deleteRoom(@PathVariable("id") long id) {
      return dictionaryController.deleteRoom(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "fieldOfKnowledge")
   public PageResultDTO<FieldOfKnowledgeDTO> getFieldOfKnowledgeList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getFieldOfKnowledgeList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "fieldOfKnowledge")
   public ResponseStatusDTO addFieldOfKnowledge(@RequestBody FieldOfKnowledgeDTO fieldOfKnowledge) {
      return dictionaryController.addFieldOfKnowledge(fieldOfKnowledge);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "fieldOfKnowledge/{id}")
   public ResponseStatusDTO deleteFieldOfKnowledge(@PathVariable("id") long id) {
      return dictionaryController.deleteFieldOfKnowledge(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "role")
   public PageResultDTO<RoleDTO> getRoleList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getRoleList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "role")
   public ResponseStatusDTO addRole(@RequestBody RoleDTO role) {
      return dictionaryController.addRole(role);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "role/{id}")
   public ResponseStatusDTO deleteRole(@PathVariable("id") long id) {
      return dictionaryController.deleteRole(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "group")
   public PageResultDTO<GroupDTO> getGroupList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getGroupList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "group")
   public ResponseStatusDTO addGroup(@RequestBody GroupDTO group) {
      return dictionaryController.addGroup(group);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "group/{id}")
   public ResponseStatusDTO deleteGroup(@PathVariable("id") long id) {
      return dictionaryController.deleteGroup(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "department")
   public PageResultDTO<DepartmentDTO> getDepartmentList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getDepartmentList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "department")
   public ResponseStatusDTO addDepartment(@RequestBody DepartmentDTO department) {
      return dictionaryController.addDepartment(department);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "department/{id}")
   public ResponseStatusDTO deleteDepartment(@PathVariable("id") long id) {
      return dictionaryController.deleteDepartment(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "institute")
   public PageResultDTO<InstituteDTO> getInstituteList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getInstituteList(filter);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "institute")
   public ResponseStatusDTO addInstitute(@RequestBody InstituteDTO institute) {
      return dictionaryController.addInstitute(institute);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "institute/{id}")
   public ResponseStatusDTO deleteInstitute(@PathVariable("id") long id) {
      return dictionaryController.deleteInstitute(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "discipline")
   public PageResultDTO<DisciplineDTO> getDisciplineList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getDisciplineList(filter);
}
   @RequestMapping(method = RequestMethod.PUT, value = "discipline")
   public ResponseStatusDTO addDiscipline(@RequestBody DisciplineDTO discipline) {
      return dictionaryController.addDiscipline(discipline);
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "discipline/{id}")
   public ResponseStatusDTO deleteDiscipline(@PathVariable("id") long id) {
      return dictionaryController.deleteDiscipline(id);
   }

   @RequestMapping(method = RequestMethod.POST, value = "pointType")
   public PageResultDTO<PointTypeDTO> getPointTypeList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getPointTypeList(filter);
   }

   @RequestMapping(method = RequestMethod.POST, value = "pairTypes")
   public PageResultDTO<PairTypeDTO> getPairTypeList(@RequestBody FilterQueryDTO filter) {
      return dictionaryController.getPairTypeList(filter);
   }

}
