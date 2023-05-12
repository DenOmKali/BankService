import { Component, OnInit } from '@angular/core';
import { ExchangeService } from 'src/app/services/exchange.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  exchangeRateEUR: string[] = [];
  exchangeRateUSD: string[] = [];
  exchangeRatePLN: string[] = [];

  constructor(private service: ExchangeService) {}

  ngOnInit() {
    this.service.getExchanges().subscribe(data => {
      this.exchangeRateEUR = data.exchangeRateEUR;
      this.exchangeRateUSD = data.exchangeRateUSD;
      this.exchangeRatePLN = data.exchangeRatePLN;
    });
  }

}
