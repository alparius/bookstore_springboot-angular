import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";


@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

    constructor(private httpClient: HttpClient,
                private router: Router) {
    }

    logout() {
        this.httpClient.post(`http://localhost:8080/logout`, null, {withCredentials: true})
            .subscribe((result) => {
                this.router.navigateByUrl("/index");
                sessionStorage.removeItem("userRole");
            });
    }
}
