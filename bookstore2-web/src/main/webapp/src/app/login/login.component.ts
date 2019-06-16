import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {User} from "./user.model";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    constructor(private router: Router,
                private httpClient: HttpClient) {
    }

    ngOnInit() {
    }

    login(username: string, password: string) {
        console.log('login initiated');
        this.httpClient.post(`http://localhost:8080/login?username=${username}&password=${password}`, null, {withCredentials: true})
            .subscribe((result) => {
                console.log('login returned');
                this.httpClient.get<User>(`http://localhost:8080/api/users`, {withCredentials: true})
                //this.httpClient.get<User>(`http://localhost:8080/api/users/${username}`, {withCredentials: true})
                    .subscribe(user => {
                        sessionStorage.setItem("userRole", user.role);
                        this.router.navigateByUrl("/index");
            })
        });
    }
}
