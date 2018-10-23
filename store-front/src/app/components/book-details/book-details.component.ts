import {Component, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {AppConst} from "../../constans/app-const";
import {BookService} from "../../services/book.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {CartService} from "../../services/cart.service";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  private bookId: number;
  private book: Book = new Book();
  private serverPath: string = AppConst.serverPath;
  private numberList: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
  private qty: number;

  private addBookSuccess: boolean = false;
  private notEnoughStouck: boolean;

  constructor(private bookService: BookService,
              private cartService: CartService,
              private http: HttpClient,
              private router: Router,
              private route: ActivatedRoute) {
  }

  onAddToCart() {
    this.cartService.addItem(this.book.id, this.qty).subscribe(
      res => {
        this.addBookSuccess = true;
      },
      err => {
        console.log(err['error']);
      }
    );

  }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.bookId = Number.parseInt(params['id']);
    });
    this.bookService.getBook(this.bookId).subscribe(
      res => {
        //console.log(JSON.parse(JSON.stringify(res));
        this.book = JSON.parse((res));
        console.log(this.book);
      },
      err => {
        console.log('con`t getbook', err['error']);
      }
    );
    this.qty = 1;
  }

}
