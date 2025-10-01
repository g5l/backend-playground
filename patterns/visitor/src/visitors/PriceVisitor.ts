import { Visitor } from "./Visitor";
import { Book } from "../elements/Book";
import { DVD } from "../elements/DVD";

export class PriceVisitor implements Visitor {
  private total: number = 0;

  visitBook(book: Book): void {
    this.total += book.price;
  }

  visitDVD(dvd: DVD): void {
    this.total += dvd.price;
  }

  getTotal(): number {
    return this.total;
  }
}
