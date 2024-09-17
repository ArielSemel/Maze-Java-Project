import java.util.Stack;

public class DFS implements Algorithm{

    @Override
    public String getName(){
        return Definitions.ALGORITHM_DFS;
    }

    @Override
    public boolean solve(Node startNode, Maze maze){

        Stack<Node> stack = new Stack<>();
        stack.add(startNode);

        while (!stack.empty()) {
            Node currentNode = stack.pop();

            if (!currentNode.isVisited()) {
                Maze.setCounterNodes(Maze.getCounterNodes()+1);
                currentNode.setVisited(true);
                maze.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);

                if (currentNode.getRow() == maze.getRows() - 1 && currentNode.getColumn() == maze.getColumns() - 1) {
                    return true;
                }

                for (Node neighbor : currentNode.getNeighbors()) {
                    if (!neighbor.isVisited()) {
                        stack.add(neighbor);
                    }
                }
            }
        }

        return false;
    }
}
