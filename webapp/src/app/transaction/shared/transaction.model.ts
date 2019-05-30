import {Client} from "../../clients/shared/client.model";
import {Book} from "../../books/shared/book.model";

export class Transaction {
    public clientId: number;
    public clientName: string;
    public bookId: number;
    public bookAuthor: string;
    public bookTitle: string;
    public details: string;
}
