import { Component, OnInit } from '@angular/core';
import { ProfileModel } from 'src/app/model/profile.model';
import { ProfileService } from 'src/app/services/profile.service';
import {CardsService} from "../../services/cards.service";
import {CardModel} from "../../model/card.model";
import {TransactionService} from "../../services/transaction.service";
import {TransactionModel} from "../../model/transaction.model";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profile: ProfileModel = new ProfileModel('', '', '', '', 'USER');
  cards: CardModel[] = [];
  allMyTransaction: TransactionModel[] = []
  constructor(private profileService: ProfileService, private transactionService: TransactionService, private cardService: CardsService) { }

  ngOnInit() {
    this.profileService.getProfile().subscribe(data => {
      this.profile = data;
    });

    this.cardService.getCards().subscribe(data => {
      this.cards = data;
    });

    this.getMyTransactions();
  }

  getMyTransactions() {
    return this.transactionService.getMyTransactions().subscribe(data => {
      this.allMyTransaction = data;
    })
  }

}
