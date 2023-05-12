export class SendMoneyRequestModel {
  sendCardNumber: string;
  receivedCardNumber: string;
  amount: number;
  message: string;

  constructor(sendCardNumber: string, receivedCardNumber: string, amount: number, message: string) {
    this.sendCardNumber = sendCardNumber;
    this.receivedCardNumber = receivedCardNumber;
    this.amount = amount;
    this.message = message;
  }
}
