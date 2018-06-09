package com.unesco.core.models.plan;

import com.unesco.core.models.shedule.InstituteModel;

public class DepartmentModel {

   private long id;
   private String name;
   /** Поле Институты */
   private InstituteModel institute;

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

   public InstituteModel getInstitute() {
      return institute;
   }
   public void setInstitute(InstituteModel institute) {
      this.institute = institute;
   }

}