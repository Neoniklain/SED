import { Pipe, PipeTransform } from "@angular/core";

@Pipe({ name: "enumKeys" })
export class EnumKeysPipe implements PipeTransform {
    transform(value, args: string[]): any {
        console.log("value: ", value);
        console.log("args: ", args);
        let keys = [];
        for (let enumMember in value) {
            console.log("enumMember", enumMember);
            let isValueProperty = parseInt(enumMember, 10) >= 0;
            if (isValueProperty) {
                keys.push({ key: enumMember, value: value[enumMember] });
                // Uncomment if you want log
                console.log("enum member: ", value[enumMember]);
            }
        }
        return keys;
    }
}