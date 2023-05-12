export class RegistrationModel {
    name: string;
    surname: string;
    phoneNumber: string;
    email: string;
    password: string;

    constructor(name: string, surname: string, phoneNumber: string, email: string, password: string) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }
}
