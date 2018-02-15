package com.unesco.core.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="competence")
/**
 * Справочник компетенций
 */
public class Competence {
   @Id
   @SequenceGenerator(name = "competenceSequenceGen", sequenceName = "competenceSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceSequenceGen")
   private long id;
   /** Поле кода */
   private String code;
   /** Поле описания */
   private String description;
   @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
   @JoinTable(name = "plan_competence",
           joinColumns = {@JoinColumn(name = "plan_id")},
           inverseJoinColumns = {@JoinColumn(name = "competence_id")})
   /** Поле список компетенций */
   private Set<Plan> plan;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public String getCode() {
      return code;
   }
   public void setCode(String code) {
      this.code = code;
   }

   public String getDescription() {
      return description;
   }
   public void setDescription(String description) {
      this.description = description;
   }

   public Competence(String code)
   {
      this.code = code;
   }

   public Competence(String code, String description)
   {
      this.code = code;
      this.description = description;
   }
}
