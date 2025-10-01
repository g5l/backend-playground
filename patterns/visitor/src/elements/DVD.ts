import { Element } from "./Element";
import { Visitor } from "../visitors/Visitor";

export class DVD implements Element {
  constructor(public title: string, public price: number) {}

  accept(visitor: Visitor): void {
    visitor.visitDVD(this);
  }
}
