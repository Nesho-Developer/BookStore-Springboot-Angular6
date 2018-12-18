import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './components/home/home.component';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MyAccountComponent} from './components/my-account/my-account.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatInputModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatTabsModule
} from "@angular/material";
import {MatFormFieldModule} from '@angular/material/form-field';
import {LoginService} from "./services/login.service";
import {UserService} from "./services/user.service";
import {MyProfileComponent} from './components/my-profile/my-profile.component';
import {PaymentService} from "./services/payment-service";
import {ShippingService} from "./services/shipping.service";
import {BookListComponent} from './components/book-list/book-list.component';
import {BookService} from "./services/book.service";
import {DataTableModule} from "angular-6-datatable";
import {DataFilterPipe} from "./components/book-list/data-filter.pipe";
import {BookDetailsComponent} from './components/book-details/book-details.component';
import {ShoppingCartComponent} from './components/shopping-cart/shopping-cart.component';
import {CartService} from "./services/cart.service";
import {OrderComponent} from './components/order/order.component';
import {OrderService} from "./services/order.service";
import {CheckoutService} from "./services/checkout.service";
import {OrderSummaryComponent} from './components/order-summary/order-summary.component';
import {AppInterceptor} from "./AppInterceptor";



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    MyProfileComponent,
    BookListComponent,
    DataFilterPipe,
    BookDetailsComponent,
    ShoppingCartComponent,
    OrderComponent,
    OrderSummaryComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule, MatTabsModule, MatFormFieldModule, MatProgressSpinnerModule,
    AppRoutingModule, FormsModule, HttpClientModule, MatButtonModule, MatInputModule,
    ReactiveFormsModule, MatSelectModule, MatRadioModule, DataTableModule

  ],
  providers: [LoginService, UserService, PaymentService, ShippingService,
    BookService, CartService, OrderService, CheckoutService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptor,
      multi: true
    }

  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
