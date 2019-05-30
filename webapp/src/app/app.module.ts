import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

import {BooksComponent} from './books/books.component';
import {BookService} from "./books/shared/book.service";
import {BookListComponent} from './books/book-list/book-list.component';

import {NavbarComponent} from './navbar/navbar.component';
import {FooterComponent} from './footer/footer.component';
import {IndexComponent} from './index/index.component';
import {ClientListComponent} from "./clients/client-list/client-list.component";
import {ClientsComponent} from "./clients/clients.component";
import {ClientService} from "./clients/shared/client.service";
import {BookNewComponent} from './books/book-new/book-new.component';
import {BookUpdateComponent} from './books/book-update/book-update.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {ClientUpdateComponent} from './clients/client-update/client-update.component';
import {TransactionComponent} from './transaction/transaction.component';
import {TransactionNewComponent} from './transaction/transaction-new/transaction-new.component';
import {TransactionListComponent} from './transaction/transaction-list/transaction-list.component';
import {TransactionService} from "./transaction/shared/transaction.service";
import { Books2Component } from './books2/books2.component';
import { Book2ListComponent } from './books2/book2-list/book2-list.component';
import { Book2NewComponent } from './books2/book2-new/book2-new.component';
import { Book2UpdateComponent } from './books2/book2-update/book2-update.component';
import {Book2Service} from "./books2/shared/book2.service";
import {ActionReducerMap, StoreModule} from "@ngrx/store";

import * as bookReducer from './books2/shared/store/book2.reducers';
import {EffectsModule} from "@ngrx/effects";
import {Book2Effects} from "./books2/shared/store/book2.effects";
export const reducers: ActionReducerMap<any> = {
    books: bookReducer.reducer,
};

@NgModule({
    declarations: [
        AppComponent,

        BooksComponent,
        BookListComponent,
        BookNewComponent,
        BookUpdateComponent,

        Books2Component,
        Book2ListComponent,
        Book2NewComponent,
        Book2UpdateComponent,

        ClientsComponent,
        ClientListComponent,
        ClientNewComponent,
        ClientUpdateComponent,

        NavbarComponent,
        FooterComponent,
        IndexComponent,
        TransactionComponent,
        TransactionNewComponent,
        TransactionListComponent,

    ],
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        AppRoutingModule,
        NgbModule.forRoot(),
        StoreModule.forRoot(reducers),
        EffectsModule.forRoot([Book2Effects])
    ],
    providers: [BookService, Book2Service, ClientService, TransactionService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
