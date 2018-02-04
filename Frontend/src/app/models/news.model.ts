
export class News {
  public id:number;
  public header:string;
  public author:string;
  public content:string;
  public tags:string;
  public image:string;
  public date:Date;
  constructor()
  {
    this.header="";
    this.author="";
    this.content="";
    this.tags="";
    this.image="";
    this.date= new Date();
  }
}
