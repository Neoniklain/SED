package com.unesco.core.models;

import com.unesco.core.entities.Group;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GroupModel {
   private int id;
   private String name;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public GroupModel(Group group) {
      this.name = group.getName();
   }

   public GroupModel() {

   }

}
