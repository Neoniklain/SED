package com.unesco.core.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unesco.core.entities.Discipline;
import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.models.additional.EntityModel;

import java.util.Date;

public class DisciplineModel {

   private int id;
   /** Поле название */
   private String name;
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

   public FieldOfKnowledgeModel getFieldOfKnowledge() {
      return fieldOfKnowledge;
   }
   public void setFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge) {
      this.fieldOfKnowledge = fieldOfKnowledge;
   }

   public DisciplineModel()
   {
      this.name = "";
   }
   public DisciplineModel(String name)
   {
      this.name = name;
   }
}
