import {Action} from '@ngrx/store';
import {Book} from "../../../books/shared/book.model";

export const GET_BOOKS = 'GET_BOOKS';
export const GET_BOOKS_SUCCESS = 'GET_BOOKS_SUCCESS';
export const GET_BOOKS_ERROR = 'GET_BOOKS_ERROR';

export const GET_BOOK = 'GET_BOOK';
export const GET_BOOK_SUCCESS = 'GET_BOOK_SUCCESS';

export const CREATE_BOOK = 'CREATE_BOOK';
export const UPDATE_BOOK = 'UPDATE_BOOK';

export const REMOVE_BOOK = 'REMOVE_BOOK';


export const GET_DONE = 'GET_DONE';
export class GetDone implements Action {
    readonly type = GET_DONE;
}


export class GetAllBooks implements Action {
    readonly type = GET_BOOKS;
}
export class GetAllBooksSuccess implements Action {
    readonly type = GET_BOOKS_SUCCESS;
    constructor(public payload: Book[]) {
    }
}
export class GetAllBooksError implements Action {
    readonly type = GET_BOOKS_ERROR;
    constructor(public payload: Error) {
    }
}

export class GetBook implements Action {
    readonly type = GET_BOOK;
    constructor(public payload: number) {
    }
}
export class GetBookSuccess implements Action {
    readonly type = GET_BOOK_SUCCESS;
    constructor(public payload: Book) {
    }
}

export class AddBook implements Action {
    readonly type = CREATE_BOOK;
    constructor(public payload: Book) {
    }
}

export class UpdateBook implements Action {
    readonly type = UPDATE_BOOK;
    constructor(public payload: Book) {
    }
}

export class RemoveBook implements Action {
    readonly type = REMOVE_BOOK;
    constructor(public payload: number) {
    }
}
