# CityMap

Java project for a course assignment - **"City Map"** — models a city map as a directed, weighted graph (junctions = vertices, streets = edges), with algorithms operating on it.

The map data is hardcoded directly in the code (in `Main.java`), via `CityMap.addStreet(from, to, length)`.

## Project structure

```
CityMap/
├── src/
│   ├── Main.java              — demo examples with "yes"/"no" results for each check
│   └── model/
│       ├── Street.java        — a single directed edge (destination junction + length), with validation
│       ├── CityMap.java       — the graph itself (adjacency list) + adding junctions/streets
│       ├── PathChecker.java   — check whether a path exists between two junctions (BFS)
│       ├── ContainsCycle.java — can we tour around and return to the starting junction (DFS)
│       ├── Reach.java  — are all junctions reachable from a given one (BFS)
│       └── DeadEnd.java       — find all dead-end streets
└── README.md
```

## Graph modeling

- `CityMap` stores the graph as a `Map<String, List<Street>>` — for each junction, a list of its outgoing streets.
- Every street is a **directed edge**: `addStreet("A", "B", 150)` adds a street ONLY from `A` to `B`. For a two-way street, `addStreet` is called twice, once in each direction (as in the assignment's example).
- `addStreet` throws `IllegalArgumentException` if a street in the same direction between the same two junctions already exists (not allowed per the assignment).
- `Street` validates in its setters: the destination junction cannot be `null`/blank, and the length must be a positive number.

## Implemented tasks

| Task | Description | Class/method |
|---|---|---|
| 1 | Is there a path between two junctions | `PathChecker.hasPath(map, start, end)` |
| 2 | Can we tour around and return to the start | `ContainsCycle.canReturnToStart(map, start)` |
| 3 | Are all junctions reachable from a given one | `Reachability.canReachAll(map, start)` / `unreachableFrom(map, start)` |
| 4 | All dead-end streets | `DeadEnd.findDeadEndStreets(map)` |


## Running it

`Main.java` contains two hardcoded maps:
- `buildDemoMap()` — a larger map, deliberately built so that every check has an example for both "yes" and "no" (includes cycles, a dead-end street, and an isolated, disconnected component).
- `buildFullyConnectedMap()` — a small, fully connected map, used as a contrast (demonstrates the "yes"/"no dead ends" cases).

Just run `Main.main()` — the results are printed to the console for each of the four implemented tasks.

## Requirements

- Java 17+ (uses `var`; text blocks are not required).
- No external libraries — plain Java (`java.util`).
