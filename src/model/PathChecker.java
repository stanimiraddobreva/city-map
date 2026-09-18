package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import model.CityMap;

public class PathChecker {
        public static boolean hasPath(CityMap map, String start, String end) {
            if (!map.getJunctions().contains(start) || !map.getJunctions().contains(end)) {
                return false;
            }
            if (start.equals(end)) {
                return true;
            }

            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                String current = queue.poll();

                for (Street street : map.getStreetsFrom(current)) {
                    if (street.getTo().equals(end)) {
                        return true;
                    }
                    if (!visited.contains(street.getTo())) {
                        visited.add(street.getTo());
                        queue.add(street.getTo());
                    }
                }
            }

            return false;
        }
}
