
import {User} from "./user.model";

export class News {
  public id: number;
  public header: string;
  public author: User;
  public content: string;
  public tags: string;
  public image: string;
  public date: Date;
  constructor()
  {
    this.header = "" ;
    this.author = new User();
    this.content = "" ;
    this.tags = "" ;
    this.image = "";
    this.date = new Date();
  }
}
