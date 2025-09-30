export interface TransportStrategy {
  readonly name: string;
  timeToArrive(distanceKm: number): number;
}

export function computeHours(distanceKm: number, speedKmh: number): number {
  return distanceKm / speedKmh;
}
