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

import java.util.ArrayList;
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
   RoomModel getRooms(int id);
   PageResult<String> getWeekTypePage(FilterQuery filter);
   PageResult<String> getDayOfWeekPage(FilterQuery filter);
   PageResult<ProfessorModel> getProfessorPage(FilterQuery filter);

   List<InstituteModel> getInstitutes();
   List<DepartmentModel> getDepartments();
   List<GroupModel> getGroups();
   List<DisciplineModel> getDisciplines();
   List<UserModel> getUsers();
   List<RoleModel> getRoles();
   List<FieldOfKnowledgeModel> getFieldOfKnowledges();
   List<RoomModel> getRooms();

   PageResult<InstituteModel> getInstitutePage(FilterQuery filter);
   PageResult<DepartmentModel> getDepartmentPage(FilterQuery filter);
   PageResult<GroupModel> getGroupPage(FilterQuery filter);
   PageResult<DisciplineModel> getDisciplinePage(FilterQuery filter);
   PageResult<UserModel> getUserPage(FilterQuery filter);
   PageResult<RoleModel> getRolePage(FilterQuery filter);
   PageResult<FieldOfKnowledgeModel> getFieldOfKnowledgePage(FilterQuery filter);
   PageResult<RoomModel> getRoomPage(FilterQuery filter);

   JSONResponseStatus AddOrUpdateDiscipline(DisciplineModel discipline);
   JSONResponseStatus AddOrUpdateInstitute(InstituteModel institute);
   JSONResponseStatus AddOrUpdateDepartment(DepartmentModel department);
   JSONResponseStatus AddOrUpdateGroup(GroupModel group);
   JSONResponseStatus AddOrUpdateRole(RoleModel role);
   JSONResponseStatus AddOrUpdateFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge);
   JSONResponseStatus AddOrUpdateRoom(RoomModel room);

   JSONResponseStatus DeleteDiscipline(int id);
   JSONResponseStatus DeleteInstitute(int id);
   JSONResponseStatus DeleteDepartment(int id);
   JSONResponseStatus DeleteGroup(int id);
   JSONResponseStatus DeleteRole(int id);
   JSONResponseStatus DeleteFieldOfKnowledge(int id);
   JSONResponseStatus DeleteRoom(int id);
}
