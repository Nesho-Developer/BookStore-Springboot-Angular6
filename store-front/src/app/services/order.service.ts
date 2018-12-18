import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private  http: HttpClient) {
  }

  getOrderList() {
    let url = 'http://localhost:8080/order/getOrderList';

    return this.http.get(url);
  }
}
