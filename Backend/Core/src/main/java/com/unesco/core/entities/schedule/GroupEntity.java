package com.unesco.core.entities.schedule;

import javax.persistence.*;

@Entity
@Table(name="un_group")
public class GroupEntity {
   @Id
   @SequenceGenerator(name = "groupSequenceGen", sequenceName = "groupSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groupSequenceGen")
   private long id;

   @Column(unique=true)
   private String name;

   @ManyToOne
   @JoinColumn(name = "speciality_id", referencedColumnName = "id")
   private SpecialityEntity speciality;

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

   public SpecialityEntity getSpeciality() {
      return speciality;
   }
   public void setSpeciality(SpecialityEntity speciality) {
      this.speciality = speciality;
   }
}
