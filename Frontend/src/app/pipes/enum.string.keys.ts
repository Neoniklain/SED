import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "enumStringKeys" })
export class EnumStringKeysPipe implements PipeTransform {
    transform(value, args: string[]): any {
        let keys = [];
        for (let enumMember in value) {
            keys.push( enumMember );
        }
        return keys;
    }
}



