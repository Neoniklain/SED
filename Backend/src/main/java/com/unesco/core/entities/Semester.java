package com.unesco.core.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="semester")
public class Semester {

   @Id
   @SequenceGenerator(name = "semesterSequenceGen", sequenceName = "semesterSequenceGen", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "semesterSequenceGen")
   private long id;
   /** Поле номера семестра*/
   private int number;
   @ManyToOne
   @JoinColumn(name = "plan_id", referencedColumnName = "id")
   /** Поле плана*/
   private Plan plan;
   /** Поле колличество часов лекций*/
   private int lection_hours;
   /** Поле колличество часов лабороторных*/
   private int laboratory_hours;
   /** Поле колличество часов практик*/
   private int practice_hours;
   /** Поле колличество часов СРС*/
   private int SRS_hours;
   /** Поле колличество часов для контроля*/
   private int control_hours;
   /** Поле колличество часов ЗЕТ*/
   private int ZET_hours;

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

   public int getLection_hours() {
      return lection_hours;
   }
   public void setLection_hours(int lection_hours) {
      this.lection_hours = lection_hours;
   }

   public int getLaboratory_hours() {
      return laboratory_hours;
   }
   public void setLaboratory_hours(int laboratory_hours) {
      this.laboratory_hours = laboratory_hours;
   }

   public int getPractice_hours() {
      return practice_hours;
   }
   public void setPractice_hours(int practice_hours) {
      this.practice_hours = practice_hours;
   }

   public int getSRS_hours() {
      return SRS_hours;
   }
   public void setSRS_hours(int SRS_hours) {
      this.SRS_hours = SRS_hours;
   }

   public int getControl_hours() {
      return control_hours;
   }
   public void setControl_hours(int control_hours) {
      this.control_hours = control_hours;
   }

   public int getZET_hours() {
      return ZET_hours;
   }
   public void setZET_hours(int ZET_hours) {
      this.ZET_hours = ZET_hours;
   }
}
