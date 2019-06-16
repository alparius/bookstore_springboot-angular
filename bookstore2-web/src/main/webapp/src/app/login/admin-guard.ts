import {CanActivate, Router} from "@angular/router";

export class AdminGuard implements CanActivate {

    //constructor(private router: Router) {}

    canActivate() {
        if (sessionStorage.getItem("userRole") === "ADMIN") {
            return true;
        }
        else {
            //this.router.navigateByUrl('/autherror');
            alert("please login");
            return false;
        }
    }
}
