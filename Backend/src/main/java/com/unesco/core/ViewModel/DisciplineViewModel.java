package com.unesco.core.ViewModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.entities.Plan;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

public class DisciplineViewModel {

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

   public DisciplineViewModel(Discipline discipline){
      this.name = discipline.getName();
      this.datecreate = discipline.getDatecreate();
      if(discipline.getFieldOfKnowledge() != null)
         this.fieldOfKnowledge = discipline.getFieldOfKnowledge().getName();
   }
   public DisciplineViewModel(String name, Date datecreate)
   {
      this.name = name;
      this.datecreate = datecreate;
   }
}
