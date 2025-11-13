package com.aida.mst;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private final Map<String, String> parent = new HashMap<>();
    private final Map<String, Integer> rank = new HashMap<>();

    public UnionFind(Iterable<String> items) {
        for (String s : items) {
            parent.put(s, s);
            rank.put(s, 0);
        }
    }

    public String find(String x) {
        String p = parent.get(x);
        if (p == null) return null;
        if (!p.equals(x)) {
            String r = find(p);
            parent.put(x, r);
            return r;
        }
        return p;
    }

    public boolean union(String a, String b) {
        String ra = find(a);
        String rb = find(b);
        if (ra == null || rb == null) return false;
        if (ra.equals(rb)) return false;
        int raRank = rank.getOrDefault(ra, 0);
        int rbRank = rank.getOrDefault(rb, 0);
        if (raRank < rbRank) parent.put(ra, rb);
        else if (raRank > rbRank) parent.put(rb, ra);
        else {
            parent.put(rb, ra);
            rank.put(ra, raRank + 1);
        }
        return true;
    }
}
