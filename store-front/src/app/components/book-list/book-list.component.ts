import {Component, OnInit} from '@angular/core';
import {BookService} from "../../services/book.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {Book} from "../../models/book";

@Component({
  selector: 'book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  public filterQuery = "";
  public rowsOnPage = 5;

  private selectedBook: Book;
  private bookList: Book[] = [];


  constructor(private bookService: BookService,
              private http: HttpClient,
              private router: Router,
              private route: ActivatedRoute) {
  }

  onSelectBook(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['bookDetails', this.selectedBook.id]);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {

        if (params['bookList']) {
          this.bookList = JSON.parse((params['bookList']));
        } else {
          this.bookService.getBookList().subscribe(
            res => {
              //console.log(JSON.parse(JSON.stringify(res)));
              this.bookList = JSON.parse(JSON.stringify(res));
            },
            err => {
              console.log(err['error']);
            }
          );
        }
      }
    );
  }

}
