import {Component, OnInit} from '@angular/core';
import {CardModel} from "../../model/card.model";
import {CardsService} from "../../services/cards.service";
import {SendMoneyRequestModel} from "../../model/sendmoneyrequest.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-transfers',
  templateUrl: './transfers.component.html',
  styleUrls: ['./transfers.component.scss']
})
export class TransfersComponent implements OnInit{
  cards: CardModel[] = []
  requestModel: SendMoneyRequestModel = new SendMoneyRequestModel('', '', 0, '');

  constructor(private service: CardsService, private router: Router) { }

  ngOnInit(): void {
    this.service.getCards().subscribe(data => {
      this.cards = data;
    })
  }

  sendMoney() {
    this.service.sendMoney(this.requestModel).subscribe(data => {
      console.log(data);
    }, error => {
      console.log(error);
      this.router.navigate(['/profile']);
    })
  }

}
