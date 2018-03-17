package com.unesco.core.controller;

import com.unesco.core.models.DepartmentModel;
import com.unesco.core.models.DisciplineModel;
import com.unesco.core.models.GroupModel;
import com.unesco.core.models.InstituteModel;
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

   @RequestMapping(value = "page/users")
   public PageResult<UserModel> GetUserList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getUserPage(filter);
   }
   @RequestMapping(value = "page/disciplines")
   public PageResult<DisciplineModel> GetDisciplineList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDisciplinePage(filter);
   }
   @RequestMapping(value = "page/institutes")
   public PageResult<InstituteModel> GetInstituteList(@RequestBody FilterQuery filter) {
      PageResult<InstituteModel> result = ditionaryDataService.getInstitutePage(filter);
      return result;
   }
   @RequestMapping(value = "page/departments")
   public PageResult<DepartmentModel> GetDepartmentList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getDepartmentPage(filter);
   }
   @RequestMapping(value = "page/groups")
   public PageResult<GroupModel> GetGroupList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getGroupPage(filter);
   }
   @RequestMapping(value = "page/roles")
   public PageResult<RoleModel> GetRoleList(@RequestBody FilterQuery filter) {
      return ditionaryDataService.getRolePage(filter);
   }
}
