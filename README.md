# Maze Solver

## Overview

## This project is a maze-solving application developed in Java. It provides a visual interface for generating mazes and solving them using various algorithms. Users can select different algorithms and visualize the maze-solving process in real-time.

## Features

- **Maze Generation**: Generates a maze with random obstacles and a specified size.
  
- **Algorithm Options**: Supports implementations of multiple pathfinding algorithms:
  - **Breadth-First Search (BFS)**
  - **Depth-First Search (DFS)**
  - **Dijkstra's Algorithm**
  - **AStar Search Algorithm**
    
- **Visual Interface**: Displays the maze with clear distinctions between obstacles, start, and end nodes.
  
- **Real-Time Visualization**: Tracks and visualizes the pathfinding process with distinct colors for explored nodes.

- **User Interaction**: Select maze size and algorithm via a graphical user interface (GUI).
  
- **Result Presentation**: Shows results with detailed information about the pathfinding process.
  
- **Exception Handling**: Gracefully handles errors and invalid inputs.

## Key Concepts

### Backtracking
Backtracking is a fundamental algorithmic technique used for solving problems incrementally by trying to build a solution piece by piece, removing solutions that fail to meet the criteria at any point. In this maze solver project, backtracking is used in certain algorithms to explore all possible paths from the start node to the end node. When a path leads to a dead end, the algorithm "backtracks" to the previous node to try a different path.

### Data Structures
Understanding data structures is crucial for efficient algorithm design and implementation. In this project, several key data structures are utilized:

- **Arrays and Matrices**: Used to represent the maze grid and store nodes.
- **Lists**: Used for storing neighbors of nodes and maintaining the open and closed sets in pathfinding algorithms.
- **Queues and Stacks**: Employed in BFS and DFS algorithms respectively to manage nodes to be explored.
- **Priority Queues**: Utilized in Dijkstra’s and A* algorithms to efficiently retrieve the node with the lowest cost.
  
### Algorithm Design
Algorithm design involves creating step-by-step procedures for solving problems efficiently.This project demonstrates several algorithms, each with distinct approaches:

- **Breadth-First Search (BFS)**: Explores all possible paths level by level, ensuring the shortest path in an unweighted maze.
- **Depth-First Search (DFS)**: Explores paths by going as deep as possible before backtracking, useful for finding a path or exploring all nodes.
- **Dijkstra's Algorithm**: Finds the shortest path in a weighted graph, where each node has a cost associated with reaching it.
- **AStar Algorithm**: An extension of Dijkstra's that uses heuristics to improve efficiency by prioritizing nodes that are estimated to be closer to the goal.
  
By integrating these algorithms, the project provides a comprehensive demonstration of various pathfinding techniques and their practical applications.

### MultiThreading
Multithreading is a key technique used to improve the performance and responsiveness of applications by performing multiple operations concurrently. In this project, multithreading is utilized to handle the computationally intensive maze-solving processes and the graphical user interface (GUI) updates simultaneously.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE such as IntelliJ IDEA or Eclipse (optional, but recommended)

### Installation
- **Clone the Repository**: git clone https://github.com/ArielSemel/Maze-Java-Project.git
- **Compile and Run**: javac -d bin src/**/*.java ---> java -cp bin Main (Alternatively, you can use an IDE like IntelliJ IDEA or Eclipse to open the project and run it directly from the Main class)
  
## Uploading Images



![image](https://github.com/user-attachments/assets/fde22eb2-d087-493a-897f-da13dbc1bd52)



