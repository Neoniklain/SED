import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "enumStringKeys" })
export class EnumStringKeysPipe implements PipeTransform {
    transform(value, args: string[]): any {
        let keys = [];
        for (let enumString in value) {
            keys.push({ key: enumString, value: enumString });
            // Uncomment if you want log
            // console.log("enum string: ",enumString);
        }
        return keys;
    }
}




