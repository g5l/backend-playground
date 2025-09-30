import type { TransportStrategy } from "../strategies/TransportStrategy";

export class TransportationToAirport {
  constructor(private strategy: TransportStrategy) {}

  setStrategy(strategy: TransportStrategy) {
    this.strategy = strategy;
  }

  estimateTime(distanceKm: number): number {
    return this.strategy.timeToArrive(distanceKm);
  }

  get strategyName(): string {
    return this.strategy.name;
  }
}
