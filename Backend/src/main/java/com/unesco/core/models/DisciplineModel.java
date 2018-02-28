package com.unesco.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unesco.core.entities.Discipline;

import java.util.Date;

public class DisciplineModel {

   /** Поле название */
   private String name;
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
   /** Поле дата создания */
   private Date datecreate;
   /** Поле раздел знаний */
   private String fieldOfKnowledge;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public Date getDatecreate() {
      return datecreate;
   }
   public void setDatecreate(Date datecreate) {
      this.datecreate = datecreate;
   }

   public String getFieldOfKnowledge() {
      return fieldOfKnowledge;
   }
   public void setFieldOfKnowledge(String fieldOfKnowledge) {
      this.fieldOfKnowledge = fieldOfKnowledge;
   }

   public DisciplineModel(Discipline discipline){
      this.name = discipline.getName();
      this.datecreate = discipline.getDatecreate();
      if(discipline.getFieldOfKnowledge() != null)
         this.fieldOfKnowledge = discipline.getFieldOfKnowledge().getName();
   }
   public DisciplineModel(String name, Date datecreate)
   {
      this.name = name;
      this.datecreate = datecreate;
   }
}
