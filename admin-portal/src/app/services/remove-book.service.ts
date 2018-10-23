import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http: HttpClient) {
  }

  sendBook(id: number) {
    let url = 'http://localhost:8080/book/remove';
    let headers: HttpHeaders = new HttpHeaders({

      'Content-Type': 'application/json',
      'x-auth-token': localStorage.getItem('xAuthToken')
    });
    return this.http.post(url, id, {headers: headers, responseType: 'text'});

  }
}
