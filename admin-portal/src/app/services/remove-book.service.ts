import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RemoveBookService {

  constructor(private http: HttpClient) {
  }

  sendBook(id: number) {
    let url = 'http://localhost:8080/book/remove';

    return this.http.post(url, id, {responseType: 'text'});

  }
}
