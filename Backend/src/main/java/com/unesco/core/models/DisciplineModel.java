package com.unesco.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unesco.core.entities.Discipline;

import java.util.Date;

public class DisciplineModel {

   /** Поле название */
   private int id;
   private String discipline;
//   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT")
//   /** Поле дата создания */
//   private Date datecreate;
//   /** Поле раздел знаний */
//   private String fieldOfKnowledge;

   public String getName() {
      return discipline;
   }
   public void setName(String name) {
      this.discipline = name;
   }

//   public Date getDatecreate() {
//      return datecreate;
//   }
//   public void setDatecreate(Date datecreate) {
//      this.datecreate = datecreate;
//   }
//
//   public String getFieldOfKnowledge() {
//      return fieldOfKnowledge;
//   }
//   public void setFieldOfKnowledge(String fieldOfKnowledge) {
//      this.fieldOfKnowledge = fieldOfKnowledge;
//   }

   public DisciplineModel(Discipline discipline){
      this.discipline = discipline.getName();
//      if(discipline.getFieldOfKnowledge() != null)
//         this.fieldOfKnowledge = discipline.getFieldOfKnowledge().getName();
   }
   public DisciplineModel(String name/*, Date datecreate*/)
   {
      this.discipline = name;
//      this.datecreate = datecreate;
   }
}
