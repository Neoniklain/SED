package com.unesco.core.entities.plan;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_semester")
public class Semester {
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
   private Plan plan;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "semester")
   /** Поле семестра */
   private Set<LessonType> lessonsType;

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

   public Plan getPlan() {
      return plan;
   }
   public void setPlan(Plan plan) {
      this.plan = plan;
   }

   public Set<LessonType> getLessonsType() {
      return lessonsType;
   }
   public void setLessonsType(Set<LessonType> lessonsType) {
      this.lessonsType = lessonsType;
   }

}
