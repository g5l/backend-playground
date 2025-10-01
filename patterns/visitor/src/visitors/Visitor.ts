import { Book } from "../elements/Book";
import { DVD } from "../elements/DVD";

export interface Visitor {
  visitBook(book: Book): void;
  visitDVD(dvd: DVD): void;
}
