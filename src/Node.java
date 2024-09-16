//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int row;
    private int column;
    private boolean visited;
    private final int value;
    Node[][] matrixNodes;
    private List<Node> neighbors = new ArrayList();
    private int cost;
    private int heuristic;
    private Node previous;

    public Node(int row, int column, int value, Node[][] matrixNodes) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.matrixNodes = matrixNodes;
        this.visited = false;
        this.cost = Integer.MAX_VALUE;
        this.heuristic = 0;
        this.previous = null;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void addNeighbor(Node neighbor) {
        this.neighbors.add(neighbor);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getValue() {
        return this.value;
    }

    public List<Node> getNeighbors() {
        int matrixRows = this.matrixNodes.length;
        int matrixColumns = this.matrixNodes[0].length;
        if (this.row - 1 >= 0 && this.matrixNodes[this.row - 1][this.column].getValue() != 1) {
            this.neighbors.add(this.matrixNodes[this.row - 1][this.column]);
        }

        if (this.row + 1 < matrixRows && this.matrixNodes[this.row + 1][this.column].getValue() != 1) {
            this.neighbors.add(this.matrixNodes[this.row + 1][this.column]);
        }

        if (this.column + 1 < matrixColumns && this.matrixNodes[this.row][this.column + 1].getValue() != 1) {
            this.neighbors.add(this.matrixNodes[this.row][this.column + 1]);
        }

        if (this.column - 1 >= 0 && this.matrixNodes[this.row][this.column - 1].getValue() != 1) {
            this.neighbors.add(this.matrixNodes[this.row][this.column - 1]);
        }

        return this.neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristic() {
        return this.heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getTotalCost() {
        return this.cost + this.heuristic;
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
