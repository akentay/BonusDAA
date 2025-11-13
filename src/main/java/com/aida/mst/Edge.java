package com.aida.mst;

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    public final String from;
    public final String to;
    public final int weight;

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // undirected equality: A-B same as B-A
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge e = (Edge) o;
        return weight == e.weight &&
               ((Objects.equals(from, e.from) && Objects.equals(to, e.to)) ||
                (Objects.equals(from, e.to) && Objects.equals(to, e.from)));
    }

    @Override
    public int hashCode() {
        // unordered pair hash
        int a = from.hashCode();
        int b = to.hashCode();
        return Integer.hashCode(weight) ^ Integer.rotateLeft(a ^ b, 16);
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("%s-%s(%d)", from, to, weight);
    }
}
