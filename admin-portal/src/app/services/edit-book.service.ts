import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Book} from '../models/book';

@Injectable({
  providedIn: 'root'
})
export class EditBookService {

  constructor(private http: HttpClient) {
  }

  sendBook(book: Book) {
    let url = 'http://localhost:8080/book/update';
    let headers: HttpHeaders = new HttpHeaders({

      'Content-Type': 'application/json',
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, JSON.stringify(book), {headers: headers});

  }
}
