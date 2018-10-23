import {UserPayment} from "./user-payment";
import {UserShipping} from "./user-shipping";

export class User {
  public id: number;
  public firstName: string;
  public lastName: string;
  public username: string;
  public password: string;
  public email: string
  public phone: string;
  public enabled: boolean;
  public userPayments: UserPayment[];
  public userShippingList: UserShipping[];
}
