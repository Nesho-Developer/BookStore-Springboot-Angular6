import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserShipping} from "../models/user-shipping";

@Injectable({
  providedIn: 'root'
})
export class ShippingService {

  constructor(private http: HttpClient) {

  }

  newShipping(shipping: UserShipping) {
    let url = 'http://localhost:8080/shipping/add';

    return this.http.post(url, shipping, { responseType: 'text'});
  }

  getUserShippingList() {
    let url = 'http://localhost:8080/shipping/getAll';

    return this.http.get(url, {responseType: 'text'});
  }

  removeShipping(id: number) {
    let url = 'http://localhost:8080/shipping/remove';

    return this.http.post(url, id, { responseType: 'text'});
  }

  setDefaultShipping(id: number) {
    let url = 'http://localhost:8080/shipping/setDefault';

    return this.http.post(url, id, { responseType: 'text'});
  }
}
