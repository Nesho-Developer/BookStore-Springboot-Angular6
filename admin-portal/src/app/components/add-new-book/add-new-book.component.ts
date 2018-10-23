import {Component, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {AddNewBookService} from "../../services/add-new-book.service";
import {UploadImageService} from "../../services/upload-image.service";


@Component({
  selector: 'app-add-new-book',
  templateUrl: './add-new-book.component.html',
  styleUrls: ['./add-new-book.component.css']
})
export class AddNewBookComponent implements OnInit {


  public newbook: Book = new Book();
  public bookAdded: boolean;
  color = 'accent';
  checked = false;
  disabled = false;

  constructor(private addnewbookservice: AddNewBookService, private uploadImageService: UploadImageService) {
  }

  onSubmit() {

    this.addnewbookservice.sendBook(this.newbook).subscribe(
      data => {
        // let bid:number = JSON.parse(JSON.stringify(data)).id;//JSON.parse(JSON.parse(JSON.stringify(data))._body).id;
        console.log(JSON.parse(JSON.stringify(data)).id, '--------=-==============-===============-');
        this.uploadImageService.upload(JSON.parse(JSON.stringify(data)).id);

        this.bookAdded = true;
        this.newbook = new Book();
        this.init();
      },
      error => {


      }
    );
  }

  init() {
    this.newbook.active = true;
    this.newbook.category = 'Programming';
    this.newbook.language = 'English';
    this.newbook.format = 'PaperBack';
  }

  ngOnInit() {
    this.init();

  }


}
