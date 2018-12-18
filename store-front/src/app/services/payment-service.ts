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
    return this.http.post(url, payment, { responseType: 'text'});
  }

  getUserPaymentList() {
    let url = 'http://localhost:8080/payment/getUserPayments';
    return this.http.get(url);
  }

  removePayment(id: number) {
    let url = 'http://localhost:8080/payment/remove';

    return this.http.post(url, id, { responseType: 'text'});
  }

  setDefaultPayment(id: number) {
    let url = 'http://localhost:8080/payment/setDefault';

    return this.http.post(url, id);
  }
}
