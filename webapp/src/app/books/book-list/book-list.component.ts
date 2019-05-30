import {Book} from "../shared/book.model";
import {BookService} from "../shared/book.service";

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'app-book-list',
    templateUrl: './book-list.component.html',
    styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
    books: Array<Book>;

    constructor(private bookService: BookService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getBooks();
    }

    getBooks() {
        this.bookService.getBooks()
            .subscribe(
                books => this.books = books,
                error => console.error("error getting books", error)
            );
    }

    delete(id: number) {
        this.bookService.delete(id)
            .subscribe(
                _ => {
                    console.debug("book deleted");
                    //this.books = this.books.filter(b => b.id !== id);
                    this.ngOnInit();
                },
                error => console.error(`error deleting book: ${error}`)
            );
    }

    update(id: number) {
        this.router.navigate(['/books/update', id]);
    }
}
