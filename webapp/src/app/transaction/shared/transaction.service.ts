import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Transaction} from "./transaction.model";

import {Observable} from "rxjs";
import {map} from 'rxjs/operators';


@Injectable()
export class TransactionService {
    private transactionsUrl = 'http://localhost:8080/api/transactions';

    constructor(private httpClient: HttpClient) {
    }

    getTransactions(): Observable<Transaction[]> {
        return this.httpClient.get<Array<Transaction>>(this.transactionsUrl);
    }

    save(clientId: number, bookId: number, details: string): Observable<Transaction> {
        let transaction = {clientId, bookId, details};
        console.log(`clientId: ${clientId}; bookId: ${bookId}`);
        return this.httpClient.post<Transaction>(this.transactionsUrl, transaction);
    }
}
