import { Book } from "./elements/Book";
import { DVD } from "./elements/DVD";
import { PriceVisitor } from "./visitors/PriceVisitor";
import { NameVisitor } from "./visitors/NameVisitor";

// Our "shopping cart"
const items = [
  new Book("Clean Code", 30),
  new DVD("Inception", 20),
  new Book("Domain-Driven Design", 40),
  new DVD("The Matrix", 15),
];

// Visitors
const priceVisitor = new PriceVisitor();
const nameVisitor = new NameVisitor();

// Apply visitors to items
for (const item of items) {
  item.accept(priceVisitor);
  item.accept(nameVisitor);
}

// Results
console.log("Total Price:", priceVisitor.getTotal());
console.log("Item List:");
console.log(nameVisitor.getNames().join("\n"));
