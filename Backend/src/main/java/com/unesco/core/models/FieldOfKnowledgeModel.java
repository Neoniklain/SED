package com.unesco.core.models;

import com.unesco.core.entities.FieldOfKnowledge;
import com.unesco.core.models.additional.EntityModel;

public class FieldOfKnowledgeModel implements EntityModel<FieldOfKnowledge> {
   private String name;

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }

   public void EntityToModel(FieldOfKnowledge fieldOfKnowledge) {
      this.name = fieldOfKnowledge.getName();
   }

   public FieldOfKnowledgeModel() {
      this.name = "";
   }
}
