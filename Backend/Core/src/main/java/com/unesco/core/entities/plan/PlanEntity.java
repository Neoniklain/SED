package com.unesco.core.entities.plan;

import com.unesco.core.entities.schedule.DepartmentEntity;
import com.unesco.core.entities.schedule.DisciplineEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="un_plan")
public class PlanEntity {

   @Id
   @SequenceGenerator(name = "planSequenceGen", sequenceName = "planSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planSequenceGen")
   private long id;
   @ManyToOne
   @JoinColumn(name = "discipline_id", referencedColumnName = "id")
   /** Дисциплина*/
   private DisciplineEntity discipline;
   @ManyToOne
   @JoinColumn(name = "department_id", referencedColumnName = "id")
   /** Поле кафедры */
   private DepartmentEntity department;
   /** Поле индекса */
   private String index;
   @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
   @JoinTable(name = "un_plan_competence",
           joinColumns = {@JoinColumn(name = "competence_id")},
           inverseJoinColumns = {@JoinColumn(name = "plan_id")})
   private Set<CompetenceEntity> competence;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public DisciplineEntity getDiscipline() {
      return discipline;
   }
   public void setDiscipline(DisciplineEntity disciplineEntity) {
      this.discipline = disciplineEntity;
   }

   public DepartmentEntity getDepartment() {
      return department;
   }
   public void setDepartment(DepartmentEntity departmentEntity) {
      this.department = departmentEntity;
   }

   public String getIndex() {
      return index;
   }
   public void setIndex(String index) {
      this.index = index;
   }

   public Set<CompetenceEntity> getCompetence() {
      return competence;
   }
   public void setCompetence(Set<CompetenceEntity> competenceEntity) {
      this.competence = competenceEntity;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
   /** Поле семестра */
   private Set<SemesterEntity> semesterEntities;

   public Set<SemesterEntity> getSemester() {
      return semesterEntities;
   }
   public void setSemester(Set<SemesterEntity> semesterEntities) {
      this.semesterEntities = semesterEntities;
   }
}
