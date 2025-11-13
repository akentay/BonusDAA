package com.aida.mst;

import java.util.*;

public class Graph {
    public final List<String> nodes;
    public final List<Edge> edges;

    public Graph(List<String> nodes, List<Edge> edges) {
        this.nodes = new ArrayList<>(nodes);
        this.edges = new ArrayList<>(edges);
    }

    // adjacency map for convenience
    public Map<String, List<Edge>> adjacency() {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(e);
        }
        return adj;
    }
}
