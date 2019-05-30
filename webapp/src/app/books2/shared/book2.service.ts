import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from "rxjs/operators";
import {Book} from "../../books/shared/book.model";

@Injectable()
export class Book2Service {
    protected booksUrl = 'http://localhost:8080/api/books';

    constructor(private httpClient: HttpClient) {
    }

    getBooks(): Observable<Book[]> {
        return this.httpClient.get<Array<Book>>(this.booksUrl);
    }

    getBook(id: number): Observable<Book> {
        return this.getBooks().pipe(
            map(books => books.find(book => book.id === id)));
    }

    delete(id: number): Observable<Book> {
        const url = `${this.booksUrl}/${id}`;
        return this.httpClient.delete<Book>(url);
    }

    save(isbn, title, author, publisher, price): Observable<Book> {
        let book = {isbn, title, author, publisher, price};
        return this.httpClient.post<Book>(this.booksUrl, book);
    }

    update(book): Observable<Book> {
        const url = `${this.booksUrl}/${book.id}`;
        return this.httpClient.put<Book>(url, book);
    }
}
