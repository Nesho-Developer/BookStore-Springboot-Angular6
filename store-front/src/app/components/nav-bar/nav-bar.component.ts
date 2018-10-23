import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../services/login.service";
import {BookService} from "../../services/book.service";
import {Book} from "../../models/book";
import {NavigationExtras, Router} from "@angular/router";

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  private LoggedIn = false;
  private bookList: Book[] = [];
  private keyword: string;

  constructor(private loginService: LoginService,
              private bookService: BookService,
              private router: Router) {
  }

  logout() {
    this.loginService.logout().subscribe(
      data => {
        console.log(data, 'logout!');
        localStorage.removeItem('Authorization');
        location.reload();
      },
      error => {
        console.log(error, 'Ops! logout fail!');

      }
    );
  }

  onSearchByTitle() {
    this.bookService.searchBook(this.keyword).subscribe(
      res => {
        this.bookList = JSON.parse(JSON.stringify(res));
        let navigationExtras: NavigationExtras = {
          queryParams: {
            'bookList': JSON.stringify(this.bookList)
          }
        };
        this.router.navigate(['bookList'], navigationExtras);
      },
      err => {
        console.log(err['error']);
      }
    );
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        console.log(res);
        this.LoggedIn = true;
      },
      err => {
        console.log(err['error']);
        this.LoggedIn = false;
      }
    );


  }
}
