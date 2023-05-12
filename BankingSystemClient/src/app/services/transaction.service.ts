import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, interval, switchMap } from "rxjs";
import {TransactionModel} from "../model/transaction.model";

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  url = 'http://localhost:8080/transaction';

  constructor(private http: HttpClient) { }

  getMyTransactions() {
    return this.http.get<TransactionModel[]>(this.url + '/userTransactions');
  }
}
