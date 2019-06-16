import {Transaction} from "../shared/transaction.model";
import {TransactionService} from "../shared/transaction.service";

import {Component, OnInit} from '@angular/core';


@Component({
    selector: 'app-transaction-list',
    templateUrl: './transaction-list.component.html',
    styleUrls: ['./transaction-list.component.css']
})
export class TransactionListComponent implements OnInit {
    transactions: Array<Transaction>;

    constructor(private transactionService: TransactionService) {
    }

    ngOnInit(): void {
        this.getTransactions();
    }

    getTransactions() {
        this.transactionService.getTransactions()
            .subscribe(
                transactions => this.transactions = transactions,
                error => console.error("error getting transactions", error)
            );
    }
}
