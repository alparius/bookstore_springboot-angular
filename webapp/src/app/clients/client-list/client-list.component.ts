import {Client} from "../shared/client.model";
import {ClientService} from "../shared/client.service";

import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'app-client-list',
    templateUrl: './client-list.component.html',
    styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
    clients: Array<Client>;

    constructor(private clientService: ClientService,
                private router: Router) {
    }

    ngOnInit(): void {
        this.getClients();
    }

    getClients() {
        this.clientService.getClients()
            .subscribe(
                clients => this.clients = clients,
                error => console.error("error getting clients", error)
            );
    }

    delete(id: number) {
        this.clientService.delete(id)
            .subscribe(
                _ => {
                    console.debug("client deleted");
                    this.ngOnInit();
                },
                error => console.error("error deleting client", error)
            );
    }

    update(id: number) {
        this.router.navigate(['/clients/update', id]);
    }
}
