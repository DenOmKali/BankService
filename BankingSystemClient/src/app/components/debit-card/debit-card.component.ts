import { Component } from '@angular/core';
import {CardsService} from "../../services/cards.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-debit-card',
  templateUrl: './debit-card.component.html',
  styleUrls: ['./debit-card.component.scss']
})
export class DebitCardComponent {
  pinCode: string = '1111';

  constructor(private service: CardsService, private router: Router) { }

  saveDebitCard() {
    this.service.saveDebitCard(this.pinCode).subscribe(() => {
      console.log('Succesful');
    }, error => {
        this.router.navigate(['/profile']);
        console.log(error);
      }
    )
  }

}
