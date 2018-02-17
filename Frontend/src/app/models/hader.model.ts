export class HeaderLink {
  link: string;
  icon: string;
  content: string;
  constructor(content, link, icon?) {
     if (icon) this.icon = icon;
     this.link = link;
     this.content = content;
  }
}
