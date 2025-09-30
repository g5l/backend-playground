# Strategy Pattern (TypeScript)

This project demonstrates the Strategy design pattern: define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

## Problem Modeled
Estimate how long it takes to reach the airport depending on the transportation method (car, bike, bus, walking, metro). The behavior for “estimate time” changes by strategy, and we want to switch it at runtime without `if/else` trees.

## Structure
- `TransportStrategy` (interface): common contract for all transport strategies.
- Concrete strategies: `CarStrategy`, `BikeStrategy`, `BusStrategy`, `WalkingStrategy`, `MetroStrategy` implement the algorithm.
- `TransportationToAirport` (context): holds a reference to a `TransportStrategy` and delegates the work.
- `main.ts`: simple demo switching strategies at runtime.

```
src/
  context/
    TransportationToAirport.ts   # Context
  strategies/
    TransportStrategy.ts         # Strategy interface + helper
    CarStrategy.ts               # Concrete strategy
    BikeStrategy.ts              # Concrete strategy
    BusStrategy.ts               # Concrete strategy
    WalkingStrategy.ts           # Concrete strategy
    MetroStrategy.ts             # Concrete strategy
  main.ts                        # Demo
```

## How It Works
- The interface `TransportStrategy` exposes `timeToArrive(distanceKm: number): number` and a human-friendly `name`.
- Each concrete strategy implements the same method with different speeds.
- The context `TransportationToAirport` accepts any `TransportStrategy`, and you can swap it at runtime with `setStrategy`.

## Run the Example
Prerequisites: Node.js 18+.

- Install: `npm install`
- Run: `npm start`

Example output for a 20 km trip:
```
Using Car, it will take ~0.40 hours to travel 20 km
Using Bike, it will take ~1.11 hours to travel 20 km
Using Bus, it will take ~0.71 hours to travel 20 km
Using Walking, it will take ~4.00 hours to travel 20 km
Using Metro, it will take ~0.33 hours to travel 20 km
```

## When To Use Strategy
- You have multiple ways to perform the same task, chosen at runtime.
- You want to eliminate large conditionals that pick algorithms.
- You need to add new algorithms without modifying existing client code.
- You want to keep algorithm-related code isolated and testable.

## Benefits
- Open/Closed: add new strategies without changing the context.
- Single Responsibility: each algorithm lives in its own class.
- Composability: swap behavior at runtime (even based on user input).

## Trade-offs
- Slight increase in number of classes/objects.
- The client must know which strategy to pick (or you add a factory/selector).

## Extending This Example
Add a new transport method (e.g., `TaxiStrategy`):
1. Create `src/strategies/TaxiStrategy.ts` implementing `TransportStrategy`.
2. Implement `timeToArrive` using your speed rule.
3. Import and add an instance to the `strategies` array in `src/main.ts`.

## Related Patterns
- State: similar structure; state transitions drive which strategy is active.
- Template Method: shares common flow with varying steps via inheritance.
- Bridge: separates abstraction from implementation; Strategy focuses on algorithm substitution.

---
This folder is a minimal, didactic example intended for clarity over completeness.
