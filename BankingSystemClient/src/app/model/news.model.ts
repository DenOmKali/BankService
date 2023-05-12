export class NewsModel {
    id: number;
    title: string;
    content: string;
    imageUrl: string;
    date: Date;

    constructor(id: number, title: string, content: string, imageUrl: string, date: Date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
    }
}
