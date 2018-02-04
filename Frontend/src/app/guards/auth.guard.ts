import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../services/authService';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private authService: AuthenticationService) { }

    canActivate(
      next: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      let role = this.authService.getRole();
      if (typeof(role) === "string")
        role = role;

        const expectedRoles = role;
        // const isInExpectedRole = expectedRoles != undefined ? expectedRoles.filter(role).length > 0 : true;

      /*if (true) {
          return true;
      } else {
          this.authService.redirectToLogin(state.url);
          return false;
      }*/
    return true;
    }

}