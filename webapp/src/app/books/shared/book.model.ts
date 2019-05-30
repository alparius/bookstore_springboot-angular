import {Transaction} from "../../transaction/shared/transaction.model";

export class Book {
    id: number;
    isbn: string;
    title: string;
    author: string;
    publisher: string;
    price: number;
    transactions: Transaction[];

    constructor(args: {
        id: number,
        isbn: string,
        title: string,
        author: string,
        publisher: string,
        price: number,
        transactions: Transaction[]
    }) {
        this.id = args.id;
        this.isbn = args.isbn;
        this.title = args.title;
        this.author = args.author;
        this.publisher = args.publisher;
        this.price = args.price;
        this.transactions = args.transactions;
    }
}
