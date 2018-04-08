package com.unesco.core.services.dictionaryDataService;

import com.unesco.core.entities.*;
import com.unesco.core.entities.account.Role;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.additional.PageResult;
import com.unesco.core.models.enums.RoleType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDitionaryDataService {

   InstituteModel getInstitute(int id);
   DepartmentModel getDepartment(int id);
   GroupModel getGroup(int id);
   DisciplineModel getDiscipline(int id);
   UserModel getUser(int id);
   RoleModel getRole(int id);
   RoleModel getRole(RoleType roleType);
   FieldOfKnowledgeModel getFieldOfKnowledges(int id);

   List<InstituteModel> getInstitutes();
   List<DepartmentModel> getDepartments();
   List<GroupModel> getGroups();
   List<DisciplineModel> getDisciplines();
   List<UserModel> getUsers();
   List<RoleModel> getRoles();
   List<FieldOfKnowledgeModel> getFieldOfKnowledges();

   PageResult<InstituteModel> getInstitutePage(FilterQuery filter);
   PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter);
   PageResult<GroupModel> getGroupPage(FilterQuery filter);
   PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter);
   PageResult<UserModel> getUserPage(FilterQuery filter);
   PageResult<RoleModel> getRolePage(FilterQuery filter);
   PageResult<FieldOfKnowledgeModel> getFieldOfKnowledgePage(FilterQuery filter);

   String AddOrUpdateDiscipline(DisciplineModel discipline);
   String AddOrUpdateInstitute(InstituteModel institute);
   String AddOrUpdateDepartment(DepartmentModel department);
   String AddOrUpdateGroup(GroupModel group);
   String AddOrUpdateRole(RoleModel role);
   String AddOrUpdateFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge);

   String DeleteDiscipline(int id);
   String DeleteInstitute(int id);
   String DeleteDepartment(int id);
   String DeleteGroup(int id);
   String DeleteRole(int id);
   String DeleteFieldOfKnowledge(int id);
}
