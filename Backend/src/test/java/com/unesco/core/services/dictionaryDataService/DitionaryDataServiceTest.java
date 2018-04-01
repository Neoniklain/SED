package com.unesco.core.services.dictionaryDataService;

import com.google.common.collect.ImmutableList;
import com.unesco.core.models.InstituteModel;
import com.unesco.core.repositories.account.RoleRepository;
import com.unesco.core.repositories.account.UserRepository;
import com.unesco.core.repositories.plan.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class DitionaryDataServiceTest {
   @Mock
   private InstituteRepository instituteRepository;
   @Mock
   private DepartmentRepository departmentRepository;
   @Mock
   private GroupRepository groupRepository;
   @Mock
   private UserRepository userRepository;
   @Mock
   private DisciplineRepository disciplineRepository;
   @Mock
   private RoleRepository roleRepository;
   @Mock
   private FieldOfKnowledgeRepository fieldOfKnowledgeRepository;

   @InjectMocks
   DitionaryDataService ditionaryDataService;

   @Test
   public void getInstitutes() throws Exception {
      //prepare
      when(instituteRepository.findAll()).thenReturn(ImmutableList.of());
      //testing
      List<InstituteModel> models = ditionaryDataService.getInstitutes();
      //validate
      verify(instituteRepository).findAll();
   }

   @Test
   public void getDepartments() throws Exception {
   }

   @Test
   public void getGroups() throws Exception {
   }

   @Test
   public void getDisciplines() throws Exception {
   }

   @Test
   public void getUsers() throws Exception {
   }

   @Test
   public void getRoles() throws Exception {
   }

   @Test
   public void getFieldOfKnowledges() throws Exception {
   }

   @Test
   public void getInstitutePage() throws Exception {
   }

   @Test
   public void getDepartmentPage() throws Exception {
   }

   @Test
   public void getGroupPage() throws Exception {
   }

   @Test
   public void getDisciplinePage() throws Exception {
   }

   @Test
   public void getUserPage() throws Exception {
   }

   @Test
   public void getRolePage() throws Exception {
   }

   @Test
   public void getFieldOfKnowledgePage() throws Exception {
   }

   @Test
   public void addOrUpdateDiscipline() throws Exception {
   }

   @Test
   public void deleteDiscipline() throws Exception {
   }

}