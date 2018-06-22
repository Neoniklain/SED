package com.unesco.core.entities.plan;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_semester")
public class SemesterEntity {
   @Id
   @SequenceGenerator(name = "semesterSequenceGen", sequenceName = "semesterSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semesterSequenceGen")
   private long id;
   /** Поле номера семестра*/
   private int number;
   /** Поле типа контроля (экзамен зачет и т.д.)*/
   private String control_type;
   @ManyToOne
   @JoinColumn(name = "plan_id", referencedColumnName = "id")
   /** Поле плана*/
   private PlanEntity plan;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester")
   /** Поле семестра */
   private Set<LessonTypeEntity> lessonsType;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public int getNumber() {
      return number;
   }
   public void setNumber(int number) {
      this.number = number;
   }

   public PlanEntity getPlanEntity() {
      return plan;
   }
   public void setPlanEntity(PlanEntity planEntity) {
      this.plan = planEntity;
   }

   public Set<LessonTypeEntity> getLessonsType() {
      return lessonsType;
   }
   public void setLessonsType(Set<LessonTypeEntity> lessonsType) {
      this.lessonsType = lessonsType;
   }

}
