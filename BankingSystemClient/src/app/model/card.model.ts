export class CardModel{
  cardNumber: string;
  cardType: string;
  dateExpiration: string;
  cvv2: string;
  balance: number;

  constructor(cardNumber: string, cardType: string, dateExpiration: string, cvv2: string, balance: number) {
    this.cardNumber = cardNumber;
    this.cardType = cardType;
    this.dateExpiration = dateExpiration;
    this.cvv2 = cvv2;
    this.balance = balance;
  }

}
