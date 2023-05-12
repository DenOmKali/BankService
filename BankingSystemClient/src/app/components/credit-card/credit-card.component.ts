import { Component } from '@angular/core';
import {CardsService} from "../../services/cards.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.scss']
})
export class CreditCardComponent {
  pinCode: string = '1111';

  constructor(private service: CardsService, private router: Router) { }

  saveCreditCard() {
    this.service.saveCreditCard(this.pinCode).subscribe(() => {
        console.log('Succesful');
      }, error => {
        this.router.navigate(['/profile']);
        console.log(error);
      }
    )
  }

}
