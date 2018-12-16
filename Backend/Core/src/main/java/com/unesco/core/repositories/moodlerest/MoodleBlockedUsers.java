/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unesco.core.repositories.moodlerest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Bill Antonia
 */
public class MoodleBlockedUsers extends MoodleWarnings implements Serializable {
  private ArrayList<MoodleUser> users=null;

  public MoodleBlockedUsers() {
  }

  public ArrayList<MoodleUser> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<MoodleUser> users) {
    this.users = users;
  }
  
  public void addUser(MoodleUser user) {
    if (users==null) users=new ArrayList<MoodleUser>();
    users.add(user);
  }
}
