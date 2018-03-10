package com.unesco.core.models;


import com.unesco.core.entities.Department;
import com.unesco.core.entities.Institute;
import com.unesco.core.models.additional.EntityModel;

import java.util.ArrayList;
import java.util.List;

public class InstituteModel implements EntityModel<Institute> {
   private String name;
   /** Поле набор кафедр */
   private List<DepartmentModel> departments;

   public InstituteModel() {
      this.name = "";
      this.departments = new ArrayList<DepartmentModel>();
   }

   public void EntityToModel(Institute institute) {
      this.name = institute.getName();
      this.departments = new ArrayList<DepartmentModel>();
      for(Department department : institute.getDepartments())
      {
         DepartmentModel departmentModel = new DepartmentModel();
         departmentModel.EntityToModel(department);
         this.departments.add(departmentModel);
      }
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public List<DepartmentModel> getDepartments() {
      return departments;
   }
   public void setDepartments(List<DepartmentModel> departments) {
      this.departments = departments;
   }

}
