# 🕸️ Minimum Spanning Tree (MST) Project

This Java project demonstrates the construction, manipulation, and recovery of a **Minimum Spanning Tree (MST)** from a given weighted graph.  
It implements **Prim’s** and **Kruskal’s** algorithms to compare performance and find efficient edge replacements after edge removal.

---

## 📋 Project Description

The goal of this project is to:
1. Build an MST (Minimum Spanning Tree) from a given weighted graph.
2. Display the MST edges before removing any edge.
3. Remove one edge from the MST.
4. Display the resulting disconnected components.
5. Find and add a replacement edge that reconnects the components **while keeping the graph minimum**.
6. Display the final reconnected MST.

This demonstrates not only your MST implementation but also your ability to efficiently handle **edge removal and reconnection**.

---

## 🧩 Project Structure

/src/com/aida/mst/
│
├── Main.java # Main file that reads JSON, runs Prim and Kruskal algorithms, and compares results
├── Graph.java # Graph class that holds nodes and edges
├── Edge.java # Edge class with 'from', 'to', and 'weight'
├── Prim.java # Prim's algorithm implementation
├── Kruskal.java # Kruskal's algorithm implementation
├── UnionFind.java # Union-Find (Disjoint Set) helper class used in Kruskal’s algorithm
└── input.json # Input data file containing graphs and their edges
