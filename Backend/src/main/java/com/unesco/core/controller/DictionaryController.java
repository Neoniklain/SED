package com.unesco.core.controller;

import com.unesco.core.entities.Professor;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.services.dictionaryDataService.IDitionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/dictionary")
public class DictionaryController {

   @Autowired
   private IDitionaryDataService ditionaryDataService;

   @RequestMapping(method = RequestMethod.POST, value = "user")
   public PageResult<UserModel> GetUserList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getUserPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "discipline")
   public PageResult<DisciplineModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDisciplinePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "institute")
   public PageResult<InstituteModel> GetInstituteList(@RequestBody FilterQuery filter) {
      PageResult<InstituteModel> result = ditionaryDataService.getInstitutePage(filter);
      return result;
   }
   @RequestMapping(method = RequestMethod.POST, value = "department")
   public PageResult<DepartmentModel> GetDepartmentList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDepartmentPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "group")
   public PageResult<GroupModel> GetGroupList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getGroupPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "role")
   public PageResult<RoleModel> GetRoleList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getRolePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "fieldOfKnowledge")
   public PageResult<FieldOfKnowledgeModel> GetFieldOfKnowledgeList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getFieldOfKnowledgePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "room")
   public PageResult<RoomModel> GetRoomList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getRoomPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "weekType")
   public PageResult<String> GetWeekTypeList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getWeekTypePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "dayOfWeek")
   public PageResult<String> GetDayOfWeekList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDayOfWeekPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "professor")
   public PageResult<ProfessorModel> GetProfessorList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getProfessorPage(filter);
   }

   @RequestMapping(method = RequestMethod.PUT, value = "discipline")
   public JSONResponseStatus AddDiscipline(@RequestBody DisciplineModel discipline) {
      JSONResponseStatus result = ditionaryDataService.AddOrUpdateDiscipline(discipline);
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "group")
   public JSONResponseStatus AddGroup(@RequestBody GroupModel group) {
      JSONResponseStatus result = ditionaryDataService.AddOrUpdateGroup(group);
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "department")
   public JSONResponseStatus AddDepartment(@RequestBody DepartmentModel department) {
      JSONResponseStatus result = ditionaryDataService.AddOrUpdateDepartment(department);
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "institute")
   public JSONResponseStatus AddInstitute(@RequestBody InstituteModel institute) {
      JSONResponseStatus result = ditionaryDataService.AddOrUpdateInstitute(institute);
      return result;
   }
   @RequestMapping(method = RequestMethod.PUT, value = "room")
   public JSONResponseStatus AddRoom(@RequestBody RoomModel room) {
      JSONResponseStatus result = ditionaryDataService.AddOrUpdateRoom(room);
      return result;
   }

   @RequestMapping(method = RequestMethod.DELETE, value = "discipline/{id}")
   public JSONResponseStatus DeleteDiscipline(@PathVariable("id") int id) {
      JSONResponseStatus result = ditionaryDataService.DeleteDiscipline(id);
         return result;
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "group/{id}")
   public JSONResponseStatus DeleteGroup(@PathVariable("id") int id) {
      JSONResponseStatus result = ditionaryDataService.DeleteGroup(id);
      return result;
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "department/{id}")
   public JSONResponseStatus DeleteDepartment(@PathVariable("id") int id) {
      JSONResponseStatus result = ditionaryDataService.DeleteDepartment(id);
      return result;
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "institute/{id}")
   public JSONResponseStatus DeleteInstitute(@PathVariable("id") int id) {
      JSONResponseStatus result = ditionaryDataService.DeleteInstitute(id);
      return result;
   }
   @RequestMapping(method = RequestMethod.DELETE, value = "room/{id}")
   public JSONResponseStatus DeleteRoom(@PathVariable("id") int id) {
      JSONResponseStatus result = ditionaryDataService.DeleteRoom(id);
      return result;
   }
}
