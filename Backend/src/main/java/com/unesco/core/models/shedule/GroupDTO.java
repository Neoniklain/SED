package com.unesco.core.models.shedule;

import com.unesco.core.models.plan.DepartmentDTO;

public class GroupDTO {

   private long id;
   private String name;

   /** Поле кафедры */
   private DepartmentDTO department;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public DepartmentDTO getDepartment() {
      return department;
   }
   public void setDepartment(DepartmentDTO department) {
      this.department = department;
   }

   public GroupDTO() {
      this.name = "";
   }

}
