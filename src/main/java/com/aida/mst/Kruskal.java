package com.aida.mst;

import java.util.*;

public class Kruskal {
    public static List<Edge> kruskalMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        List<Edge> edges = new ArrayList<>(graph.edges);
        Collections.sort(edges); // by weight
        UnionFind uf = new UnionFind(graph.nodes);

        for (Edge e : edges) {
            if (!uf.find(e.from).equals(uf.find(e.to))) {
                uf.union(e.from, e.to);
                result.add(e);
            }
            if (result.size() == graph.nodes.size() - 1) break;
        }
        return result;
    }
}
