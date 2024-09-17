import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar implements Algorithm {

    @Override
    public String getName(){
        return Definitions.ALGORITHM_A_STAR;
    }

    @Override
    public boolean solve(Node startNode, Maze maze) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Node::getTotalCost));
        startNode.setCost(0);
        startNode.setHeuristic(heuristic(startNode, maze.getRows() - 1, maze.getColumns() - 1));
        priorityQueue.add(startNode);

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (currentNode.isVisited()) continue;  // Skip if already visited

            Maze.setCounterNodes(Maze.getCounterNodes()+1);
            currentNode.setVisited(true);
            maze.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);

            // Check if we reached the goal
            if (currentNode.getRow() == maze.getRows() - 1 && currentNode.getColumn() == maze.getColumns()- 1) {
                return true;
            }

            for (Node neighbor : currentNode.getNeighbors()) {
                if (!neighbor.isVisited()) {
                    int newCost = currentNode.getCost() + 1;
                    if (newCost < neighbor.getCost()) {
                        neighbor.setCost(newCost);
                        neighbor.setHeuristic(heuristic(neighbor, maze.getRows() - 1, maze.getColumns() - 1));
                        neighbor.setPrevious(currentNode);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
        return false;
    }

    private int heuristic(Node node, int goalRow, int goalColumn) {
        // Manhattan distance: |currentX - goalX| + |currentY - goalY|
        return Math.abs(node.getRow() - goalRow) + Math.abs(node.getColumn() - goalColumn);
    }

}
