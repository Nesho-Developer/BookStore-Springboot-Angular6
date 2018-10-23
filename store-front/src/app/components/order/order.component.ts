import {Component, OnInit} from '@angular/core';
import {AppConst} from "../../constans/app-const";
import {Book} from "../../models/book";
import {CartItem} from "../../models/cart-item";
import {ShoppingCart} from "../../models/shopping-cart";
import {ShippingAddress} from "../../models/shipping-adress";
import {BillingAddress} from "../../models/billing-address";
import {UserPayment} from "../../models/user-payment";
import {UserShipping} from "../../models/user-shipping";
import {UserBilling} from "../../models/user-billing";
import {Payment} from "../../models/payment";
import {Order} from "../../models/order";
import {NavigationExtras, Router} from "@angular/router";
import {CartService} from "../../services/cart.service";
import {ShippingService} from "../../services/shipping.service";
import {PaymentService} from "../../services/payment-service";
import {CheckoutService} from "../../services/checkout.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {
  private serverPath = AppConst.serverPath;
  private selectedBook: Book;
  private cartItemList: CartItem[] = [];
  private cartItemNumber: number;
  private shoppingCart: ShoppingCart = new ShoppingCart();
  private cartItemUpdated: boolean;
  private shippingAddress: ShippingAddress = new ShippingAddress();
  private billingAddress: BillingAddress = new BillingAddress();
  private userPayment: UserPayment = new UserPayment();
  private userShipping: UserShipping = new UserShipping();
  private userBilling: UserBilling = new UserBilling();
  private userShippingList: UserShipping[] = [];
  private userPaymentList: UserPayment[] = [];
  private payment: Payment = new Payment();
  private selectedTab: number;
  private emptyShippingList: boolean = true;
  private emptyPaymentList: boolean = true;
  private stateList: string[] = [];
  private shippingMethod: string;
  private order: Order = new Order();

  constructor(
    private router: Router,
    private cartService: CartService,
    private shippingService: ShippingService,
    private paymentService: PaymentService,
    private checkoutService: CheckoutService
  ) {
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

  selectedChange(val: number) {
    this.selectedTab = val;
  }

  goToPayment() {
    this.selectedTab = 1;
  }

  goToReview() {
    this.selectedTab = 2;
  }

  getCartItemList() {
    this.cartService.getCartItemList().subscribe(
      res => {
        this.cartItemList = JSON.parse(JSON.stringify(res));
        this.cartItemNumber = this.cartItemList.length;
      },
      error => {
        console.log(error['error']);
      }
    );
  }

  setShippingAddress(userShipping: UserShipping) {
    this.shippingAddress.shippingAddressName = userShipping.userShippingName;
    this.shippingAddress.shippingAddressStreet1 = userShipping.userShippingStreet1;
    this.shippingAddress.shippingAddressStreet2 = userShipping.userShippingStreet2;
    this.shippingAddress.shippingAddressCity = userShipping.userShippingCity;
    this.shippingAddress.shippingAddressState = userShipping.userShippingState;
    this.shippingAddress.shippingAddressCountry = userShipping.userShippingCountry;
    this.shippingAddress.shippingAddressZipcode = userShipping.userShippingZipcode;
  }

  setPaymentMethod(userPayment: UserPayment) {
    this.payment.type = userPayment.type;
    this.payment.cardNumber = userPayment.cardNumber;
    this.payment.expiryMonth = userPayment.expiryMonth;
    this.payment.expiryYear = userPayment.expiryYear;
    this.payment.cvc = userPayment.cvc;
    this.payment.holderName = userPayment.holderName;
    this.payment.defaultPayment = userPayment.defaultPayment;
    this.billingAddress.billingAddressName = userPayment.userBilling.userBillingName;
    this.billingAddress.billingAddressStreet1 = userPayment.userBilling.userBillingStreet1;
    this.billingAddress.billingAddressStreet2 = userPayment.userBilling.userBillingStreet2;
    this.billingAddress.billingAddressCity = userPayment.userBilling.userBillingCity;
    this.billingAddress.billingAddressState = userPayment.userBilling.userBillingState;
    this.billingAddress.billingAddressCountry = userPayment.userBilling.userBillingCountry;
    this.billingAddress.billingAddressZipcode = userPayment.userBilling.userBillingZipcode;
  }

  setBillingAsShipping(checked: boolean) {
    console.log("same as shipping")

    if (checked) {
      this.billingAddress.billingAddressName = this.shippingAddress.shippingAddressName;
      this.billingAddress.billingAddressStreet1 = this.shippingAddress.shippingAddressStreet1;
      this.billingAddress.billingAddressStreet2 = this.shippingAddress.shippingAddressStreet2;
      this.billingAddress.billingAddressCity = this.shippingAddress.shippingAddressCity;
      this.billingAddress.billingAddressState = this.shippingAddress.shippingAddressState;
      this.billingAddress.billingAddressCountry = this.shippingAddress.shippingAddressCountry;
      this.billingAddress.billingAddressZipcode = this.shippingAddress.shippingAddressZipcode;
    } else {
      this.billingAddress.billingAddressName = "";
      this.billingAddress.billingAddressStreet1 = "";
      this.billingAddress.billingAddressStreet2 = "";
      this.billingAddress.billingAddressCity = "";
      this.billingAddress.billingAddressState = "";
      this.billingAddress.billingAddressCountry = "";
      this.billingAddress.billingAddressZipcode = "";
    }
  }

  onSubmit() {
    console.log(this.shippingAddress,
      this.billingAddress,
      this.payment,
      this.shippingMethod);
    this.checkoutService.checkout(
      this.shippingAddress,
      this.billingAddress,
      this.payment,
      this.shippingMethod
    ).subscribe(
      res => {
        this.order = JSON.parse(JSON.stringify(res));
        console.log('#########################3');
        console.log('&&&&&&&', this.order);

        let navigationExtras: NavigationExtras = {
          queryParams: {
            "order": JSON.stringify(this.order)
          }
        };

        this.router.navigate(['/orderSummary'], navigationExtras);
      },
      error => {
        console.log(error['error']);
      }
    );
  }

  ngOnInit() {
    this.getCartItemList();


    this.cartService.getShoppingCart().subscribe(
      res => {
        console.log(JSON.parse(JSON.stringify(res)));
        this.shoppingCart = JSON.parse(JSON.stringify(res));
      },
      error => {
        console.log(error['error']);
      }
    );

    this.shippingService.getUserShippingList().subscribe(
      res => {
        console.log(JSON.parse(JSON.stringify(res)));
        this.userShippingList = JSON.parse((res));
        if (this.userShippingList.length) {
          this.emptyShippingList = false;

          for (let userShipping of this.userShippingList) {
            if (userShipping.userShippingDefault) {
              this.setShippingAddress(userShipping);
              return;
            }
          }
        }
      },
      error => {
        console.log(error['error']);
      }
    );

    this.paymentService.getUserPaymentList().subscribe(
      res => {
        console.log(JSON.parse(JSON.stringify(res)));
        this.userPaymentList = JSON.parse(JSON.stringify(res));
        this.emptyPaymentList = false;

        if (this.userPaymentList.length) {
          this.emptyPaymentList = false;

          for (let userPayment of this.userPaymentList) {
            if (userPayment.defaultPayment) {
              this.setPaymentMethod(userPayment);
              return;
            }
          }
        }
      },
      error => {
        console.log(error['error']);
      }
    );

    for (let s in AppConst.usStat) {
      this.stateList.push(s);
    }

    this.payment.type = "";
    this.payment.expiryMonth = "";
    this.payment.expiryYear = "";
    this.billingAddress.billingAddressState = "";
    this.shippingAddress.shippingAddressState = "";
    this.shippingMethod = "groundShipping";
  }

}
