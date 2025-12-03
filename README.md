# 🧩 Intelligent Sudoku Solver

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![AI](https://img.shields.io/badge/AI-Constraint%20Satisfaction-blue?style=for-the-badge)
![Algorithms](https://img.shields.io/badge/Algorithm-AC3%20%2B%20Forward%20Checking-green?style=for-the-badge)
![License](https://img.shields.io/github/license/AntonMG4/sudoku_solver?style=for-the-badge&logo=github)

> A high-performance AI agent capable of solving thousands of Sudoku puzzles in milliseconds using Constraint Satisfaction Problem (CSP) techniques and heuristic search.

---

## 📖 Project Overview

This project implements an intelligent agent designed to solve Sudoku puzzles provided in text format. Unlike brute-force approaches, this solver treats Sudoku as a **Constraint Satisfaction Problem (CSP)**.

It combines pre-processing algorithms with optimized search strategies to drastically reduce the computational time required to find a solution, achieving near-instant results even for complex grids.

### ⚡ Performance
As detailed in the technical report, the implementation of **Forward Checking** reduced execution time from seconds to **milliseconds** compared to standard backtracking.

| Algorithm | Performance | Complexity |
| :--- | :--- | :--- |
| Standard Backtracking | 🐢 Slow | High state-space exploration |
| **AC3 + Forward Checking** | ⚡ **< 0.01s** | Heavily pruned state space |

---

## 🧠 Algorithms & Logic

The solver follows a strict pipeline to guarantee efficiency:

1.  **Modeling:** The board is treated as a 9x9 matrix of variables, where each cell has a domain of `{1..9}`.
2.  **Constraints:** Rules are applied for Rows, Columns, and 3x3 Subgrids (AllDisjoint).
3.  **AC3 Algorithm (Arc Consistency):** * Runs before the search begins.
    * Prunes the domains of variables by ensuring consistency between arcs, reducing the search space initially.
4.  **Backtracking with Forward Checking:**
    * Performs a Depth-First Search (DFS) to assign values.
    * **Forward Checking Optimization:** Whenever a value is assigned, it is immediately removed from the domains of all related neighbors. If a domain becomes empty, the branch is discarded immediately (pruning).

---

## 📂 Project Structure

```text
sudoku_solver/
├── src/
│   ├── Restricciones/    # Constraint definitions (AllDisjoint, etc.)
│   ├── AC3.java          # Arc Consistency algorithm implementation
│   ├── Busqueda.java     # Backtracking & Forward Checking logic
│   ├── Estado.java       # State representation for the search tree
│   ├── LectorFichero.java # Input parsing logic
│   ├── GeneradorFichero.java # Output logic
│   └── Practica05_exe.java # Main application entry point
├── info.pdf # Technical documentation and benchmarks
│  
└── README.md
