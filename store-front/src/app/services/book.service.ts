import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {
  }

  getBookList() {
    let url = 'http://localhost:8080/book/book-list';
    return this.http.get(url).pipe(
      // catchError()
    );
  }

  getBook(id: number) {
    let url = 'http://localhost:8080/book/' + id;
    return this.http.get(url, { responseType: 'text'});
  }

  searchBook(keyWord: string) {
    let url = 'http://localhost:8080/book/searchBook';
    return this.http.post(url, keyWord);
  }
}
