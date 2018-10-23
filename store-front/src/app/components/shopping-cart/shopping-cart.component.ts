import {Component, OnInit} from '@angular/core';
import {Book} from "../../models/book";
import {CartItem} from "../../models/cart-item";
import {ShoppingCart} from "../../models/shopping-cart";
import {CartService} from "../../services/cart.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  private selectedBook: Book;
  private cartItemList: CartItem[] = [];
  private cartItemNumber: number;
  private shoppingCart: ShoppingCart = new ShoppingCart();
  private cartItemUpdated: boolean;
  private emptyCart: boolean;
  private noEnoughStouck: boolean;

  constructor(private cartService: CartService, private  router: Router) {
  }

  onSelectBook(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['bookDetails', this.selectedBook.id]);
  }

  onRemoveCartItem(cartItem: CartItem) {
    this.cartService.removeCartItem(cartItem.id).subscribe(
      res => {
        this.getCartItemList();
        this.getShoppingCart();
      },
      err => {
        console.log(err['error']);
      }
    );
  }

  onUpdateCartItem(cartItem: CartItem) {
    this.cartItemUpdated = false;
    this.noEnoughStouck = false;
    this.emptyCart = false;
    if (cartItem.qty > cartItem.book.inStockNumber) {
      this.noEnoughStouck = true;
    } else {
      this.cartService.updateCartItem(cartItem.id, cartItem.qty).subscribe(
        res => {
          this.cartItemUpdated = true;
          this.getShoppingCart();
        },
        err => {
          console.log(err['error']);
        }
      );
    }

  }

  getCartItemList() {
    this.cartService.getCartItemList().subscribe(
      res => {
        this.cartItemList = JSON.parse(JSON.stringify(res));
        this.cartItemNumber = this.cartItemList.length;

      },
      err => {
        console.log(err['error']);
      }
    );
  }

  getShoppingCart() {
    this.cartService.getShoppingCart().subscribe(
      res => {
        this.shoppingCart = JSON.parse(JSON.stringify(res));
      },
      err => {
        console.log(err['error']);
      }
    );
  }

  onCheckout() {
    if (this.cartItemNumber == 0) {
      this.emptyCart = true;
    } else {
      for (let item of this.cartItemList) {
        if (item.qty > item.book.inStockNumber) {
          this.noEnoughStouck = true;
          return;
        }
      }
    }
    //this.router.navigate(['/order']);
  }

  ngOnInit() {
    this.getCartItemList();
    this.getShoppingCart();
  }

}
