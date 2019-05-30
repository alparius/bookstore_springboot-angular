import {ClientService} from "../shared/client.service";

import {Component} from '@angular/core';
import {Router} from "@angular/router";


@Component({
    selector: 'app-client-new',
    templateUrl: './client-new.component.html',
    styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent {

    constructor(private clientService: ClientService,
                private router: Router) {
    }

    save(personalId, name, email) {
        console.log("save button pressed", personalId, name, email);
        this.clientService.save(personalId, name, email)
            .subscribe(
                _ => {
                    console.debug("client saved");
                    this.goBack();
                },
                error => console.error("error saving client", error)
            );
    }

    goBack(): void {
        this.router.navigate(['/clients/list-clients']);
    }
}
