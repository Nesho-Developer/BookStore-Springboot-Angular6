import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {LoginService} from "../../services/login.service";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {PaymentService} from "../../services/payment-service";
import {UserPayment} from "../../models/user-payment";
import {UserBilling} from "../../models/user-billing";
import {AppConst} from "../../constans/app-const";
import {UserShipping} from "../../models/user-shipping";
import {ShippingService} from "../../services/shipping.service";
import {Order} from "../../models/order";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {


  private LoggedIn: boolean;
  private dataFetched = false;
  private loginError = false;
  private credential = {'username': '', 'password': 'password'};
  private user: User = new User();
  private updateSuccess: boolean;
  private newPassword: string;
  private incorrectPassword: boolean;
  private currentPassword: string;

  private selectedProfileTab = 0;
  private selectedBillingTab = 0;
  private selectedShippingTab = 0;

  private userPayment: UserPayment = new UserPayment();
  private userBilling: UserBilling = new UserBilling();
  private userPayments: UserPayment[] = [];
  private defaultPaymentSet: boolean;
  private defaultUserPaymentId = 0;
  private usState: string[] = [];

  private userShipping: UserShipping = new UserShipping();
  private userShippingList: UserShipping[];

  private defaultUserShippingId: number;
  private defaultUserShippingSet: boolean;

  private orderList: Order[] = [];
  private order: Order = new Order();
  private displayOrderDetail: boolean;

  constructor(private loginService: LoginService,
              private orderService: OrderService,
              private  userService: UserService,
              private paymentService: PaymentService,
              private shippingService: ShippingService,
              private router: Router) {
  }

  selectedBillingChange(val: number) {
    this.selectedBillingTab = val;
  }

  selectedShippingChange(val: number) {
    this.selectedShippingTab = val;
  }

  onUpdateUserInfo() {
    this.userService.updateUserInfo(this.user, this.newPassword, this.currentPassword).subscribe(
      res => {

        if (this.newPassword != null && this.newPassword != '') {


          let encodedCredential = btoa(this.user.username + ":" + this.newPassword);
          let basicHeader = 'Basic ' + encodedCredential;
          localStorage.setItem('Authorization', basicHeader);
        }
        this.updateSuccess = true;
        // location.reload();
        // this.router.navigate(['myAccount']);
      },
      err => {
        let errorMessage = err['error'];
        if (errorMessage === 'Incorrect current password') {
          this.incorrectPassword = true;
        }
      }
    );
  }

  getCurrentUser() {
    this.userService.getCurrentUser().subscribe(
      res => {
        this.user = JSON.parse(JSON.stringify(res));
        this.userPayments = this.user.userPayments;
        this.userShippingList = this.user.userShippingList;
        for (let index in this.userPayments) {
          if (this.userPayments[index].defaultPayment) {
            this.defaultUserPaymentId = this.userPayments[index].id;
            break;
          }
        }
        for (let index in this.userShippingList) {
          if (this.userShippingList[index].userShippingDefault) {
            this.defaultUserShippingId = this.userShippingList[index].id;
            break;
          }
        }
        this.dataFetched = true;
      },
      err => {
        console.log(err['error']);
        this.dataFetched = false;
      }
    );
  }

  onNewPayment() {
    this.paymentService.newPayment(this.userPayment).subscribe(
      res => {
        this.getCurrentUser();
        this.selectedBillingTab = 0;
        this.userPayment = new UserPayment();
      },
      err => {
        console.log(err['error']);
      }
    );
  }

  onNewShipping() {
    this.shippingService.newShipping(this.userShipping).subscribe(
      res => {
        this.getCurrentUser();
        this.selectedShippingTab = 0;
        this.userShipping = new UserShipping();

      },
      err => {
        console.log(err['error']);
      }
    );
  }

  onUpdateShipping(shipping: UserShipping) {
    this.userShipping = shipping;
    this.selectedShippingTab = 1;
  }

  onUpdatePayment(payment: UserPayment) {
    this.userPayment = payment;
    this.userBilling = payment.userBilling;
    this.selectedBillingTab = 1;
  }

  onRemoveShipping(id: number) {
    this.shippingService.removeShipping(id).subscribe(
      res => {
        this.getCurrentUser();
      },
      err => {
        console.log(err['error'])
      }
    );
  }

  onRemovePayment(id: number) {
    this.paymentService.removePayment(id).subscribe(
      res => {
        this.getCurrentUser();
      },
      err => {
        console.log(err['error'])
      }
    );
  }

  setDefaultPayment() {
    this.defaultPaymentSet = false;
    this.paymentService.setDefaultPayment(this.defaultUserPaymentId).subscribe(
      res => {
        this.getCurrentUser();
        this.defaultPaymentSet = true;
      },
      err => {
        console.log(err['error'])
      }
    );
  }

  setDefaultShipping() {
    this.defaultUserShippingSet = false;
    this.shippingService.setDefaultShipping(this.defaultUserShippingId).subscribe(
      res => {
        this.getCurrentUser();
        this.defaultUserShippingSet = true;
      },
      err => {
        console.log(err['error'])
      }
    );
  }

  onDisplayOrder(order: Order) {
    this.displayOrderDetail = true;
    this.order = order;

  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.LoggedIn = true;
        console.log('session active');
      },
      err => {
        console.log(err['error']);
        this.LoggedIn = false;
        console.log('inactive session');
        this.router.navigate(['/myAccount']);
      }
    );
    this.getCurrentUser();
    for (let s in AppConst.usStat) {
      this.usState.push(s);
    }
    this.userBilling.userBillingState = "";
    this.userPayment.type = "";
    this.userPayment.expiryMonth = "";
    this.userPayment.expiryYear = "";
    this.userPayment.userBilling = this.userBilling;
    this.defaultPaymentSet = false;

    this.userShipping.userShippingState = "";
    this.userShipping.userShippingDefault = false;
    this.orderService.getOrderList().subscribe(
      res => {
        this.orderList = JSON.parse(JSON.stringify(res));
        console.log(this.orderList);
      },
      err => {
        console.log(err['error']);
      }
    );
  }

}
