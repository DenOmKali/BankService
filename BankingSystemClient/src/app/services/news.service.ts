import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, interval, switchMap } from 'rxjs';
import { NewsModel } from '../model/news.model';
import {AddNewsModel} from "../model/addnews.model";

@Injectable({
  providedIn: 'root'
})
export class NewsService {
  url = 'http://localhost:8080/news';

  constructor(private http: HttpClient) { }

  getCount(): Observable<any>{
    return interval(1000)
    .pipe(
      switchMap(() => this.http.get<any>(this.url + '/count'))
    );
  }

  getAllNews(): Observable<any> {
    return this.http.get<NewsModel[]>(this.url + '/all')
  }

  addNews(body: AddNewsModel) : Observable<any>{
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<AddNewsModel>(this.url + '/addNews', body, { headers });
  }
}
