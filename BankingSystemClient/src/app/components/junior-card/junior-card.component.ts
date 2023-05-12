import { Component } from '@angular/core';
import {CardsService} from "../../services/cards.service";
import {Router} from "@angular/router";
import {JuniorResponseCardModel} from "../../model/juniorcardresponse.model";

@Component({
  selector: 'app-junior-card',
  templateUrl: './junior-card.component.html',
  styleUrls: ['./junior-card.component.scss']
})
export class JuniorCardComponent {
  body: JuniorResponseCardModel = new JuniorResponseCardModel('', '');

  constructor(private service: CardsService, private router: Router) { }

  saveJuniorCard() {
    this.service.saveJuniorCard(this.body).subscribe(() => {
        console.log('Succesful');
      }, error => {
        this.router.navigate(['/profile']);
        console.log(error);
      }
    )
  }

}
