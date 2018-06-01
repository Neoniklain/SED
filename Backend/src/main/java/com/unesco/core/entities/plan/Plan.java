package com.unesco.core.entities.plan;

import com.unesco.core.entities.schedule.Department;
import com.unesco.core.entities.schedule.Discipline;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_plan")
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
   @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
   @JoinTable(name = "un_plan_competence",
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

   public Set<Competence> getCompetence() {
      return competence;
   }
   public void setCompetence(Set<Competence> competence) {
      this.competence = competence;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
   /** Поле семестра */
   private Set<Semester> semesters;

   public Set<Semester> getSemesters() {
      return semesters;
   }
   public void setSemesters(Set<Semester> semesters) {
      this.semesters = semesters;
   }
}
