import {Component, OnInit} from '@angular/core';
import {AppState} from "../app.state";
import {Store} from "@ngrx/store";
import {Router} from "@angular/router";
import {GetAllBooks} from "./shared/store/book2.actions";
import {getBooksError} from "./shared/store/book2.reducers";

@Component({
    selector: 'app-books2',
    templateUrl: './books2.component.html',
    styleUrls: ['./books2.component.css']
})
export class Books2Component implements OnInit {

    constructor(private store: Store<AppState>,
                private router: Router) {
    }

    ngOnInit() {
        console.log("gettin books");
        this.store.dispatch(new GetAllBooks());

        this.store.select(getBooksError).subscribe((error) =>
            this.alertThis(error, 'error loading list of books'));
    }

    alertThis(condition, message) {
        if (condition) {
            alert(message);
            //this.router.navigate(['/books2/list-books']);
        }
    }

}
