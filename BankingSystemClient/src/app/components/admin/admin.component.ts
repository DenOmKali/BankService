import {Component, OnInit} from '@angular/core';
import {AddNewsModel} from "../../model/addnews.model";
import {NewsService} from "../../services/news.service";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent {
  newsModel: AddNewsModel = new AddNewsModel('', '', '');

  constructor(private newsService: NewsService) { }

  addNews() {
    this.newsService.addNews(this.newsModel).subscribe(
      data => {
        console.log(data);
      }, error => {
        console.log(error);
      }
    )
  }

}
