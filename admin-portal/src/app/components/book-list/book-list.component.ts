import {Component, OnInit} from '@angular/core';
import {GetBookListService} from "../../services/get-book-list.service";
import {Router} from "@angular/router";
import {Book} from '../../models/book';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {RemoveBookService} from "../../services/remove-book.service";
import {SelectionModel} from '@angular/cdk/collections';


@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})

export class BookListComponent implements OnInit {

  checkList = [];

  bookList: Book[];
  bookListSize: number;
  datasource: any;
  selection = new SelectionModel<Book>(true, []);
  displayedColumns: string[] = ['select', 'title', 'author',
    'category', 'listPrice', 'ourPrice', 'active', 'oPerations'];

  constructor(private getBookListService: GetBookListService,
              private router: Router,
              public dialog: MatDialog,
              private removeBookService: RemoveBookService) {
  }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.bookListSize;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.datasource.forEach(row => this.selection.select(row));
  }

  openDialog(book: Book) {
    let dialogRef = this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      data => {
        if (data == 'yes') {
          console.log(book.id);
          this.removeBookService.sendBook(book.id).subscribe(
            data => {
              console.log(data, 'oooooo');
              //location.reload();
              this.getBook();

            },
            error1 => {
              console.log(error1, 'not emove book');
            }
          );
        }
      }, error1 => {

      }
    );
  }

  getBook() {
    this.getBookListService.getBookList().subscribe(
      data => {
        this.bookList = JSON.parse(data);
        this.datasource = JSON.parse(data); //Object.entries(this.bookList).map(([type, value]) => ({type, value}));;
        this.bookListSize = this.bookList.length;
      },
      error1 => {
        console.log(error1, 'ERROR');
      }
    );
  }

  ngOnInit() {
    this.getBook();
  }

  removeBookList() {
    let dialogRef = this.dialog.open(DialogResultExampleDialog);
    dialogRef.afterClosed().subscribe(
      data => {
        if (data == 'yes') {
          for (let book of this.selection.selected) {
            this.removeBookService.sendBook(book.id).subscribe(
              data => {
                console.log(data, 'oooooo');
                //location.reload();
                this.selection.clear();
                this.getBook();
              },
              error1 => {
                console.log(error1, 'not emove book');
              }
            );
          }
        }
      }, error1 => {

      }
    );
  }
}


@Component({
  selector: 'dialog-result-example-dialog',
  templateUrl: 'dialog-result-example-dialog.html'
})
export class DialogResultExampleDialog {
  constructor(public dialogRef: MatDialogRef<DialogResultExampleDialog>) {
  }

}
