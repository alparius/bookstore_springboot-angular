import {Book} from "./book.model";

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from 'rxjs/operators';


@Injectable()
export class BookService {
    private booksUrl = 'http://localhost:8080/api/books';

    constructor(private httpClient: HttpClient) {
    }

    getBooks(): Observable<Book[]> {
        return this.httpClient.get<Array<Book>>(this.booksUrl).pipe(
            map(objs => objs.map(json => new Book(json)))
        );
    }

    getBook(id: number): Observable<Book> {
        return this.getBooks().pipe(
            map(books => books.find(book => book.id === id)));
    }

    update(book): Observable<Book> {
        const url = `${this.booksUrl}/${book.id}`;
        return this.httpClient.put<Book>(url, book);
    }

    save(isbn: string, title: string, author: string, publisher: string, price: number): Observable<Book> {
        let book = {isbn, title, author, publisher, price};
        return this.httpClient.post<Book>(this.booksUrl, book);
    }

    delete(id: number): Observable<Book> {
        const url = `${this.booksUrl}/${id}`;
        return this.httpClient.delete<Book>(url);
    }
}
