export class PairNumber {
    public num: number;

    constructor(num: number) {
        this.num = num;
    }

    getTime(): PairTime {

        let start: Date = new Date();
        let end: Date = new Date();
        switch (this.num) {
            case 1:
                start.setHours(8);
                start.setMinutes(30);
                end.setHours(10);
                end.setMinutes(5);
                return new PairTime(start, end);
            case 2:
                start.setHours(10);
                start.setMinutes(15);
                end.setHours(11);
                end.setMinutes(50);
                return new PairTime(start, end);
            case 3:
                start.setHours(12);
                start.setMinutes(15);
                end.setHours(13);
                end.setMinutes(50);
                return new PairTime(start, end);
            case 4:
                start.setHours(14);
                start.setMinutes(0);
                end.setHours(15);
                end.setMinutes(35);
                return new PairTime(start, end);
            case 5:
                start.setHours(16);
                start.setMinutes(0);
                end.setHours(17);
                end.setMinutes(35);
                return new PairTime(start, end);
            case 6:
                start.setHours(17);
                start.setMinutes(45);
                end.setHours(19);
                end.setMinutes(20);
                return new PairTime(start, end);
        }

        return null;

    }
}

export class PairTime {
    public start: number;
    public end: number;

    constructor(start, end) {
        this.start = start;
        this.end = end;
    }
}