import {Component, OnInit} from '@angular/core';
import {Order} from "../../models/order";
import {CartItem} from "../../models/cart-item";
import {CheckoutService} from "../../services/checkout.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Book} from "../../models/book";

@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.css']
})
export class OrderSummaryComponent implements OnInit {

  private order: Order;
  private estimatedDeliveryDate: string;
  private cartItemList: CartItem[] = [];

  constructor(private checkoutService: CheckoutService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  onSelectBook(book: Book) {
    this.router.navigate(['bookDetails', book.id]);
  }

  ngOnInit() {
    this.route.queryParams.subscribe(
      params => {
        this.order = JSON.parse(params['order']);
        let deleveryDate = new Date();
        if (this.order.shippingMethod === 'groundShipping') {
          deleveryDate.setDate(deleveryDate.getDate() + 5);
        } else {
          deleveryDate.setDate(deleveryDate.getDate() + 3);
        }
        let days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
        this.estimatedDeliveryDate = days[deleveryDate.getDay()] +
          ',' + deleveryDate.getFullYear() + '/' + deleveryDate.getMonth() + '/' + deleveryDate.getDate();
        this.cartItemList = this.order.cartItemList;

      }
    );
  }

}
