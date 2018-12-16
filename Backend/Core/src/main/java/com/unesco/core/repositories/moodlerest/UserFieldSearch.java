/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unesco.core.repositories.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public enum UserFieldSearch implements Serializable {
  
  ID("id"),
  IDNUMBER("idnumber"),
  USERNAME("username"),
  EMAIL("email");
  
  private String fieldName;
  
  private UserFieldSearch(String fieldName) {
    this.fieldName=fieldName;
  }

  @Override
  public String toString() {
    return fieldName;
  }
  
}
