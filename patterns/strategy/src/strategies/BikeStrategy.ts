import { TransportStrategy, computeHours } from "./TransportStrategy";

export class BikeStrategy implements TransportStrategy {
  readonly name = "Bike";
  
  timeToArrive(distanceKm: number): number {
    return computeHours(distanceKm, 18);
  }
}
