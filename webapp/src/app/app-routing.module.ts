import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {IndexComponent} from "./index/index.component";
import {BookNewComponent} from "./books/book-new/book-new.component";
import {BookListComponent} from "./books/book-list/book-list.component";
import {BookUpdateComponent} from "./books/book-update/book-update.component";
import {ClientListComponent} from "./clients/client-list/client-list.component";
import {ClientNewComponent} from "./clients/client-new/client-new.component";
import {ClientUpdateComponent} from "./clients/client-update/client-update.component";
import {TransactionListComponent} from "./transaction/transaction-list/transaction-list.component";
import {TransactionNewComponent} from "./transaction/transaction-new/transaction-new.component";
import {Book2ListComponent} from "./books2/book2-list/book2-list.component";
import {Book2NewComponent} from "./books2/book2-new/book2-new.component";
import {Book2UpdateComponent} from "./books2/book2-update/book2-update.component";


const routes: Routes = [
    {path: 'index', component: IndexComponent},

    {path: 'books/list-books', component: BookListComponent},
    {path: 'books/new-book', component: BookNewComponent},
    {path: 'books/update/:id', component: BookUpdateComponent},

    {path: 'books2/list-books', component: Book2ListComponent},
    {path: 'books2/new-book', component: Book2NewComponent},
    {path: 'books2/update/:id', component: Book2UpdateComponent},

    {path: 'clients/list-clients', component: ClientListComponent},
    {path: 'clients/new-client', component: ClientNewComponent},
    {path: 'clients/update/:id', component: ClientUpdateComponent},

    {path: 'transactions/list-transactions', component: TransactionListComponent},
    {path: 'transactions/new-transaction', component: TransactionNewComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
