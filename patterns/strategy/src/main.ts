import { TransportationToAirport } from "./context/TransportationToAirport";
import { CarStrategy } from "./strategies/CarStrategy";
import { BikeStrategy } from "./strategies/BikeStrategy";
import { BusStrategy } from "./strategies/BusStrategy";
import { WalkingStrategy } from "./strategies/WalkingStrategy";
import { MetroStrategy } from "./strategies/MetroStrategy";

const distance = 20;

const strategies = [
  new CarStrategy(),
  new BikeStrategy(),
  new BusStrategy(),
  new WalkingStrategy(),
  new MetroStrategy(),
];

const context = new TransportationToAirport(strategies[0]);

for (const strategy of strategies) {
  context.setStrategy(strategy);
  const hours = context.estimateTime(distance);
  console.log(
    `Using ${context.strategyName}, it will take ~${hours.toFixed(2)} hours to travel ${distance} km`
  );
}
