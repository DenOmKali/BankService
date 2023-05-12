import { Component } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthTokenModel } from 'src/app/model/authtoken.model';
import { LoginModel } from 'src/app/model/login.model';
import { ProfileModel } from 'src/app/model/profile.model';
import { RegistrationModel } from 'src/app/model/registration.model';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  showLogin = false;
  showRegistration = false;
  loginData: LoginModel = new LoginModel('', '');
  registrationData: RegistrationModel = new RegistrationModel('', '', '', '', '');
  profile: ProfileModel = new ProfileModel('', '', '', '', 'USER');
  token: AuthTokenModel = new AuthTokenModel('null');

  constructor (private authService: AuthService, private profileService: ProfileService, private cookieService: CookieService, private router: Router) {
    this.profileService.getProfile().subscribe( data => {
      this.profile = data;
    })
  }

  loginForm() {
    this.authService.authenticate(this.loginData).subscribe(
      response => {
        this.cookieSetAndRedirect(response);
      }, error => {
        console.log(error);
        this.signOut();
      }
    )
  }

  registrationForm() {
    this.authService.registration(this.registrationData).subscribe(
      response => {
        this.cookieSetAndRedirect(response);
      }, error => {
        console.log(error);
        this.signOut();
      }
    )
  }

  cookieSetAndRedirect(response: any) {
    this.token = response;
    this.cookieService.set('jwtToken', this.token.token);
    this.router.navigate(['/profile']).then(r => console.log(response));
    this.showLogin = false;
    this.showRegistration = false;
  }

  getTokenFromCookies() {
    return this.cookieService.get('jwtToken');
  }

  signOut() {
    this.router.navigate(['/']);
    return this.cookieService.delete('jwtToken');
  }


  openLoginPopup() {
    this.showLogin = true;
    if (this.showRegistration) {
      this.closeRegistrationPopup();
    }
  }

  closeLoginPopup() {
    this.showLogin = false;
  }

  openRegistrationPopup() {
    this.showRegistration = true;
    if (this.showLogin) {
      this.closeLoginPopup();
    }
  }

  closeRegistrationPopup() {
    this.showRegistration = false;
  }

}
