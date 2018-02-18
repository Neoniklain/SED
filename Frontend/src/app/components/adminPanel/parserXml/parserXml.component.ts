import {Component, Input, OnChanges, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {FileUploaderOptions, FileUploader} from "ng2-file-upload";
import {AuthenticationService} from "../../../services/authService";

const MB = 1024 * 1024;
const URL = 'http://localhost:8080/api/excel/ParseStudyPlan';

@Component({
   selector: 'parser-xml',
   templateUrl: "./parserXml.component.html",
   styleUrls: ["./parserXml.component.css"],
})

export class ParserXmlComponent implements OnInit, OnChanges {

   public _uploader: FileUploader;
   public _fileOptions: FileUploaderOptions = {
      url: URL,
      maxFileSize: 50 * MB,
      allowedMimeType: [
         'application/msexcel',
         'application/vnd.ms-excel',
         'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' // excell
      ],
      headers: [
         { name: 'Authorization', value: this.authService.getToken() }
      ]
   };

   constructor(private router: Router,
               private authService: AuthenticationService) {
      this._uploader = new FileUploader(this._fileOptions);
   }

   ngOnInit(): void {
   }

   ngOnChanges(): void {
   }

   public tryUpload(item: any) {
      console.log("Try to upload file");
      console.log("FILE = ", item);
      item.upload();
      console.log("End of try");
   }

}
