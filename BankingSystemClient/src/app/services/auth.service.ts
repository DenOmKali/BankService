import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthTokenModel } from '../model/authtoken.model';
import { LoginModel } from '../model/login.model';
import { RegistrationModel } from '../model/registration.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
    url = 'http://localhost:8080/user';

  constructor(private http: HttpClient) { }
  
  authenticate(body: LoginModel): Observable<any>{
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<AuthTokenModel>(this.url + '/authenticate', body, { headers })
  }

  registration(body: RegistrationModel): Observable<any>{
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<AuthTokenModel>(this.url + '/register', body, { headers })
  }
  
}
