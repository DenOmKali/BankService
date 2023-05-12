import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private cookie: CookieService) {}

  canActivate(): boolean {
    const token = this.cookie.check('jwtToken');
    if (token) {
      return true;
    } else {
      this.router.navigate(['/']);
      return false;
    }
  }

}
