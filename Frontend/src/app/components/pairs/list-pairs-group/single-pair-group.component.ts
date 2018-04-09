import {Component, OnInit, Input} from "@angular/core";
import {Pair} from "../../../models/pair.model";
@Component({
    selector: 'single-pair-group',
    templateUrl: './single-pair-group.component.html',
    // styleUrls: ['./single-news.component.css']
})

export class SinglePairGroupComponent implements OnInit {

    @Input() pair: Pair;

    ngOnInit(): void{

    }
}
