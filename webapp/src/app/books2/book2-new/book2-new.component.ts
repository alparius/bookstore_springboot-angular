import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {AppState} from "../../app.state";
import {Store} from "@ngrx/store";
import {AddBook} from "../shared/store/book2.actions";
import {Book} from "../../books/shared/book.model";
import {Transaction} from "../../transaction/shared/transaction.model";


@Component({
    selector: 'app-book2-new',
    templateUrl: './book2-new.component.html',
    styleUrls: ['./book2-new.component.css']
})
export class Book2NewComponent {

    constructor(private store: Store<AppState>,
                private router: Router) {
    }

    save(isbn, title, author, publisher, price) {
        let id:number = -1;
        //let book = new Book({id, isbn, title, author, publisher, price});
        //this.store.dispatch(new AddBook(book));
        //setTimeout( _ => this.router.navigate(['/books2/list-books']), 1000);
        this.router.navigate(['/books2/list-books']);
    }
}
