import { Visitor } from "../visitors/Visitor";

export interface Element {
  accept(visitor: Visitor): void;
}
