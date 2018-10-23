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
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.get(url, {headers: headers}).pipe(
      // catchError()
    );
  }

  getBook(id: number) {
    let url = 'http://localhost:8080/book/' + id;
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.get(url, {headers: headers, responseType: 'text'});
  }

  searchBook(keyWord: string) {
    let url = 'http://localhost:8080/book/searchBook';
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, keyWord, {headers: headers});
  }
}
