import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {
  url = 'http://localhost:8080/exchange';

  constructor(private http: HttpClient) { }

  getExchanges(): Observable<any> {
    return this.http.get<any>(this.url + '/rates');
  }

}
