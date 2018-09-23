export class FileDescription {
    id:number;
    fileName:string;
    fileType:string;
}

export enum ObjectType {
    /**
     * Описание задачи
     */
    TaskDescription = 0,
    /**
     * Реализация задачи юзером
     */
    TaskUser = 1
}