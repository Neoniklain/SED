/*export class Discipline {
   name: string;
   datecreate: Date;
   fieldOfKnowledge: string;
}
*/
export class Discipline {
   public id:number;
   public discipline:string;

   constructor() {
      this.id = null;
      this.discipline = "";
   }
}
