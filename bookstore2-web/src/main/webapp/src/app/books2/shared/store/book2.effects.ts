import {Injectable} from '@angular/core';
import {Actions, Effect, ofType} from '@ngrx/effects';
import * as bookActions from './book2.actions';
import {
    AddBook,
    GetAllBooksError,
    GetAllBooksSuccess,
    GetBook,
    GetBookSuccess,
    GetDone,
    RemoveBook,
    UpdateBook
} from './book2.actions';
import {catchError, map, switchMap} from 'rxjs/operators';
import {Book2Service} from "../book2.service";
import {Observable} from "rxjs";
import {Action} from "@ngrx/store";

@Injectable()
export class Book2Effects {
    constructor(private actions$: Actions,
                private service: Book2Service) {
    }

    @Effect()
    getAllBooks$ : Observable<Action> = this.actions$.pipe(
        ofType(bookActions.GET_BOOKS),
        switchMap((action) => this.service.getBooks()),
        map(resp => new GetAllBooksSuccess(resp)),
        catchError((err) => [new GetAllBooksError(err)])
    );

    @Effect()
    getBook$ = this.actions$.pipe(
        ofType(bookActions.GET_BOOK),
        map((action: GetBook) => action.payload),
        switchMap(id => this.service.getBook(id)),
        map(resp => [])
    );


    @Effect()
    updateBook$ : Observable<Action> = this.actions$.pipe(
        ofType(bookActions.UPDATE_BOOK),
        map((action: UpdateBook) => action.payload),
        switchMap(book => this.service.update(book)),
        map(resp => new GetDone())
    );

    @Effect()
    createBook$ : Observable<Action> = this.actions$.pipe(
        ofType(bookActions.CREATE_BOOK),
        map((action: AddBook) => action.payload),
        switchMap(b => this.service.save(b.isbn, b.title, b.author, b.publisher, b.price)),
        map(resp => new GetDone())
    );

    @Effect()
    removeBook$ : Observable<Action> = this.actions$.pipe(
        ofType(bookActions.REMOVE_BOOK),
        map((action: RemoveBook) => action.payload),
        switchMap(id => this.service.delete(id)),
        map(resp => new GetDone())
    );
}
