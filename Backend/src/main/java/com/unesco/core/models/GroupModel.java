package com.unesco.core.models;

public class GroupModel {

   private long id;
   private String name;

   /** Поле кафедры */
   private DepartmentModel department;

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

   public DepartmentModel getDepartment() {
      return department;
   }
   public void setDepartment(DepartmentModel department) {
      this.department = department;
   }

   public GroupModel() {
      this.name = "";
   }

}
