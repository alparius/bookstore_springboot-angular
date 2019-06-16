import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {Book} from "../../books/shared/book.model";
import {AppState} from "../../app.state";
import {Store} from "@ngrx/store";
import {GetBook, UpdateBook} from "../shared/store/book2.actions";
import {getBook} from "../shared/store/book2.reducers";


@Component({
    selector: 'app-book2-update',
    templateUrl: './book2-update.component.html',
    styleUrls: ['./book2-update.component.css']
})
export class Book2UpdateComponent implements OnInit {
    @Input() book: Book;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private store: Store<AppState>) {
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.store.dispatch(new GetBook(+params['id']));
        });
        this.store.select(getBook).subscribe(book => {
            if (book != null) {
                this.book = book;
            }
        });
    }

    save(): void {
        this.store.dispatch(new UpdateBook(this.book));
        //setTimeout( _ => this.goBack(), 1000);
        this.goBack();
    }

    goBack(): void {
        this.router.navigate(['/books2/list-books']);
    }
}
