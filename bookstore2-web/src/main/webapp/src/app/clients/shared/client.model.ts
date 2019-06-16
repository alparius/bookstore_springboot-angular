import {Transaction} from "../../transaction/shared/transaction.model";

export class Client {
    id: number;
    personalId: number;
    name: string;
    email: string;
    transactions: Transaction[];
    constructor(args: {
        id: number,
        personalId: number,
        name: string,
        email: string,
        transactions: Transaction[]
    }) {
        this.id = args.id;
        this.personalId = args.personalId;
        this.name = args.name;
        this.email = args.email;
        this.transactions = args.transactions;
    }
}
