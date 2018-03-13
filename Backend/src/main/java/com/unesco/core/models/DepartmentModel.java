package com.unesco.core.models;

import com.unesco.core.entities.Department;
import com.unesco.core.entities.Group;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DepartmentModel {
   private String name;
   /** Поле Группы */
   private List<GroupModel> groups;

   DepartmentModel() {
      this.groups = new ArrayList<GroupModel>();
   }

   public DepartmentModel(Department department) {
      this.name = department.getName();
//      this.groups = new ArrayList<GroupModel>();
//      for(Group group : department.getGroups())
//      {
//         this.groups.add(new GroupModel(group));
//      }
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
