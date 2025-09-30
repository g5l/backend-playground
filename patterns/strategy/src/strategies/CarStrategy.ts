import { TransportStrategy, computeHours } from "./TransportStrategy";

export class CarStrategy implements TransportStrategy {
  readonly name = "Car";
  
  timeToArrive(distanceKm: number): number {
    return computeHours(distanceKm, 50); // avg 50 km/h
  }
}
