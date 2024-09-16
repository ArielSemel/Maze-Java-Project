//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class Maze extends JFrame {
    private int[][] values;
    private boolean[][] visited;
    private final int startRow;
    private final int startColumn;
    private ArrayList<JButton> buttonList;
    private int rows;
    private int columns;
    private boolean backtracking;
    private final String algorithm;
    private static int counterNodes;

    public Maze(String algorithm, int size, int startRow, int startColumn) {
        counterNodes = 0;
        this.algorithm = algorithm;
        this.startRow = startRow;
        this.startColumn = startColumn;
        this.initializeMaze(size);
        this.setupFrame();
        this.solveMaze();
    }

    private void initializeMaze(int size) {
        Random random = new Random();
        this.values = new int[size][];

        for(int i = 0; i < this.values.length; ++i) {
            int[] row = new int[size];

            for(int j = 0; j < row.length; ++j) {
                if (i <= 1 && j <= 1) {
                    row[j] = 0;
                } else {
                    row[j] = random.nextInt(8) % 7 == 0 ? 1 : 0;
                }
            }

            this.values[i] = row;
        }

        this.values[0][0] = 0;
        this.values[size - 1][size - 1] = 0;
        this.visited = new boolean[this.values.length][this.values.length];
        this.buttonList = new ArrayList();
        this.rows = this.values.length;
        this.columns = this.values.length;
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(0);
        this.setSize(725, 725);
        this.setResizable(false);
        this.setLayout(new GridLayout(this.rows, this.columns));

        for(int i = 0; i < this.rows * this.columns; ++i) {
            int value = this.values[i / this.rows][i % this.columns];
            JButton jButton = new JButton(String.valueOf(i));
            if (value == 1) {
                jButton.setBackground(Color.BLACK);
            } else {
                jButton.setBackground(Color.WHITE);
            }

            this.buttonList.add(jButton);
            this.add(jButton);
        }

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Maze.this.exitApplication();
            }
        });
    }

    private void solveMaze() {
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            protected Boolean doInBackground() {
                Boolean var10000;
                Node startNode;
                switch (Maze.this.algorithm) {
                    case "DFS":
                        startNode = new Node(Maze.this.startRow, Maze.this.startColumn, 0, Maze.this.createMatrixNodes());
                        var10000 = Maze.this.DFS_ALGORITHM(startNode);
                        break;
                    case "BFS":
                        startNode = new Node(Maze.this.startRow, Maze.this.startColumn, 0, Maze.this.createMatrixNodes());
                        var10000 = Maze.this.BFS_ALGORITHM(startNode);
                        break;
                    case "Dijkstra":
                        startNode = new Node(Maze.this.startRow, Maze.this.startColumn, 0, Maze.this.createMatrixNodes());
                        var10000 = Maze.this.Dijkstra(startNode);
                        break;
                    case "A*":
                        startNode = new Node(Maze.this.startRow, Maze.this.startColumn, 0, Maze.this.createMatrixNodes());
                        var10000 = Maze.this.AStar(startNode);
                        break;
                    default:
                        var10000 = false;
                }

                return var10000;
            }

            protected void done() {
                try {
                    boolean result = (Boolean)this.get();
                    String message = String.format("<html><body style='font-size:30px;'><p style='color:blue;'>Results</p><p><b>Algorithm:</b> <span style='color:green;'>%s</span></p><p><b>Solution:</b> <span style='color:%s;'>%s</span></p><p><b>Number of Nodes:</b> <span style='color:orange;'>%d</span></p></body></html>", Maze.this.algorithm, result ? "green" : "red", result ? "Found" : "Not Found", Maze.counterNodes);
                    JOptionPane.showMessageDialog(Maze.this, message, "Results", 1);
                    int returnMenu = JOptionPane.showConfirmDialog(Maze.this, "Do you want to return to the menu?", "Return to Menu", 0);
                    if (returnMenu == 0) {
                        Maze.this.dispose();
                        new Main();
                    } else {
                        System.exit(0);
                    }
                } catch (Exception var4) {
                    var4.printStackTrace();
                }

            }
        };
        worker.execute();
    }

    public void setSquareAsVisited(int x, int y, boolean visited) {
        try {
            if (!visited) {
                this.visited[x][y] = false;
                ((JButton)this.buttonList.get(x * this.columns + y)).setBackground(Color.WHITE);
                Thread.sleep(0L);
                this.backtracking = true;
            } else {
                if (this.backtracking) {
                    Thread.sleep(375L);
                    this.backtracking = false;
                }

                this.visited[x][y] = true;

                for(int i = 0; i < this.visited.length; ++i) {
                    for(int j = 0; j < this.visited[i].length; ++j) {
                        if (this.visited[i][j]) {
                            if (i == x && y == j) {
                                ((JButton)this.buttonList.get(i * this.rows + j)).setBackground(Color.RED);
                            } else {
                                ((JButton)this.buttonList.get(i * this.rows + j)).setBackground(Color.BLUE);
                            }
                        }
                    }
                }
            }

            Thread.sleep(visited ? 75L : 18L);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public Node[][] createMatrixNodes() {
        Node[][] matrixNodes = new Node[this.rows][this.columns];

        for(int i = 0; i < this.rows; ++i) {
            for(int j = 0; j < this.columns; ++j) {
                matrixNodes[i][j] = new Node(i, j, this.values[i][j], matrixNodes);
            }
        }

        return matrixNodes;
    }

    public boolean BFS_ALGORITHM(Node startNode) {
        Queue<Node> queue = new LinkedList();
        queue.add(startNode);

        while(true) {
            Node currentNode;
            do {
                if (queue.isEmpty()) {
                    return false;
                }

                currentNode = (Node)queue.poll();
            } while(currentNode.isVisited());

            ++counterNodes;
            currentNode.setVisited(true);
            PrintStream var10000 = System.out;
            int var10001 = currentNode.getRow();
            var10000.println("(" + var10001 + "," + currentNode.getColumn() + ")");
            this.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);
            if (currentNode.getRow() == this.rows - 1 && currentNode.getColumn() == this.columns - 1) {
                System.out.println("Number of nodes: " + counterNodes);
                return true;
            }

            Iterator var4 = currentNode.getNeighbors().iterator();

            while(var4.hasNext()) {
                Node neighbor = (Node)var4.next();
                if (!neighbor.isVisited()) {
                    queue.add(neighbor);
                }
            }
        }
    }

    public boolean DFS_ALGORITHM(Node startNode) {
        Stack<Node> stack = new Stack();
        stack.add(startNode);

        while(true) {
            Node currentNode;
            do {
                if (stack.empty()) {
                    return false;
                }

                currentNode = (Node)stack.pop();
            } while(currentNode.isVisited());

            ++counterNodes;
            currentNode.setVisited(true);
            this.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);
            if (currentNode.getRow() == this.rows - 1 && currentNode.getColumn() == this.columns - 1) {
                return true;
            }

            Iterator var4 = currentNode.getNeighbors().iterator();

            while(var4.hasNext()) {
                Node neighbor = (Node)var4.next();
                if (!neighbor.isVisited()) {
                    stack.add(neighbor);
                }
            }
        }
    }

    public boolean AStar(Node startNode) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue(Comparator.comparingInt(Node::getTotalCost));
        startNode.setCost(0);
        startNode.setHeuristic(this.heuristic(startNode, this.rows - 1, this.columns - 1));
        priorityQueue.add(startNode);

        while(true) {
            Node currentNode;
            do {
                if (priorityQueue.isEmpty()) {
                    return false;
                }

                currentNode = (Node)priorityQueue.poll();
            } while(currentNode.isVisited());

            ++counterNodes;
            currentNode.setVisited(true);
            this.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);
            if (currentNode.getRow() == this.rows - 1 && currentNode.getColumn() == this.columns - 1) {
                return true;
            }

            Iterator var4 = currentNode.getNeighbors().iterator();

            while(var4.hasNext()) {
                Node neighbor = (Node)var4.next();
                if (!neighbor.isVisited()) {
                    int newCost = currentNode.getCost() + 1;
                    if (newCost < neighbor.getCost()) {
                        neighbor.setCost(newCost);
                        neighbor.setHeuristic(this.heuristic(neighbor, this.rows - 1, this.columns - 1));
                        neighbor.setPrevious(currentNode);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
    }

    public boolean Dijkstra(Node startNode) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue(Comparator.comparingInt(Node::getCost));
        startNode.setCost(0);
        priorityQueue.add(startNode);

        while(true) {
            Node currentNode;
            do {
                if (priorityQueue.isEmpty()) {
                    return false;
                }

                currentNode = (Node)priorityQueue.poll();
            } while(currentNode.isVisited());

            ++counterNodes;
            currentNode.setVisited(true);
            this.setSquareAsVisited(currentNode.getRow(), currentNode.getColumn(), true);
            if (currentNode.getRow() == this.rows - 1 && currentNode.getColumn() == this.columns - 1) {
                return true;
            }

            Iterator var4 = currentNode.getNeighbors().iterator();

            while(var4.hasNext()) {
                Node neighbor = (Node)var4.next();
                if (!neighbor.isVisited()) {
                    int newCost = currentNode.getCost() + 1;
                    if (newCost < neighbor.getCost()) {
                        neighbor.setCost(newCost);
                        neighbor.setPrevious(currentNode);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }
    }

    private int heuristic(Node node, int goalRow, int goalColumn) {
        return Math.abs(node.getRow() - goalRow) + Math.abs(node.getColumn() - goalColumn);
    }

    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", 0);
        if (confirm == 0) {
            System.exit(0);
        }

    }
}
