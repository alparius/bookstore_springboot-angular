import {Book} from "../shared/book.model";
import {BookService} from "../shared/book.service";

import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {switchMap} from "rxjs/internal/operators/switchMap";


@Component({
    selector: 'app-book-update',
    templateUrl: './book-update.component.html',
    styleUrls: ['./book-update.component.css']
})
export class BookUpdateComponent implements OnInit {
    @Input() book: Book;

    constructor(private bookService: BookService,
                private router: Router,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.bookService.getBook(+params['id'])))
            .subscribe(book => this.book = book);
    }

    save(): void {
        this.bookService.update(this.book)
            .subscribe(_ => this.goBack());
    }

    goBack(): void {
        this.router.navigate(['/books/list-books']);
    }
}
