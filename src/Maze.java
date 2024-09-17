import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.SwingWorker;

public class Maze extends JFrame{

    private int[][] values;
    private boolean[][] visited;
    private final int startRow;
    private final int startColumn;
    private ArrayList<JButton> buttonList;
    private int rows;
    private int columns;
    private boolean backtracking;
    private final Algorithm algorithm;
    private static int counterNodes;


    public Maze(Algorithm algorithm, int size, int startRow, int startColumn) {

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
        for (int i = 0; i < values.length; i++) {
            int[] row = new int[size];
            for (int j = 0; j < row.length; j++) {
                if (i > 1 || j > 1) {
                    row[j] = random.nextInt(8) % 7 == 0 ? Definitions.OBSTACLE : Definitions.EMPTY;
                } else {
                    row[j] = Definitions.EMPTY;
                }
            }
            values[i] = row;
        }

        values[0][0] = Definitions.EMPTY;
        values[size - 1][size - 1] = Definitions.EMPTY;
        this.visited = new boolean[this.values.length][this.values.length];
        this.buttonList = new ArrayList<>();
        this.rows = values.length;
        this.columns = values.length;
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(Definitions.WINDOW_WIDTH, Definitions.WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLayout(new GridLayout(rows, columns));

        for (int i = 0; i < rows * columns; i++) {
            int value = values[i / rows][i % columns];
            JButton jButton = new JButton(String.valueOf(i));
            if (value == Definitions.OBSTACLE) {
                jButton.setBackground(Color.BLACK);
            } else {
                jButton.setBackground(Color.WHITE);
            }
            this.buttonList.add(jButton);
            this.add(jButton);
        }

        this.setVisible(true);

        // Add window listener for exit confirmation
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApplication();
            }
        });
    }

    private void solveMaze() {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                Node startNode = new Node(startRow, startColumn, Definitions.EMPTY, createMatrixNodes());
                return algorithm.solve(startNode, Maze.this);
            }

            @Override
            protected void done() {

                try {
                    boolean result = get();
                    ResultMessage rm = new ResultMessage(result, Maze.this);
                    rm.display();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public void setSquareAsVisited(int x, int y, boolean visited) {
        try {
            if (visited) {
                if (this.backtracking) {
                    Thread.sleep(Definitions.PAUSE_BEFORE_NEXT_SQUARE * 5);
                    this.backtracking = false;
                }
                this.visited[x][y] = true;
                for (int i = 0; i < this.visited.length; i++) {
                    for (int j = 0; j < this.visited[i].length; j++) {
                        if (this.visited[i][j]) {
                            if (i == x && y == j) {
                                this.buttonList.get(i * this.rows + j).setBackground(Color.RED);
                            } else {
                                this.buttonList.get(i * this.rows + j).setBackground(Color.BLUE);
                            }
                        }
                    }
                }
            } else {
                this.visited[x][y] = false;
                this.buttonList.get(x * this.columns + y).setBackground(Color.WHITE);
                Thread.sleep(Definitions.PAUSE_BEFORE_BACKTRACK);
                this.backtracking = true;
            }
            Thread.sleep(visited ? Definitions.PAUSE_BEFORE_NEXT_SQUARE : Definitions.PAUSE_BEFORE_NEXT_SQUARE / 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node[][] createMatrixNodes() {
        Node[][] matrixNodes = new Node[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixNodes[i][j] = new Node(i, j, values[i][j], matrixNodes);
            }
        }
        return matrixNodes;
    }

    private void exitApplication() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public int[][] getValues() {
        return values;
    }

    public void setValues(int[][] values) {
        this.values = values;
    }

    public boolean[][] getVisited() {
        return visited;
    }

    public void setVisited(boolean[][] visited) {
        this.visited = visited;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(ArrayList<JButton> buttonList) {
        this.buttonList = buttonList;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public boolean isBacktracking() {
        return backtracking;
    }

    public void setBacktracking(boolean backtracking) {
        this.backtracking = backtracking;
    }

    public Algorithm getAlgorithm() {
        return algorithm;
    }

    public static int getCounterNodes() {
        return counterNodes;
    }

    public static void setCounterNodes(int counterNodes) {
        Maze.counterNodes = counterNodes;
    }
}



