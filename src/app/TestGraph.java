package app;

import graph.CampusGraph;

public class TestGraph {
    public static void main(String[] args) {
        CampusGraph g = new CampusGraph();
        g.addLocation("Library");
        g.addLocation("Cafeteria");
        g.addLocation("Lab");
        g.addLocation("Hostel");
        System.out.println("Duplicate location: " + g.addLocation("library"));

        g.addConnection("Library", "Cafeteria");
        g.addConnection("Library", "Lab");
        g.addConnection("Cafeteria", "Hostel");
        System.out.println("Missing location: " + g.addConnection("Library", "Gym"));

        g.displayNetwork();
        g.bfs("Library");
        g.dfs("Library");

        g.removeConnection("Library", "Lab");
        g.removeLocation("Hostel");
        g.displayNetwork();
    }
}