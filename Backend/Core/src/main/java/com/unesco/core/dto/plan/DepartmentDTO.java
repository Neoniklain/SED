package com.unesco.core.dto.plan;

import com.unesco.core.dto.shedule.InstituteDTO;

public class DepartmentDTO {

   private long id;
   private String name;
   /** Поле Институты */
   private InstituteDTO institute;

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

   public InstituteDTO getInstitute() {
      return institute;
   }
   public void setInstitute(InstituteDTO institute) {
      this.institute = institute;
   }

}
