package com.unesco.core.models;


import com.unesco.core.entities.Department;
import com.unesco.core.entities.Institute;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class InstituteModel {
   private String name;
   /** Поле набор кафедр */
   private List<DepartmentModel> departments;

   InstituteModel() {
      this.departments = new ArrayList<DepartmentModel>();
   }

   public InstituteModel(Institute institute) {
      this.name = institute.getName();
      this.departments = new ArrayList<DepartmentModel>();
//      for(Department department : institute.getDepartments())
//      {
//         this.departments.add(new DepartmentModel(department));
//      }
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
