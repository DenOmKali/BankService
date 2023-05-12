import { ChangeDetectionStrategy, Component, OnDestroy, OnInit } from '@angular/core';
import { NewsModel } from 'src/app/model/news.model';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default
})
export class NewsComponent implements OnInit {
  count: any = 0;
  news: NewsModel[] = [];

  constructor(private service: NewsService) {}

  ngOnInit(): void {
      this.service.getCount().subscribe(data => {
        this.count = data;
      });
      this.service.getAllNews().subscribe(data => {
        this.news = data;
      });
  }

}
