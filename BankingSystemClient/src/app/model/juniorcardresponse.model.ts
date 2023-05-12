export class JuniorResponseCardModel{
  pinCode: string;
  parentEmail: string;

  constructor(pinCode: string, parentEmail: string) {
    this.pinCode = pinCode;
    this.parentEmail = parentEmail;
  }

}
