package graph;

import java.util.*;

/**
 * Undirected campus graph using an adjacency list.
 * Vertices = campus locations, edges = roads/paths.
 */
public class CampusGraph {

    private Map<String, List<String>> adjList = new LinkedHashMap<>();

    public boolean addLocation(String name) {
        if (adjList.containsKey(name.toLowerCase())) return false; // duplicate
        adjList.put(name.toLowerCase(), new ArrayList<>());
        return true;
    }

    public boolean removeLocation(String name) {
        String key = name.toLowerCase();
        if (!adjList.containsKey(key)) return false;
        adjList.remove(key);
        for (List<String> neighbours : adjList.values()) {
            neighbours.remove(key);
        }
        return true;
    }

    public boolean addConnection(String a, String b) {
        String x = a.toLowerCase(), y = b.toLowerCase();
        if (!adjList.containsKey(x) || !adjList.containsKey(y)) return false; // missing location
        if (x.equals(y) || adjList.get(x).contains(y)) return false;          // self loop / duplicate
        adjList.get(x).add(y);
        adjList.get(y).add(x);
        return true;
    }

    public boolean removeConnection(String a, String b) {
        String x = a.toLowerCase(), y = b.toLowerCase();
        if (!adjList.containsKey(x) || !adjList.containsKey(y)) return false;
        if (!adjList.get(x).contains(y)) return false; // connection unavailable
        adjList.get(x).remove(y);
        adjList.get(y).remove(x);
        return true;
    }

    public void displayNetwork() {
        if (adjList.isEmpty()) {
            System.out.println("No campus locations added.");
            return;
        }
        System.out.println("--- Campus Network ---");
        for (Map.Entry<String, List<String>> e : adjList.entrySet()) {
            System.out.println(e.getKey() + " -> " + (e.getValue().isEmpty() ? "(no connections)" : e.getValue()));
        }
    }

    public void bfs(String start) {
        String s = start.toLowerCase();
        if (!adjList.containsKey(s)) {
            System.out.println("Location not found.");
            return;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s);
        queue.add(s);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            System.out.print(cur + " ");
            for (String n : adjList.get(cur)) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            }
        }
        System.out.println();
    }

    public void dfs(String start) {
        String s = start.toLowerCase();
        if (!adjList.containsKey(s)) {
            System.out.println("Location not found.");
            return;
        }
        System.out.print("DFS: ");
        dfsRec(s, new HashSet<>());
        System.out.println();
    }

    private void dfsRec(String cur, Set<String> visited) {
        visited.add(cur);
        System.out.print(cur + " ");
        for (String n : adjList.get(cur)) {
            if (!visited.contains(n)) dfsRec(n, visited);
        }
    }
}