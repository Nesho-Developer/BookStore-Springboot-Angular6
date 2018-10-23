import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserPayment} from "../models/user-payment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) {
  }

  newPayment(payment: UserPayment) {
    console.log(payment.expiryYear);
    let url = 'http://localhost:8080/payment/add';
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.post(url, payment, {headers: headers, responseType: 'text'});
  }

  getUserPaymentList() {
    let url = 'http://localhost:8080/payment/getUserPayments';
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.get(url, {headers: headers});
  }

  removePayment(id: number) {
    let url = 'http://localhost:8080/payment/remove';
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.post(url, id, {headers: headers, responseType: 'text'});
  }

  setDefaultPayment(id: number) {
    let url = 'http://localhost:8080/payment/setDefault';
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.post(url, id, {headers: headers});
  }
}
