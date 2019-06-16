import * as bookActions from './book2.actions';
import {createFeatureSelector, createSelector} from '@ngrx/store';
import {AppAction} from "../../../app.action";
import {Book} from "../../../books/shared/book.model";

export interface State {
    data: Book[];
    selected: Book;
    action: string;
    error?: Error;
}

const initialState: State = {
    data: null,
    selected: null,
    action: null,
    error: null
};

export function reducer(state = initialState, action: AppAction): State {

    switch (action.type) {

        case bookActions.GET_BOOKS:
            return {...state, action: bookActions.GET_BOOKS, selected: null};
        case bookActions.GET_BOOKS_SUCCESS:
            return {...state, data: action.payload, selected: null};
        case bookActions.GET_BOOKS_ERROR:
            return {...state, selected: null, error: action.payload};

        case bookActions.GET_BOOK:
            return {...state, action: bookActions.GET_BOOK, selected: null};
        case bookActions.GET_BOOK_SUCCESS:
            return {...state, selected: action.payload};

        case bookActions.CREATE_BOOK:
            return {...state, selected: action.payload, action: bookActions.CREATE_BOOK};

        case bookActions.UPDATE_BOOK:
            return {...state, selected: action.payload, action: bookActions.UPDATE_BOOK};

        case bookActions.REMOVE_BOOK: {
            const selected = state.data.find(h => h.id === action.payload);
            return {...state, selected, action: bookActions.REMOVE_BOOK};
        }

        default:
            return state;
    }
}


export const getBooksState = createFeatureSelector <State> ('books');

export const getAllBooks = createSelector(getBooksState, (state: State) => state.data);
export const getBooksError = createSelector(getBooksState, (state: State) => {
    return state.action === bookActions.GET_BOOKS
        ? state.error : null;
});

export const getBook = createSelector(getBooksState, (state: State) => {
    if (state.action === bookActions.GET_BOOK) {
        return state.selected;
    } else {
        return null;
    }
});
