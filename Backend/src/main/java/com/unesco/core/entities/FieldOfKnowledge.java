package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_fieldOfKnowledge")
/**
 * Класс справочник разделов знаний
 */
public class FieldOfKnowledge implements LongId {
   @Id
   @SequenceGenerator(name = "fieldOfKnowledgeSequenceGen", sequenceName = "fieldOfKnowledgeSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fieldOfKnowledgeSequenceGen")
   private long id;
   /** Поле название */
   private String name;
   @OneToMany(cascade = CascadeType.ALL, mappedBy = "fieldOfKnowledge")
   /** Поле дисциплин */
   private Set<Discipline> discipline;

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

   public Set<Discipline> getDiscipline() {
      return discipline;
   }
   public void setDiscipline(Set<Discipline> discipline) {
      this.discipline = discipline;
   }
}
