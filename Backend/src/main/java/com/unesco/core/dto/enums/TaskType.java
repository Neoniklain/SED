package com.unesco.core.dto.enums;

public enum TaskType {
   /**
    * Без уведомления
    */
   Info("Info"),
   /**
    * С уведомлением о прочтении
    */
   Notice("Notice"),
   /**
    * С ответом
    */
   Answer("Answer");

   private final String text;

   TaskType(final String text) {
      this.text = text;
   }

   @Override
   public String toString() {
      return text;
   }
}
