import {User} from './user';

export class ShoppingCart {
  public id: number;
  public grandTotal: number = 0;
  public user: User;
}
