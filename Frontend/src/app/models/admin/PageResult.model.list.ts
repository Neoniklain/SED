
export class PageResult {
   public content: Array<any>;
   public totalNumber: number;

   constructor() {
      this.totalNumber = 0;
      this.content = [];
   }
}
