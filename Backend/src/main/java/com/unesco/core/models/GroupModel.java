package com.unesco.core.models;

import com.unesco.core.entities.Group;
import com.unesco.core.models.additional.EntityModel;

public class GroupModel implements EntityModel<Group> {
   private String name;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public void EntityToModel(Group group) {
      this.name = group.getName();
   }

   public GroupModel() {
      this.name = "";
   }

}
