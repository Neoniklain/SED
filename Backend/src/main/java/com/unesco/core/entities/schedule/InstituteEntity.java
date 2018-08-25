package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name="un_institute")
public class InstituteEntity {
   @Id
   @SequenceGenerator(name = "instituteSequenceGen", sequenceName = "instituteSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituteSequenceGen")
   private long id;
   @Column(unique=true)
   private String name;
//   @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
//   /** Поле набор кафедр */
//   private Set<DepartmentEntity> departments;

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
}
