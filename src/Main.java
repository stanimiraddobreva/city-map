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

        System.out.println("=== Картата ===");
        map.printMap();

        System.out.println();
        System.out.println("=== Точка 1: Проверка за път ===");
        System.out.println("Попа -> НДК: " + PathChecker.hasPath(map, "Попа", "НДК"));
        System.out.println("НДК -> 5Кьошета: " + PathChecker.hasPath(map, "НДК", "5Кьошета"));

        System.out.println();
        System.out.println("=== Точка 4: Може ли да обиколим и да се върнем ===");
        System.out.println("От Попа: " + ContainsCycle.canReturnToStart(map, "Попа"));
        System.out.println("От 5Кьошета: " + ContainsCycle.canReturnToStart(map, "5Кьошета"));

        System.out.println();
        System.out.println("=== Точка 6: Достижимост до всички кръстовища ===");
        System.out.println("От Попа: " + Reachability.canReachAll(map, "Попа"));
        Set<String> unreachable = Reachability.unreachableFrom(map, "Попа");
        System.out.println("Недостижими от Попа: " + unreachable);

        System.out.println();
        System.out.println("=== Точка 7: Задънени улици ===");
        List<DeadEnd.StreetEdge> deadEnds = DeadEnd.findDeadEndStreets(map);
        if (deadEnds.isEmpty()) {
            System.out.println("Няма задънени улици.");
        } else {
            for (var edge : deadEnds) {
                System.out.println(edge);
            }
        }
    }

    /**
     * Хардкоднати данни по примера от условието на задачата.
     */
    private static CityMap buildSampleMap() {
        CityMap map = new CityMap();

        map.addStreet("Попа", "БСФС", 150);
        map.addStreet("БСФС", "Попа", 150);
        map.addStreet("Попа", "5Кьошета", 1000);
        map.addStreet("5Кьошета", "НДК", 400);
        map.addStreet("НДК", "Попа", 800);

        return map;
    }
}
