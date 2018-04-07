package com.unesco.core.models;


import com.unesco.core.entities.Department;
import com.unesco.core.entities.Institute;
import com.unesco.core.models.additional.EntityModel;

import java.util.ArrayList;
import java.util.List;

public class InstituteModel {

   private long id;
   private String name;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public InstituteModel() {
      this.name = "";
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

}
