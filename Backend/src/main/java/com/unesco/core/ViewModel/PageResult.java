package com.unesco.core.ViewModel;

import java.util.List;

public class PageResult<T> {
   private List<T> content;
   private long totalNumber;

   public List<T> getContent() {
      return content;
   }
   public void setContent(List<T> content) {
      this.content = content;
   }

   public long getTotalNumber() {
      return totalNumber;
   }
   public void setTotalNumber(long totalNumber) {
      this.totalNumber = totalNumber;
   }

   public PageResult(List<T> content, long total) {
      this.content = content;
      this.totalNumber = total;
   }

}
