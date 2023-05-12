import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {interval, Observable, switchMap} from "rxjs";
import {CardModel} from "../model/card.model";
import {JuniorResponseCardModel} from "../model/juniorcardresponse.model";
import {SendMoneyRequestModel} from "../model/sendmoneyrequest.model";

@Injectable({
  providedIn: 'root'
})
export class CardsService {
  url = 'http://localhost:8080/card';

  constructor(private http: HttpClient) { }

  getCards(): Observable<any> {
    return this.http.get<CardModel[]>(this.url + '/userCards')
  }

  saveDebitCard(pinCode: string) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<string>(this.url + '/saveDebitCard', pinCode, { headers })
  }

  saveCreditCard(pinCode: string) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<string>(this.url + '/saveCreditCard', pinCode, { headers })
  }

  saveJuniorCard(body: JuniorResponseCardModel) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<string>(this.url + '/saveJuniorCard', body, { headers })
  }

  sendMoney(body: SendMoneyRequestModel) {
    const headers = { 'Content-Type': 'application/json' };
    return this.http.put<string>(this.url + '/sendMoney', body, { headers })
  }
}
