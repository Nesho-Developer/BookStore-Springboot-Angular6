import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {GetBookService} from '../../services/get-book.service';
import {EditBookService} from '../../services/edit-book.service';
import {Book} from '../../models/book';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

  book: Book = new Book();
  id: number;
  imagepath: string;

  constructor(private getBookService: GetBookService,
              private route: ActivatedRoute,
              private router: Router,
              private editBookService: EditBookService) {
  }

  onSelect(book: Book) {
    this.router.navigate(['/editBook/', book.id]);
    //.then(s => location.reload());
  }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.id = Number.parseInt(params['id']);
      this.imagepath = 'http://localhost:8080/image/book/' + Number.parseInt(params['id']) + '.png';
      this.getBookService.grtBook(Number.parseInt(params['id'])).subscribe(
        data => {
          this.book = JSON.parse((data));
        }, error1 => {
          console.log('error to view book', error1);
        }
      );
    });

  }

}
