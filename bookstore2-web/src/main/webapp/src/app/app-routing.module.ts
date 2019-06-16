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
import {LoginComponent} from "./login/login.component";
import {ClientGuard} from "./login/client-guard";
import {AdminGuard} from "./login/admin-guard";
import {AutherrorComponent} from "./autherror/autherror.component";


const routes: Routes = [
    {path: 'index', component: IndexComponent},

    {path: 'books/list-books', component: BookListComponent, canActivate: [ClientGuard]},
    {path: 'books/new-book', component: BookNewComponent, canActivate: [AdminGuard]},
    {path: 'books/update/:id', component: BookUpdateComponent, canActivate: [AdminGuard]},

    {path: 'books2/list-books', component: Book2ListComponent, canActivate: [ClientGuard], },
    {path: 'books2/new-book', component: Book2NewComponent, canActivate: [AdminGuard]},
    {path: 'books2/update/:id', component: Book2UpdateComponent, canActivate: [AdminGuard]},

    {path: 'clients/list-clients', component: ClientListComponent, canActivate: [ClientGuard]},
    {path: 'clients/new-client', component: ClientNewComponent, canActivate: [AdminGuard]},
    {path: 'clients/update/:id', component: ClientUpdateComponent, canActivate: [AdminGuard]},

    {path: 'transactions/list-transactions', component: TransactionListComponent, canActivate: [AdminGuard]},
    {path: 'transactions/new-transaction', component: TransactionNewComponent, canActivate: [AdminGuard]},

    {path: 'login', component: LoginComponent},
    {path: 'autherror', component: AutherrorComponent},

];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
