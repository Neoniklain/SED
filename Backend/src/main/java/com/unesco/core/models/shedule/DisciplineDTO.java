package com.unesco.core.models.shedule;

public class DisciplineDTO {

   private long id;
   /** Поле название */
   private String name;
   /** Поле раздел знаний */
   private FieldOfKnowledgeDTO fieldOfKnowledge;



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

   public FieldOfKnowledgeDTO getFieldOfKnowledge() {
      return fieldOfKnowledge;
   }
   public void setFieldOfKnowledge(FieldOfKnowledgeDTO fieldOfKnowledge) {
      this.fieldOfKnowledge = fieldOfKnowledge;
   }

   public DisciplineDTO()
   {
      this.name = "";
      this.fieldOfKnowledge = new FieldOfKnowledgeDTO();
   }
   public DisciplineDTO(String name)
   {
      this.name = name;
   }
}
