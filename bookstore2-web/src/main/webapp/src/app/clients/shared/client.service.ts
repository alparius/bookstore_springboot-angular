import {Client} from "./client.model";

import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from 'rxjs/operators';
import {Transaction} from "../../transaction/shared/transaction.model";


@Injectable()
export class ClientService {
    private clientsUrl = 'http://localhost:8080/api/clients';

    constructor(private httpClient: HttpClient) {
    }

    getClients(): Observable<Client[]> {
        return this.httpClient.get<Array<Client>>(this.clientsUrl, {withCredentials: true}).pipe(
            map(objs => objs.map(json => new Client(json)))
        );
    }

    getClient(id: number): Observable<Client> {
        return this.getClients().pipe(
            map(clients => clients.find(client => client.id === id)));
    }

    update(client): Observable<Client> {
        const url = `${this.clientsUrl}/${client.id}`;
        return this.httpClient.put<Client>(url, client, {withCredentials: true});
    }

    save(personalId: number, name: string, email: string): Observable<Client> {
        let client = {personalId, name, email};
        return this.httpClient.post<Client>(this.clientsUrl, client, {withCredentials: true});
    }

    delete(id: number): Observable<Client> {
        const url = `${this.clientsUrl}/${id}`;
        return this.httpClient.delete<Client>(url, {withCredentials: true});
    }
}
