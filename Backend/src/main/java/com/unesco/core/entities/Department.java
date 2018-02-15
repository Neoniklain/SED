package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="department")
/**
 * Класс кафедры
 */
public class Department {

   @Id
   @SequenceGenerator(name = "departmentSequenceGen", sequenceName = "departmentSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departmentSequenceGen")
   private long id;
   /** Поле название */
   private String name;

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

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
   /** Поле набор планов */
   private Set<Plan> plan;

   public Set<Plan> getPlan() {
      return plan;
   }
   public void setPlan(Set<Plan> plan) {
      this.plan = plan;
   }

   public Department(String name)
   {
      this.name = name;
   }
}
