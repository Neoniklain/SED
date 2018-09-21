package com.unesco.core.dto.enums;

public enum TaskStatusType {
   /**
    * В процессе
    */
   Processed("Processed"),
   /**
    * Отклонено
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
    * Отправлено на доработку
    */
   SentToRevision("SentToRevision"),
   /**
    * Отправлено на проверку
    */
   SentToReview("SentToReview");

   private final String text;

   TaskStatusType(final String text) {
      this.text = text;
   }

   /**
    * Получить значение enum по её ID
    * @param id номер enum
    * @return null, если такого объекта нет в enum
    */
   public static TaskStatusType getById(long id) {
      for (TaskStatusType e : values()) {
         if (e.ordinal() == id)
            return e;
      }
      return null;
   }

   @Override
   public String toString() {
      return text;
   }
}
