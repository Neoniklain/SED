import {Component, OnInit, Input} from "@angular/core";
import {Pair} from "../../../models/pair.model";
@Component({
    selector: 'single-pair-professor',
    templateUrl: './single-pair-professor.component.html',
    // styleUrls: ['./single-news.component.css']
})

export class SinglePairProfessorComponent implements OnInit {

    @Input() pair: Pair;

    ngOnInit(): void{

    }
}
