package com.unesco.core.models;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Group;
import com.unesco.core.models.additional.EntityModel;

import java.util.ArrayList;
import java.util.List;

public class DepartmentModel implements EntityModel<Department> {
   private String name;
   /** Поле Группы */
   private List<GroupModel> groups;

   public DepartmentModel() {
      this.groups = new ArrayList<GroupModel>();
   }

   public void EntityToModel(Department department) {
      this.name = department.getName();
      this.groups = new ArrayList<GroupModel>();
      for(Group group : department.getGroups())
      {
         GroupModel groupModel = new GroupModel();
         groupModel.EntityToModel(group);
         this.groups.add(groupModel);
      }
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public List<GroupModel> getGroups() {
      return groups;
   }
   public void setGroups(List<GroupModel> groups) {
      this.groups = groups;
   }

}
