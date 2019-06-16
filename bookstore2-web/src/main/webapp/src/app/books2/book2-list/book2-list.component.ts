import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Store} from "@ngrx/store";
import {Book} from "../../books/shared/book.model";
import {Observable} from "rxjs";
import {AppState} from "../../app.state";
import {getAllBooks} from "../shared/store/book2.reducers";
import * as bookActions from "../shared/store/book2.actions";
import {GetAllBooks} from "../shared/store/book2.actions";


@Component({
    selector: 'app-book2-list',
    templateUrl: './book2-list.component.html',
    styleUrls: ['./book2-list.component.css']
})
export class Book2ListComponent implements OnInit {

    public books: Observable<Book[]>;

    constructor(private store: Store<AppState>,
                private router: Router) {
    }

    ngOnInit(): void {
        setTimeout( _ => {
            this.store.dispatch(new GetAllBooks());
            this.books = this.store.select(getAllBooks);
            },1000);
    }

    delete(id: number) {
        if (confirm('sure delete this?')) {
            this.store.dispatch(new bookActions.RemoveBook(id));
        }
        //setTimeout( _ => this.ngOnInit(), 1000);
        this.ngOnInit();
    }

    update(id: number) {
        this.router.navigate(['/books2/update', id]);
    }
}
