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


---

## ⚙️ How It Works

### Step 1: Build MST
The program reads a graph from `input.json` and builds an MST using **Prim** and **Kruskal** algorithms.

### Step 2: Display MST
It prints out all edges included in both MSTs with their total weights and computation times.

### Step 3: Remove an Edge
You can simulate removing one edge from the MST to split the graph into two disconnected components.

### Step 4: Reconnect Components
The algorithm finds a **replacement edge** that reconnects the two components with **minimum additional cost** — keeping the tree minimal.

---

## 🧠 Algorithms Used

### 🔹 Prim’s Algorithm
- Starts from any node.
- Always adds the smallest edge that connects the visited set with an unvisited node.
- Uses a **Priority Queue** for efficient edge selection.

### 🔹 Kruskal’s Algorithm
- Sorts all edges by weight.
- Adds edges one by one, skipping any that form a cycle.
- Uses a **Union-Find (Disjoint Set)** structure to detect cycles efficiently.

---

## 📁 Input Format (`input.json`)

Example of input file:
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D"],
      "edges": [
        {"from": "A", "to": "B", "weight": 2},
        {"from": "A", "to": "C", "weight": 3},
        {"from": "B", "to": "C", "weight": 1},
        {"from": "C", "to": "D", "weight": 4}
      ]
    }
  ]
}

🖥️ How to Run
✅ Requirements

Java 17+ (or any version that supports org.json)

VS Code / IntelliJ IDEA / Command line

org.json library in classpath (you can get it from Maven: org.json:json:20231013)

▶️ Steps

Clone the repository:

git clone https://github.com/yourusername/MST-Project.git
cd MST-Project

