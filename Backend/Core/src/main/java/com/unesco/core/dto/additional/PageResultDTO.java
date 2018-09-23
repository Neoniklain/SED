package com.unesco.core.dto.additional;

import java.util.List;

public class PageResultDTO<T> {
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

   public PageResultDTO(List<T> content, long total) {
      this.content = content;
      this.totalNumber = total;
   }

}
