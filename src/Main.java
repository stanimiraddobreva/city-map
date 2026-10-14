import model.CityMap;
import model.ContainsCycle;
import model.DeadEnd;
import model.PathChecker;
import model.Reachability;

import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        CityMap map = buildSampleMap();

        System.out.println("=== Map ===");
        map.printMap();

        System.out.println();
        System.out.println("=== Task 1: Is there a path between two junctions? ===");
        System.out.println("MainSquare -> CityPark: " + PathChecker.hasPath(map, "MainSquare", "CityPark"));
        System.out.println("Museum -> MainSquare: " + PathChecker.hasPath(map, "Museum", "MainSquare"));

        System.out.println();
        System.out.println("=== Task 2: Can we tour around and return to the start? ===");
        System.out.println("From MainSquare: " + ContainsCycle.canReturnToStart(map, "MainSquare"));
        System.out.println("From Museum: " + ContainsCycle.canReturnToStart(map, "Museum"));

        System.out.println();
        System.out.println("=== Task 3: Are all junctions reachable from a given one? ===");
        System.out.println("From MainSquare: " + Reach.canReachAll(map, "MainSquare"));
        Set<String> unreachable = Reach.unreachableFrom(map, "MainSquare");
        System.out.println("Unreachable from MainSquare: " + unreachable);

        System.out.println();
        System.out.println("=== Task 4: Dead-end streets ===");
        List<DeadEnd.StreetEdge> deadEnds = DeadEnd.findDeadEndStreets(map);
        if (deadEnds.isEmpty()) {
            System.out.println("No dead ends.");
        } else {
            for (var edge : deadEnds) {
                System.out.println(edge);
            }
        }
    }

    /**
     * Hardcoded data: the assignment's example plus a dead-end street (Museum)
     * and an isolated junction (Airport), so every check has a "yes" and a "no" case.
     */
    private static CityMap buildSampleMap() {
        CityMap map = new CityMap();

        map.addStreet("MainSquare", "Library", 150);
        map.addStreet("Library", "MainSquare", 150);
        map.addStreet("MainSquare", "FiveCorners", 1000);
        map.addStreet("FiveCorners", "CityPark", 400);
        map.addStreet("CityPark", "MainSquare", 800);
        map.addStreet("CityPark", "Museum", 300);   // Museum has no outgoing streets
        map.addJunction("Airport");                  // isolated junction

        return map;
    }
}
