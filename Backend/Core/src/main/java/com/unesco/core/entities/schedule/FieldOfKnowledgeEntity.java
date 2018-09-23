package com.unesco.core.entities.schedule;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_fieldOfKnowledge")
/**
 * Класс справочник разделов знаний
 */
public class FieldOfKnowledgeEntity {
   @Id
   @SequenceGenerator(name = "fieldOfKnowledgeSequenceGen", sequenceName = "fieldOfKnowledgeSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fieldOfKnowledgeSequenceGen")
   private long id;
   /** Поле название */
   @Column(unique=true)
   private String name;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldOfKnowledge")
   /** Поле дисциплин */
   private Set<DisciplineEntity> discipline;

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

   public Set<DisciplineEntity> getDiscipline() {
      return discipline;
   }
   public void setDiscipline(Set<DisciplineEntity> discipline) {
      this.discipline = discipline;
   }
}
