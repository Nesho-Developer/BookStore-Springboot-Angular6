import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GetBookListService {

  constructor(private http: HttpClient, private router: Router) {
  }

  getBookList(): Observable<any> {
    const url = 'http://localhost:8080/book/book-list';

    return this.http.get(url, {responseType: 'text'});
  }

}
