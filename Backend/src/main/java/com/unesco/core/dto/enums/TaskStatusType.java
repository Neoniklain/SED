package com.unesco.core.dto.enums;

public enum TaskStatusType {

   Processed("Processed"),
   Denied("Denied"),
   Completed("Completed"),
   Checked("Checked"),
   Viewed("Viewed"),
   SentToRevision("SentToRevision"),
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
