package com.unesco.core.dto.shedule;

public class GroupDTO {

   private long id;
   private String name;

   /** Поле кафедры */
   private SpecialityDTO specialty;

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

   public SpecialityDTO getSpecialty() {
      return specialty;
   }
   public void setSpecialty(SpecialityDTO specialty) {
      this.specialty = specialty;
   }

   public GroupDTO() {
      this.name = "";
   }

}
