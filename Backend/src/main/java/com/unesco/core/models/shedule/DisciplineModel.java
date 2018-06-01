package com.unesco.core.models.shedule;

public class DisciplineModel {

   private long id;
   /** Поле название */
   private String name;
   /** Поле раздел знаний */
   private FieldOfKnowledgeModel fieldOfKnowledge;



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

   public FieldOfKnowledgeModel getFieldOfKnowledge() {
      return fieldOfKnowledge;
   }
   public void setFieldOfKnowledge(FieldOfKnowledgeModel fieldOfKnowledge) {
      this.fieldOfKnowledge = fieldOfKnowledge;
   }

   public DisciplineModel()
   {
      this.name = "";
      this.fieldOfKnowledge = new FieldOfKnowledgeModel();
   }
   public DisciplineModel(String name)
   {
      this.name = name;
   }
}
