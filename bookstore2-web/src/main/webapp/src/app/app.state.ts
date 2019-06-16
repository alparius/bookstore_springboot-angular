import * as fromBooks from './books2/shared/store/book2.reducers';

export interface AppState {
    books: fromBooks.State;
}
