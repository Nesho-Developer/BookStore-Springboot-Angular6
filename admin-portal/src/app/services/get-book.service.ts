import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GetBookService {

  constructor(private  http: HttpClient) {
  }

  grtBook(id: number): Observable<any> {
    const url = 'http://localhost:8080/book/' + id;
    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.get(url, {headers: headers, responseType: 'text'});
  }
}
