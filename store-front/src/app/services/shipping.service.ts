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
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, shipping, {headers: headers, responseType: 'text'});
  }

  getUserShippingList() {
    let url = 'http://localhost:8080/shipping/getAll';
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.get(url, {headers: headers, responseType: 'text'});
  }

  removeShipping(id: number) {
    let url = 'http://localhost:8080/shipping/remove';
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, id, {headers: headers, responseType: 'text'});
  }

  setDefaultShipping(id: number) {
    let url = 'http://localhost:8080/shipping/setDefault';
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, id, {headers: headers, responseType: 'text'});
  }
}
