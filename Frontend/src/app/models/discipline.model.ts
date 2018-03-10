import {FieldOfKnowledgeModel} from "./fieldOfKnowledge.model";
import {DatePipe} from "@angular/common";

export class Discipline {
   id: number;
   name: string;
   datecreate: Date;
   fieldOfKnowledge: FieldOfKnowledgeModel;

   constructor() {
      this.id = 0;
      this.name = "";
      this.datecreate = new Date();
      this.fieldOfKnowledge = new FieldOfKnowledgeModel();
   }
}