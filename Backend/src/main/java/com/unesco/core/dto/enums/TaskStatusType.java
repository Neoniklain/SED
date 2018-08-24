package com.unesco.core.dto.enums;

public enum TaskStatusType {
   /**
    * В процессе
    */
   Processed("Processed"),
   /**
    * Отклнено
    */
   Denied("Denied"),
   /**
    * Завершено
    */
   Completed("Completed"),
   /**
    * Проверено
    */
   Checked("Checked"),
   /**
    * Просмотрено
    */
   Viewed("Viewed"),
   /**
    * Отправить на доработку
    */
   SentToRevision("SentToRevision"),
   /**
    * Отправить на проверку
    */
   SentToReview("SentToReview");

   private final String text;

   TaskStatusType(final String text) {
      this.text = text;
   }

   @Override
   public String toString() {
      return text;
   }
}