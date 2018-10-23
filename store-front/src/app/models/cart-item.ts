import {Book} from './book';
import {ShoppingCart} from './shopping-cart';

export class CartItem {
  public id: number;
  public subtotal: number;
  public qty: number;
  public book: Book;
  public shoppingCart: ShoppingCart
  public toUpdate: boolean;
}
