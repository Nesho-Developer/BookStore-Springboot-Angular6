import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ShippingAddress} from "../models/shipping-adress";
import {BillingAddress} from "../models/billing-address";
import {Payment} from "../models/payment";

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private  http: HttpClient) {
  }

  checkout(shippingAddress: ShippingAddress,
           billingAddress: BillingAddress,
           payment: Payment,
           shippingMethod: string) {
    let url = 'http://localhost:8080/checkOut/checkOut';
    let order = {
      'shippingAddress': shippingAddress,
      'billingAddress': billingAddress,
      'payment': payment,
      'shippingMethod': shippingMethod
    };
    // let order:Object[]=[
    //    ShippingAddress,
    //    BillingAddress,
    //    Payment,
    //     shippingMethod
    // ];
    console.log('@@@', order);
    let headers = new HttpHeaders({
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.post(url, (order), {headers: headers});
  }

  getUserOrder() {
    let url = 'http://localhost:8080/checkOut/getUserOrder';
    let headers = new HttpHeaders({
      'Authorization': localStorage.getItem('Authorization')
    });

    return this.http.get(url, {headers: headers});
  }
}
