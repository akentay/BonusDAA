package com.aida.mst;

import org.json.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // try to load input.json from resources or current dir
    private static String readJsonText(String path) throws IOException {
        Path p = Paths.get(path);
        if (Files.exists(p)) return Files.readString(p);
        // try resources
        InputStream is = Main.class.getResourceAsStream("/input.json");
        if (is != null) return new String(is.readAllBytes());
        throw new FileNotFoundException("input.json not found at " + path);
    }

    public static void main(String[] args) {
        try {
            String json = readJsonText("src/main/resources/input.json");
            JSONObject root = new JSONObject(json);
            JSONArray graphs = root.getJSONArray("graphs");

            for (int i = 0; i < graphs.length(); i++) {
                JSONObject g = graphs.getJSONObject(i);
                System.out.println("=== Graph ID: " + g.getInt("id") + " ===");

                // nodes
                List<String> nodes = new ArrayList<>();
                JSONArray nArr = g.getJSONArray("nodes");
                for (int j = 0; j < nArr.length(); j++) nodes.add(nArr.getString(j));

                // edges
                List<Edge> edges = new ArrayList<>();
                JSONArray eArr = g.getJSONArray("edges");
                for (int j = 0; j < eArr.length(); j++) {
                    JSONObject eo = eArr.getJSONObject(j);
                    edges.add(new Edge(eo.getString("from"), eo.getString("to"), eo.getInt("weight")));
                }

                Graph graph = new Graph(nodes, edges);
                System.out.printf("Vertices: %d, Edges: %d%n", graph.nodes.size(), graph.edges.size());

                // build MST (use Kruskal)
                List<Edge> mst = Kruskal.kruskalMST(graph);
                printMST("Initial MST (Kruskal)", mst);

                // choose an edge to remove from MST (for demo: middle one)
                if (mst.isEmpty()) {
                    System.out.println("MST empty, skip removal.");
                    continue;
                }
                Edge removed = mst.get(mst.size() / 2); // remove middle edge
                System.out.println("Removing MST edge: " + removed);
                List<Edge> mstAfterRemoval = new ArrayList<>(mst);
                mstAfterRemoval.remove(removed);

                // find components after removal (using MST edges)
                Map<String, List<String>> adjMST = buildAdjacency(mstAfterRemoval, nodes);
                List<Set<String>> comps = components(adjMST, nodes);
                System.out.println("Components after removal: ");
                for (int idx = 0; idx < comps.size(); idx++) {
                    System.out.println(" Component " + (idx + 1) + ": " + comps.get(idx));
                }

                // find replacement edge (from original graph) that connects two different components
                Edge replacement = findReplacementEdge(graph.edges, comps);
                if (replacement == null) {
                    System.out.println("No replacement edge found — graph remains disconnected.");
                } else {
                    System.out.println("Replacement edge chosen: " + replacement);
                    // create new MST by adding replacement
                    List<Edge> newMST = new ArrayList<>(mstAfterRemoval);
                    newMST.add(replacement);
                    printMST("New MST after replacement", newMST);
                }
                System.out.println();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error: " + ex.getMessage());
        }
    }

    private static void printMST(String title, List<Edge> mst) {
        System.out.println(">>> " + title);
        int total = mst.stream().mapToInt(e -> e.weight).sum();
        System.out.println(" Edges: " + mst);
        System.out.println(" Total weight: " + total);
    }

    // adjacency from MST edges
    private static Map<String, List<String>> buildAdjacency(List<Edge> edges, List<String> nodes) {
        Map<String, List<String>> adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e.to);
            adj.get(e.to).add(e.from);
        }
        return adj;
    }

    // connected components for given adjacency
    private static List<Set<String>> components(Map<String, List<String>> adj, List<String> nodes) {
        Set<String> visited = new HashSet<>();
        List<Set<String>> comps = new ArrayList<>();
        for (String s : nodes) {
            if (!visited.contains(s)) {
                Set<String> comp = new LinkedHashSet<>();
                Deque<String> stack = new ArrayDeque<>();
                stack.push(s);
                visited.add(s);
                while (!stack.isEmpty()) {
                    String u = stack.pop();
                    comp.add(u);
                    for (String v : adj.getOrDefault(u, Collections.emptyList())) {
                        if (!visited.contains(v)) {
                            visited.add(v);
                            stack.push(v);
                        }
                    }
                }
                comps.add(comp);
            }
        }
        return comps;
    }

    // choose cheapest original edge connecting two different components
    private static Edge findReplacementEdge(List<Edge> originalEdges, List<Set<String>> comps) {
        if (comps.size() < 2) return null;
        // map node -> component id
        Map<String, Integer> compOf = new HashMap<>();
        for (int i = 0; i < comps.size(); i++) {
            for (String n : comps.get(i)) compOf.put(n, i);
        }
        Edge best = null;
        for (Edge e : originalEdges) {
            Integer a = compOf.get(e.from);
            Integer b = compOf.get(e.to);
            if (a == null || b == null) continue;
            if (!a.equals(b)) {
                if (best == null || e.weight < best.weight) best = e;
            }
        }
        return best;
    }
}
