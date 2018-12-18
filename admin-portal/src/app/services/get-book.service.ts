import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GetBookService {

  constructor(private  http: HttpClient) {
  }

  grtBook(id: number): Observable<any> {
    const url = 'http://localhost:8080/book/' + id;

    return this.http.get(url, {responseType: 'text'});
  }
}
