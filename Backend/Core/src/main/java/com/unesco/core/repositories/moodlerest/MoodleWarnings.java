/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unesco.core.repositories.moodlerest;

import java.util.ArrayList;

/**
 *
 * @author Bill Antonia
 */
public class MoodleWarnings {
  private ArrayList<MoodleWarning> warnings=null;

  public MoodleWarnings() {
  }

  public ArrayList<MoodleWarning> getWarnings() {
    return warnings;
  }

  public void setWarnings(ArrayList<MoodleWarning> warnings) {
    this.warnings = warnings;
  }
  
  public void addWarning(MoodleWarning warning) {
    if (warnings==null) warnings=new ArrayList<MoodleWarning>();
    warnings.add(warning);
  }
}
