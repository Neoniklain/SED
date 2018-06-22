package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name="un_group")
public class GroupEntity {
   @Id
   @SequenceGenerator(name = "groupSequenceGen", sequenceName = "groupSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSequenceGen")
   private long id;
   private String name;
   @ManyToOne
   @JoinColumn(name = "department_id", referencedColumnName = "id")
   /** Поле кафедры */
   private DepartmentEntity department;

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

   public DepartmentEntity getDepartmentEntity() {
      return department;
   }
   public void setDepartmentEntity(DepartmentEntity department) {
      this.department = department;
   }
}
