export class ProfileModel {
    name: string;
    surname: string;
    phoneNumber: string;
    email: string;
    role: string;

    constructor(name: string, surname: string, phoneNumber: string, email: string, role: string) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }
}
