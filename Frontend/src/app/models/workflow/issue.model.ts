import {User} from "../account/user.model";

export class Issue {
  public id:number;
  public name:string;
  public creator:User;
  public collaborators:User[];
  constructor()
  {
    this.name = "";
    this.creator = new User();
    this.collaborators = [];
  }
}
