export class TransactionModel {
  senderId: number;
  receiverId: number;
  senderCardId: number;
  receiverCardId: number;
  amount: number;
  message: string;


  constructor(senderId: number, receiverId: number, senderCardId: number, receiverCardId: number, amount: number, message: string) {
    this.senderId = senderId;
    this.receiverId = receiverId;
    this.senderCardId = senderCardId;
    this.receiverCardId = receiverCardId;
    this.amount = amount;
    this.message = message;
  }
}
