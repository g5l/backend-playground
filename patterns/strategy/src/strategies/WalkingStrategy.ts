import { TransportStrategy, computeHours } from "./TransportStrategy";

export class WalkingStrategy implements TransportStrategy {
  readonly name = "Walking";
  
  timeToArrive(distanceKm: number): number {
    return computeHours(distanceKm, 5);
  }
}
