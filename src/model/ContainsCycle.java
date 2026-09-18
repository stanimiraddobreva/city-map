package model;

import java.util.HashSet;
import java.util.Set;

public class ContainsCycle {
    public static boolean canReturnToStart(CityMap map, String start) {
        if (!map.getJunctions().contains(start)) {
            return false;
        }

        for (Street street : map.getStreetsFrom(start)) {
            String neighbor = street.getTo();

            Set<String> visited = new HashSet<>();
            if (dfs(map, neighbor, start, visited)) {
                return true;
            }
        }

        return false;
    }

    private static boolean dfs(CityMap map, String current, String target, Set<String> visited) {
        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (Street street : map.getStreetsFrom(current)) {
            String next = street.getTo();

            if (!visited.contains(next)) {
                if (dfs(map, next, target, visited)) {
                    return true;
                }
            }
        }

        return false;
    }
}
