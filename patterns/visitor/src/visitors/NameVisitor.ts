import { Visitor } from "./Visitor";
import { Book } from "../elements/Book";
import { DVD } from "../elements/DVD";

export class NameVisitor implements Visitor {
  private names: string[] = [];

  visitBook(book: Book): void {
    this.names.push(`Book: ${book.title}`);
  }

  visitDVD(dvd: DVD): void {
    this.names.push(`DVD: ${dvd.title}`);
  }

  getNames(): string[] {
    return this.names;
  }
}
