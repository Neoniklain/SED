package com.unesco.core.models.additional;

public class FilterQueryDTO {
   private String[] filters;
   private int first;
   private String globalFilter;
   private boolean multiSortMeta;
   private int rows;
   private String sortField;
   private int sortOrder;

   public String[] getFilters() {
      return filters;
   }
   public void setFilters(String[] filters) {
      this.filters = filters;
   }

   public int getFirst() {
      return first;
   }
   public void setFirst(int first) {
      this.first = first;
   }

   public String getGlobalFilter() {
      return globalFilter;
   }
   public void setGlobalFilter(String globalFilter) {
      this.globalFilter = globalFilter;
   }

   public boolean isMultiSortMeta() {
      return multiSortMeta;
   }
   public void setMultiSortMeta(boolean multiSortMeta) {
      this.multiSortMeta = multiSortMeta;
   }

   public int getRows() {
      return rows;
   }
   public void setRows(int rows) {
      this.rows = rows;
   }

   public String getSortField() {
      return sortField;
   }
   public void setSortField(String sortField) {
      this.sortField = sortField;
   }

   public int getSortOrder() {
      return sortOrder;
   }
   public void setSortOrder(int sortOrder) {
      this.sortOrder = sortOrder;
   }

   public FilterQueryDTO(){
      first = 0;
      globalFilter = "";
      multiSortMeta = false;
      rows = 0;
      sortField = "";
      sortOrder = 0;
   }
}
