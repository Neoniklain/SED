package com.unesco.core.models;

import com.unesco.core.entities.Group;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GroupModel { //видел путь?:D можно бло и не открывать мою*****
   private String name;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public GroupModel(Group group) {
      this.name = group.getName();
   }

   public GroupModel() {

   }

}
