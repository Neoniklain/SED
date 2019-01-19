import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import {AuthenticationService} from '../services/auth.service';
import {Role} from "../models/account/role.model";

@Injectable()
export class AuthGuard implements CanActivate {

   constructor(private authService: AuthenticationService) {
   }

   canActivate(next: ActivatedRouteSnapshot,
               state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {

      const expectedRoles: string[] = next.data.expectedRoles;
      let accessAlowed = false;
      if (expectedRoles) {
          return this.authService.getRole().map(
              result => {
                 let roles: Role[] = result.data;
                 for (let expectedRole of expectedRoles) {
                    for (let role of roles) {
                       if (role.roleName === expectedRole)
                          accessAlowed = true;
                    }
                 }
                 if (accessAlowed) {
                    return true;
                 } else {
                    this.authService.redirectToAccessDenied(state.url);
                    return false;
                 }
              }
          );
      } else {
          this.authService.redirectToAccessDenied(state.url);
          return false;
      }
   }

}