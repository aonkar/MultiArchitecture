import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";

@Injectable()
export class RouteGuardService implements CanActivate {

    isLoggedIn :boolean = false;
    constructor(
        private router: Router
    ) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any {
        if(localStorage.getItem('loggedIn') == 'true'){
            return true;
        }
            this.router.navigate(['/']);
    }
}

