package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_institute")
public class Institute {
   @Id
   @SequenceGenerator(name = "instituteSequenceGen", sequenceName = "instituteSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instituteSequenceGen")
   private long id;
   private String name;
//   @OneToMany(cascade = CascadeType.ALL, mappedBy = "institute")
//   /** Поле набор кафедр */
//   private Set<Department> departments;

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
