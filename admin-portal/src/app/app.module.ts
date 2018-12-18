import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {
  MatButtonModule,
  MatIconModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatSlideToggleModule,
  MatSortModule
} from '@angular/material';
import {MatDialogModule} from '@angular/material/dialog';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatTableModule} from '@angular/material/table';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {NavBarComponent} from './components/nav-bar/nav-bar.component';
import {LoginComponent} from './components/login/login.component';

import {LoginService} from './services/login.service';
import {AddNewBookService} from './services/add-new-book.service';
import {AppRouting} from './app.routing';
import {AddNewBookComponent} from './components/add-new-book/add-new-book.component';
import {UploadImageService} from "./services/upload-image.service";
import {BookListComponent, DialogResultExampleDialog} from './components/book-list/book-list.component';
import {GetBookListService} from "./services/get-book-list.service";
import {GetBookService} from "./services/get-book.service";
import {EditBookService} from "./services/edit-book.service";
import {RemoveBookService} from "./services/remove-book.service";
import {ViewBookComponent} from './components/view-book/view-book.component';
import {EditBookComponent} from './components/edit-book/edit-book.component';
import {RouterModule} from "@angular/router";
import {AppInterceptor} from "./AppInterceptor";

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    LoginComponent,
    AddNewBookComponent,
    BookListComponent,
    ViewBookComponent,
    EditBookComponent,
    DialogResultExampleDialog
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    AppRouting, RouterModule,
    MatButtonModule,
    MatCheckboxModule, MatCardModule, MatListModule, MatDialogModule,
    MatTableModule, MatCheckboxModule, MatPaginatorModule, MatProgressSpinnerModule,
    MatSortModule,
    MatIconModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatInputModule,
    MatToolbarModule,
    MatGridListModule
  ],
  providers: [
    LoginService,
    AddNewBookService,
    UploadImageService,
    GetBookListService,
    GetBookService,
    EditBookService,
    RemoveBookService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
  entryComponents: [DialogResultExampleDialog]
})
export class AppModule {
}
