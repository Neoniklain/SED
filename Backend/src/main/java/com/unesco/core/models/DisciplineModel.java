package com.unesco.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unesco.core.entities.Discipline;
import com.unesco.core.models.additional.EntityModel;

import java.util.Date;

public class DisciplineModel implements EntityModel<Discipline> {

   private int id;
   /** Поле название */
   private String name;
   /** Поле дата создания */
   private Date datecreate;
   /** Поле раздел знаний */
   private FieldOfKnowledgeModel fieldOfKnowledge;

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }

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

   public FieldOfKnowledgeModel getFieldOfKnowledge() {
      return fieldOfKnowledge;
   }
   public void setFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge) {
      this.fieldOfKnowledge = fieldOfKnowledge;
   }

   public void EntityToModel(Discipline discipline){
      this.id = (int) discipline.getId();
      this.name = discipline.getName();
      this.datecreate = discipline.getDatecreate();
      if(discipline.getFieldOfKnowledge() != null) {
         FieldOfKnowledgeModel fieldOfKnowledge = new FieldOfKnowledgeModel();
         fieldOfKnowledge.EntityToModel(discipline.getFieldOfKnowledge());
         this.fieldOfKnowledge = fieldOfKnowledge;
      }
   }

   public DisciplineModel()
   {
      this.name = "";
      this.datecreate = new Date();
   }
   public DisciplineModel(String name, Date datecreate)
   {
      this.name = name;
      this.datecreate = datecreate;
   }
}
