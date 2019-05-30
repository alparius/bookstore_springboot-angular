import {BookService} from "../../books/shared/book.service";
import {ClientService} from "../../clients/shared/client.service";
import {TransactionService} from "../shared/transaction.service";
import {Book} from "../../books/shared/book.model";
import {Client} from "../../clients/shared/client.model";

import {Router} from "@angular/router";
import {Component, OnInit} from '@angular/core';


@Component({
    selector: 'app-transaction-new',
    templateUrl: './transaction-new.component.html',
    styleUrls: ['./transaction-new.component.css']
})
export class TransactionNewComponent implements OnInit {
    clients: Array<Client>;
    books: Array<Book>;

    currentClient: Client;
    currentBook: Book;

    constructor(private bookService: BookService,
                private clientService: ClientService,
                private transactionService: TransactionService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getClients();
        this.getBooks();
    }

    getClients() {
        this.clientService.getClients()
            .subscribe(
                clients => this.clients = clients,
                error => console.error("error getting clients", error)
            );
    }

    getBooks() {
        this.bookService.getBooks()
            .subscribe(
                books => this.books = books,
                error => console.error("error getting books", error)
            );
    }

    save(details) {
        console.log("save button pressed", this.currentClient.id, this.currentBook.id, details);

        this.transactionService.save(this.currentClient.id, this.currentBook.id, details)
            .subscribe(
                _ => {
                    console.debug("transaction saved");
                    this.goBack();
                },
                err => console.error("error saving transaction", err)
            );
    }

    goBack(): void {
        this.router.navigate(['/transactions/list-transactions']);
    }
}
