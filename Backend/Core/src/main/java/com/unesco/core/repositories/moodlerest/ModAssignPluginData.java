/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unesco.core.repositories.moodlerest;

import java.io.Serializable;

/**
 *
 * @author root
 */
public class ModAssignPluginData implements Serializable {
  private ModAssignFeedbackCommentsEditor assignFeedbackCommentsEditor=null;
  private Long filesFilemanager=null;

  public ModAssignPluginData() {
  }

  public ModAssignFeedbackCommentsEditor getAssignFeedbackCommentsEditor() {
    return assignFeedbackCommentsEditor;
  }

  public void setAssignFeedbackCommentsEditor(ModAssignFeedbackCommentsEditor assignFeedbackCommentsEditor) {
    this.assignFeedbackCommentsEditor = assignFeedbackCommentsEditor;
  }

  public Long getFilesFilemanager() {
    return filesFilemanager;
  }

  public void setFilesFilemanager(Long filesFilemanager) {
    this.filesFilemanager = filesFilemanager;
  }
  
}
