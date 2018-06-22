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
   /** Поле список компетенций */
   private Set<CompetenceEntity> competence;

   public long getId() {
      return id;
   }
   public void setId(long id) {
      this.id = id;
   }

   public DisciplineEntity getDisciplineEntity() {
      return discipline;
   }
   public void setDisciplineEntity(DisciplineEntity disciplineEntity) {
      this.discipline = disciplineEntity;
   }

   public DepartmentEntity getDepartmentEntity() {
      return department;
   }
   public void setDepartmentEntity(DepartmentEntity departmentEntity) {
      this.department = departmentEntity;
   }

   public String getIndex() {
      return index;
   }
   public void setIndex(String index) {
      this.index = index;
   }

   public Set<CompetenceEntity> getCompetenceEntity() {
      return competence;
   }
   public void setCompetenceEntity(Set<CompetenceEntity> competenceEntity) {
      this.competence = competenceEntity;
   }

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "plan")
   /** Поле семестра */
   private Set<SemesterEntity> semesterEntities;

   public Set<SemesterEntity> getSemesterEntities() {
      return semesterEntities;
   }
   public void setSemesterEntities(Set<SemesterEntity> semesterEntities) {
      this.semesterEntities = semesterEntities;
   }
}
