import {ClientService} from "../shared/client.service";
import {Client} from "../shared/client.model";

import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {switchMap} from "rxjs/operators";


@Component({
    selector: 'app-client-update',
    templateUrl: './client-update.component.html',
    styleUrls: ['./client-update.component.css']
})
export class ClientUpdateComponent implements OnInit {
    @Input() client: Client;

    constructor(private clientService: ClientService,
                private router: Router,
                private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.route.params.pipe(
            switchMap((params: Params) => this.clientService.getClient(+params['id'])))
            .subscribe(client => this.client = client);
    }

    save(): void {
        this.clientService.update(this.client)
            .subscribe(_ => this.goBack());
    }

    goBack(): void {
        this.router.navigate(['/clients/list-clients']);
    }
}
