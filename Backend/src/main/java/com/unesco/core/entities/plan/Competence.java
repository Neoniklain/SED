package com.unesco.core.entities.plan;


import com.unesco.core.entities.LongId;
import com.unesco.core.entities.plan.Plan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_competence")
/**
 * Справочник компетенций
 */
public class Competence implements LongId {
   @Id
   @SequenceGenerator(name = "competenceSequenceGen", sequenceName = "competenceSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "competenceSequenceGen")
   private long id;
   /** Поле кода */
   private String code;
   /** Поле описания */
   private String description;
   @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
   @JoinTable(name = "un_plan_competence",
           joinColumns = {@JoinColumn(name = "plan_id")},
           inverseJoinColumns = {@JoinColumn(name = "competence_id")})
   /** Поле список компетенций */
   private Set<Plan> plans;

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
   public Set<Plan> getPlans() {
      return plans;
   }
   public void setPlans(Set<Plan> plans) {
      this.plans = plans;
   }

   public Competence(String code, String description)
   {
      this.code = code;
      this.description = description;
   }
}
