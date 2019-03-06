package com.unesco.core.dto.shedule;

public class GroupDTO {

   private long id;
   private String name;

   /** Поле кафедры */
   private SpecialityDTO speciality;

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

   public SpecialityDTO getSpeciality() {
      return speciality;
   }
   public void setSpeciality(SpecialityDTO speciality) {
      this.speciality = speciality;
   }

   public GroupDTO() {
      this.name = "";
   }

}
