package com.unesco.core.entities;

import javax.persistence.*;

@Entity
@Table(name="groups")
public class Group {
   @Id
   @SequenceGenerator(name = "groupSequenceGen", sequenceName = "groupSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSequenceGen")
   private long id;
   private String name;
   @ManyToOne
   @JoinColumn(name = "department_id", referencedColumnName = "id")
   /** Поле кафедры */
   private Department department;

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

   public Department getDepartment() {
      return department;
   }
   public void setDepartment(Department department) {
      this.department = department;
   }
}
