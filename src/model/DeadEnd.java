package model;

import java.util.ArrayList;
import java.util.List;

public class DeadEnd {
    public static class StreetEdge {
        private final String from;
        private final String to;

        public StreetEdge(String from, String to) {
            this.from = from;
            this.to = to;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        @Override
        public String toString() {
            return from + " -> " + to;
        }
    }

    public static List<StreetEdge> findDeadEndStreets(CityMap map) {
        List<StreetEdge> deadEnds = new ArrayList<>();

        for (String junction : map.getJunctions()) {
            for (Street street : map.getStreetsFrom(junction)) {
                String to = street.getTo();

                if (map.getStreetsFrom(to).isEmpty()) {
                    deadEnds.add(new StreetEdge(junction, to));
                }
            }
        }

        return deadEnds;
    }

}
