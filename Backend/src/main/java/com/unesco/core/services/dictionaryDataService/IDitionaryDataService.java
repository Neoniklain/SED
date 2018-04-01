package com.unesco.core.services.dictionaryDataService;

import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.models.*;
import com.unesco.core.models.account.RoleModel;
import com.unesco.core.models.account.UserModel;
import com.unesco.core.models.additional.FilterQuery;
import com.unesco.core.models.additional.JSONResponseStatus;
import com.unesco.core.models.additional.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IDitionaryDataService {

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

   String DeleteDiscipline(int id);
}
