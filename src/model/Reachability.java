package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Reachability {

    /**
     * Проверява дали от дадено кръстовище можем да стигнем до всички останали в картата.
     */
    public static boolean canReachAll(CityMap map, String start) {
        if (!map.getJunctions().contains(start)) {
            return false;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            String current = queue.poll();

            for (Street street : map.getStreetsFrom(current)) {
                if (!visited.contains(street.getTo())) {
                    visited.add(street.getTo());
                    queue.add(street.getTo());
                }
            }
        }

        return visited.size() == map.getJunctions().size();
    }

    /**
     * Връща списък с кръстовищата, до които НЕ можем да стигнем от start (полезно за дебъг).
     */
    public static Set<String> unreachableFrom(CityMap map, String start) {
        Set<String> visited = new HashSet<>();
        if (map.getJunctions().contains(start)) {
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();
                for (Street street : map.getStreetsFrom(current)) {
                    if (!visited.contains(street.getTo())) {
                        visited.add(street.getTo());
                        queue.add(street.getTo());
                    }
                }
            }
        }

        Set<String> unreachable = new HashSet<>(map.getJunctions());
        unreachable.removeAll(visited);
        return unreachable;
    }
}
