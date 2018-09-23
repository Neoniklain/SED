import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "enumKeys" })
export class EnumKeysPipe implements PipeTransform {
    transform(value, args: string[]): any {
        let keys = [];
        for (let enumMember in value) {
            let isValueProperty = parseInt(enumMember, 10) >= 0;
            if (isValueProperty) {
                keys.push({ key: enumMember, value: value[enumMember] });
                // Uncomment if you want log
            }
        }
        return keys;
    }
}