import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {
  }

  addItem(id: number, qty: number) {
    let url = 'http://localhost:8080/cart/add';
    let itemInfo = {
      'bookId': id,
      'qty': qty

    }

    return this.http.post(url, itemInfo);
  }

  getCartItemList() {
    let url = 'http://localhost:8080/cart/getCartItemList';


    return this.http.get(url);
  }

  getShoppingCart() {
    let url = 'http://localhost:8080/cart/getShoppingCart';


    return this.http.get(url);
  }

  updateCartItem(id: number, qty: number) {
    let url = 'http://localhost:8080/cart/updateCartItem';
    let itemInfo = {
      'bookId': id,
      'qty': qty

    }

    return this.http.post(url, itemInfo, { responseType: 'text'});
  }

  removeCartItem(id: number) {
    let url = 'http://localhost:8080/cart/removeItem';

    return this.http.post(url, id);
  }

}
