import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {GetBookService} from '../../services/get-book.service';
import {Book} from '../../models/book';
import {UploadImageService} from '../../services/upload-image.service';
import {EditBookService} from '../../services/edit-book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  id: number;
  book: Book = new Book();
  bookUpdated: boolean;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private editBookService: EditBookService,
              private getBookService: GetBookService,
              private uploadImageService: UploadImageService) {
  }

  onSubmit() {
    this.editBookService.sendBook(this.book).subscribe(
      data => {
        this.uploadImageService.modify(JSON.parse(JSON.stringify(data)).id);
        this.bookUpdated = true;
      },
      error1 => {
        console.log('can not edit book');
      }
    );
  }

  ngOnInit() {
    this.route.params.forEach((parmas: Params) => {
      this.id = Number.parseInt(parmas['id']);
    });
    this.getBookService.grtBook(this.id).subscribe(
      data => {
        this.book = JSON.parse((data));
      },
      error1 => {
        console.log('can not load book to edit');
      }
    );
  }

}
