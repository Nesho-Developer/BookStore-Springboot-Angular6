import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Book} from "../models/book";

@Injectable({
  providedIn: 'root'
})
export class AddNewBookService {

  constructor(private http: HttpClient) {
  }

  sendBook(book: Book) {
    let url = 'http://localhost:8080/book/add';
    let headers: HttpHeaders = new HttpHeaders({

      'Content-Type': 'application/json',
    });
    return this.http.post(url, JSON.stringify(book), {headers: headers});

  }
}
