package model;

import java.util.*;

public class CityMap {
    private Map<String, List<Street>> graph = new LinkedHashMap<>();

    public void addJunction(String name) {
        if (!graph.containsKey(name)) {
            graph.put(name, new ArrayList<>());
        }
    }

    public void addStreet(String from, String to, int length) {
    Street street = new Street(to, length);   
    for (Street s : graph.getOrDefault(from, List.of())) {
        if (s.getTo().equals(to)) {           /
            throw new IllegalArgumentException("Street already exists: " + from + " -> " + to);
        }
    }
    addJunction(from);
    addJunction(to);
    graph.get(from).add(street);
}

    public Set<String> getJunctions() {
        return graph.keySet();
    }

    public List<Street> getStreetsFrom(String intersection) {
        return graph.getOrDefault(intersection, Collections.emptyList());
    }

    public void printMap() {
        for (var entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
