package com.aida.mst;

import java.util.*;

public class Prim {
    public static List<Edge> primMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        if (graph.nodes.isEmpty()) return result;

        Set<String> visited = new HashSet<>();
        Map<String, List<Edge>> adj = graph.adjacency();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String start = graph.nodes.get(0);
        visited.add(start);
        pq.addAll(adj.getOrDefault(start, Collections.emptyList()));

        while (!pq.isEmpty() && result.size() < graph.nodes.size() - 1) {
            Edge e = pq.poll();
            String u = e.from, v = e.to;
            String next = visited.contains(u) ? v : u;
            if (visited.contains(next)) continue;
            visited.add(next);
            result.add(e);
            for (Edge ne : adj.getOrDefault(next, Collections.emptyList())) {
                if (!visited.contains(ne.from) || !visited.contains(ne.to)) pq.add(ne);
            }
        }
        return result;
    }
}
