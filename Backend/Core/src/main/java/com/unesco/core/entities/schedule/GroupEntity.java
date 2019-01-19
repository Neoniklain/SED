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
   @JoinColumn(name = "specialty_id", referencedColumnName = "id")
   private SpecialityEntity specialty;

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

   public SpecialityEntity getSpecialty() {
      return specialty;
   }
   public void setSpecialty(SpecialityEntity specialty) {
      this.specialty = specialty;
   }
}
