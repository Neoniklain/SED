package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="plan")
public class Plan {

   @Id
   @SequenceGenerator(name = "planSequenceGen", sequenceName = "planSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planSequenceGen")
   private long id;
   @ManyToOne
   @JoinColumn(name = "discipline_id", referencedColumnName = "id")
   /** Дисциплина*/
   private Discipline discipline;
   @ManyToOne
   @JoinColumn(name = "department_id", referencedColumnName = "id")
   /** Поле кафедры */
   private Department department;
   /** Поле индекса */
   private String index;
   /** Колличество экзаменов */
   private int examsQuantity;
   /** Колличество зачетов */
   private int setoffQuantity;
   /** Колличество зачетов с оценкой*/
   private int setOffWithMarkQuantity;
   /** Колличество курсовых проектов*/
   private int courseProjectQuantity;
   /** Колличество курсовых работ*/
   private int courseWorkQuantity;
   @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
   @JoinTable(name = "plan_competence",
           joinColumns = {@JoinColumn(name = "competence_id")},
           inverseJoinColumns = {@JoinColumn(name = "plan_id")})
   /** Поле список компетенций */
   private Set<Competence> competence;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public Discipline getDiscipline() {
      return discipline;
   }
   public void setDiscipline(Discipline discipline) {
      this.discipline = discipline;
   }

   public Department getDepartment() {
      return department;
   }
   public void setDepartment(Department department) {
      this.department = department;
   }

   public String getIndex() {
      return index;
   }
   public void setIndex(String index) {
      this.index = index;
   }

   public int getExamsQuantity() {
      return examsQuantity;
   }
   public void setExamsQuantity(int examsQuantity) {
      this.examsQuantity = examsQuantity;
   }

   public int getSetoffQuantity() {
      return setoffQuantity;
   }
   public void setSetoffQuantity(int setoffQuantity) {
      this.setoffQuantity = setoffQuantity;
   }

   public int getSetOffWithMarkQuantity() {
      return setOffWithMarkQuantity;
   }
   public void setSetOffWithMarkQuantity(int setOffWithMarkQuantity) {
      this.setOffWithMarkQuantity = setOffWithMarkQuantity;
   }

   public int getCourseProjectQuantity() {
      return courseProjectQuantity;
   }
   public void setCourseProjectQuantity(int courseProjectQuantity) {
      this.courseProjectQuantity = courseProjectQuantity;
   }

   public int getCourseWorkQuantity() {
      return courseWorkQuantity;
   }
   public void setCourseWorkQuantity(int courseWorkQuantity) {
      this.courseWorkQuantity = courseWorkQuantity;
   }

   public Set<Competence> getCompetence() {
      return competence;
   }
   public void setCompetence(Set<Competence> competence) {
      this.competence = competence;
   }

   @ManyToOne
   @JoinColumn(name = "semester_id", referencedColumnName = "id")
   /** Поле семестра */
   private Semester semester;

   public Semester getSemester() {
      return semester;
   }
   public void setSemester(Semester semester) {
      this.semester = semester;
   }
}
