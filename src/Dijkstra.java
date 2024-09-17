import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra implements Algorithm {

    @Override
    public String getName(){
        return Definitions.ALGORITHM_DIJKSTRA;
    }

    @Override
    public boolean solve(Node startNode, Maze maze) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(n -> n.getCost() + heuristic(n, maze)));
        startNode.setCost(0);
        priorityQueue.add(startNode);

        while (!priorityQueue.isEmpty()) {
            Node currentNode = priorityQueue.poll();

            if (currentNode.isVisited()) continue;  // Skip if already visited

            Maze.setCounterNodes(Maze.getCounterNodes() + 1);  // Count visited nodes
            currentNode.setVisited(true);
            maze.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);

            // Check if we reached the goal
            if (currentNode.getRow() == maze.getRows() - 1 && currentNode.getColumn() == maze.getColumns() - 1) {
                return true;  // Goal reached
            }

            // Explore the neighbors
            for (Node neighbor : currentNode.getNeighbors()) {
                if (!neighbor.isVisited()) {  // Ignore visited nodes and Obstacles
                    int newCost = currentNode.getCost() + 1;

                    if (newCost < neighbor.getCost()) {
                        neighbor.setCost(newCost);
                        neighbor.setPrevious(currentNode);
                        priorityQueue.add(neighbor);  // Add neighbor to the queue for future exploration
                    }
                }
            }
        }
        return false;  // No solution found
    }

    // Heuristic function to prioritize nodes closer to the goal
    private int heuristic(Node node, Maze maze) {
        int goalRow = maze.getRows() - 1;
        int goalCol = maze.getColumns() - 1;
        // Manhattan distance (absolute difference in rows and columns)
        return Math.abs(node.getRow() - goalRow) + Math.abs(node.getColumn() - goalCol);
    }

}
