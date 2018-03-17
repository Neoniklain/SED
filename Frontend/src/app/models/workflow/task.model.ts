import {User} from "../account/user.model";

export class Task {
  public id: number;
  public name: string;
  public creator: User;
  public collaborators: User[];
  constructor()
  {
    this.name = "";
    this.creator = new User();
    this.collaborators = [];
  }
}
