import { TransportStrategy, computeHours } from "./TransportStrategy";

export class MetroStrategy implements TransportStrategy {
  readonly name = "Metro";
  
  timeToArrive(distanceKm: number): number {
    return computeHours(distanceKm, 60);
  }
}
