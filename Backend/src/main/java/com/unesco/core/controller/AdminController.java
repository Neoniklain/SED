package com.unesco.core.controller;

import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.services.dictionaryDataService.DitionaryDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdminController {

   @Autowired
   private DitionaryDataService ditionaryDataService;

   @RequestMapping(method = RequestMethod.POST, value = "page/users")
   public PageResult<UserModel> GetUserList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getUserPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/disciplines")
   public PageResult<DisciplineModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDisciplinePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/institutes")
   public PageResult<InstituteModel> GetInstituteList(@RequestBody FilterQuery filter) {
      PageResult<InstituteModel> result = ditionaryDataService.getInstitutePage(filter);
      return result;
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/departments")
   public PageResult<DepartmentModel> GetDepartmentList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDepartmentPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/groups")
   public PageResult<GroupModel> GetGroupList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getGroupPage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/roles")
   public PageResult<RoleModel> GetRoleList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getRolePage(filter);
   }
   @RequestMapping(method = RequestMethod.POST, value = "page/fieldOfKnowledge")
   public PageResult<FieldOfKnowledgeModel> GetFieldOfKnowledgeList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getFieldOfKnowledgePage(filter);
   }

   @RequestMapping(method = RequestMethod.PUT, value = "page/disciplines")
   public String AddDiscipline(@RequestBody DisciplineModel discipline) {
      String result = ditionaryDataService.AddOrUpdateDiscipline(discipline);
      return result;
   }

   @RequestMapping(method = RequestMethod.DELETE, value = "page/disciplines/{id}")
   public String DeleteDiscipline(@PathVariable("id") int id) {
      String result = ditionaryDataService.DeleteDiscipline(id);
      return result;
   }
}
