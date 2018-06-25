package com.unesco.core.dto.shedule;


public class InstituteDTO {

   private long id;
   private String name;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public InstituteDTO() {
      this.name = "";
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

}
