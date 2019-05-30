import {BookService} from "../shared/book.service";

import {Component} from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'app-book-new',
    templateUrl: './book-new.component.html',
    styleUrls: ['./book-new.component.css']
})
export class BookNewComponent {

    constructor(private bookService: BookService,
                private router: Router) {
    }

    save(isbn, title, author, publisher, price) {
        console.log("save button pressed", isbn, title, author, publisher, price);

        this.bookService.save(isbn, title, author, publisher, price)
            .subscribe(
                _ => {
                    console.debug("book saved");
                    this.goBack()
                },
                err => console.error("error saving book", err)
            );
    }

    goBack(): void {
        this.router.navigate(['/books/list-books']);
    }
}
