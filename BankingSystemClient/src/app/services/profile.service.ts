import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, interval, switchMap } from "rxjs";
import { ProfileModel } from "../model/profile.model";

@Injectable({
    providedIn: 'root'
  })
export class ProfileService {
    url = 'http://localhost:8080/user';

    constructor(private http: HttpClient) { }

    getProfile(): Observable<any> {
        return interval(1000).pipe(
          switchMap(() => this.http.get<ProfileModel>(this.url + '/profile'))
        );
    }
}
