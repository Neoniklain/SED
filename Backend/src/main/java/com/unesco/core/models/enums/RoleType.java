package com.unesco.core.models.enums;

public enum RoleType {

   ADMIN("ADMIN"),
   STUDENT("STUDENT"),
   PROFESSOR("PROFESSOR"),
   ENGINEER("ENGINEER"),
   USER("USER"),
   GUEST("GUEST");

   private final String text;

   RoleType(final String text) {
      this.text = text;
   }

   @Override
   public String toString() {
      return text;
   }
}
