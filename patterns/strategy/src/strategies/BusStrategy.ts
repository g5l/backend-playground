import { TransportStrategy, computeHours } from "./TransportStrategy";

export class BusStrategy implements TransportStrategy {
  readonly name = "Bus";
  
  timeToArrive(distanceKm: number): number {
    return computeHours(distanceKm, 28);
  }
}
