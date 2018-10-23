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
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, itemInfo, {headers: headers});
  }

  getCartItemList() {
    let url = 'http://localhost:8080/cart/getCartItemList';

    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.get(url, {headers: headers});
  }

  getShoppingCart() {
    let url = 'http://localhost:8080/cart/getShoppingCart';

    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.get(url, {headers: headers});
  }

  updateCartItem(id: number, qty: number) {
    let url = 'http://localhost:8080/cart/updateCartItem';
    let itemInfo = {
      'bookId': id,
      'qty': qty

    }
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, itemInfo, {headers: headers, responseType: 'text'});
  }

  removeCartItem(id: number) {
    let url = 'http://localhost:8080/cart/removeItem';
    let headers = new HttpHeaders({
        'Authorization': localStorage.getItem('Authorization')
      }
    );
    return this.http.post(url, id, {headers: headers});
  }

}
