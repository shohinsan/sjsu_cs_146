package cs146.project3.AnujShohin;

/**
 * A program that will automatically generate and solve mazes.
 * Each time you run the program, it will generate and print a new random maze and the solution.
 * We used depth-first search (DFS) and breadth-first search (BFS).
 *
 * @author Shohin Abdulkhamidov
 * @author Anuj Kamdar
 */

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Grid {

    Cell[][] grid;
    ArrayList<Cell> dfsCells; //path taken by dfs
    ArrayList<Cell> bfsCells; //path taken by bfs
    int size; //size
    Node[] adjacencyList; //adjacent list keep track of all adjacent nodes
    int visitBfsOrd;
    //    int visitDfsOrd;
    int time;
    int totalCells;
    int visitOrd;

    Random rand; //for generating random index values
    static final int WHITE = 1;
    static final int GREY = 2;

    public Grid(int s) {
        rand = new Random();
        size = s;
        dfsCells = new ArrayList<>();
        bfsCells = new ArrayList<>();
        grid = new Cell[size][size];
        totalCells = size * size;

        //initialize adjacent list with null values

        adjacencyList = new Node[totalCells];
        for (int i = 0; i < totalCells; i++) {
            adjacencyList[i] = null;
        }
    }

    static class Node {
        Node next = null;
        Cell vertex;

        public Node(Cell x) {
            vertex = x;
        }
    }

    //generates a perfect maze
    //starts at (0,0) and ends at (length-1,length-1)
    public void generateMaze() {
        Stack<Cell> cellStack = new Stack<>();
        int totalCells = size * size;
        Cell currentCell = grid[0][0];
        int visitedCells = 1;

        while (visitedCells < totalCells) {
            ArrayList<Cell> neighbors = findNeighbors(currentCell);
            //delete this
            //		System.out.println("neighbors row " +c.row + " col " + c.col);
            if (!neighbors.isEmpty()) {
                //choose a random index between 0 and arraylist size.
                int index = rand.nextInt(neighbors.size());
                //retrieve a neighbor at random
                Cell ney = neighbors.get(index);
                //knock down the wall between it and current cell
                //System.out.println("knock wall");
                currentCell.knockWall(ney);


                //create adjacency list. Index is label - 1. Values of list are the valid neighbors (nodes).
                Node newNode = new Node(ney);
                Node currentNode = adjacencyList[currentCell.label - 1];
                if (currentNode == null) {
                    currentNode = newNode;
                    adjacencyList[currentCell.label - 1] = currentNode;
                } else {
                    while (currentNode.next != null) {
                        currentNode = currentNode.next;
                    }
                    currentNode.next = newNode;
                }

                Node newNode2 = new Node(currentCell);
                currentNode = adjacencyList[ney.label - 1];
                if (currentNode == null) {
                    adjacencyList[ney.label - 1] = newNode2;
                } else {
                    while (currentNode.next != null) {
                        currentNode = currentNode.next;
                    }
                    currentNode.next = newNode2;
                }

                //push from stack
                cellStack.push(currentCell);
                currentCell = ney;
                currentCell.isVisited = true;
                visitedCells++;
                //System.out.println("visited nodes " + visitedCells + " row " + currentCell.row + " col " + currentCell.col + " north " + currentCell.northWall + " south "+ currentCell.southWall + " west " + currentCell.westWall + " east " + currentCell.eastWall);
            } else {
                if (!cellStack.isEmpty()) {

                    currentCell = cellStack.pop();

                }
            }
        }
        //	printAdjacencyList(adjacencyList);
    }

    //finds all neighbors
    public ArrayList<Cell> findNeighbors(Cell cellObject) {
        //neighbors  = new Cell [4];
        ArrayList<Cell> neighbors = new ArrayList<>();
        int row = cellObject.row;
        int col = cellObject.col;
        //if north neighbor
        if (row - 1 >= 0 && grid[row - 1][col].hasIntegrity()) {
            neighbors.add(grid[row - 1][col]);
        }
        //if south neighbor
        if (row + 1 < size && grid[row + 1][col].hasIntegrity()) {
            neighbors.add(grid[row + 1][col]);
        }
        //if east neighbor
        if (col + 1 < size && grid[row][col + 1].hasIntegrity()) {
            neighbors.add(grid[row][col + 1]);
        }
        //if west neighbor
        if (col - 1 >= 0 && grid[row][col - 1].hasIntegrity()) {
            neighbors.add(grid[row][col - 1]);
        }
        return neighbors;
    }

    //key: 0 - blank 1 - numbers, 2 - #
    //search: 0 - blank, 1 - bfs, 2 - dfs
    public void modelMaze(int key) {
        StringBuilder output = new StringBuilder();
        int BLANK = 0;
        int NUMBERS = 1;
        int HASH = 2;
        String ch = "";


        for (int row = 0; row < size; row++) {
            if (row == 0) {

                for (int col = 0; col < size; col++) {
                    //edge condition when row is 0. print first line
                    if (col == 0) {
                        output.append("+ +");
                    } else if (col == size - 1) {
                        output.append("-+\n");
                    } else {
                        output.append("-+");
                    }
                }
            }

            //for every row look at east
            for (int col = 0; col < size; col++) {
                Cell curCell = grid[row][col];
                //edge condition col = 0 start with |
                if (col == 0) {
                    output.append("|");
                }


                //add a space check every cell for its east wall, if it exists add a pipe, if it does not add a space
                if (curCell.eastWall) {
                    if (curCell.isInPath) {
                        if (key == HASH) {
                            ch = "#|";
                        } else if (key == NUMBERS) {
                            ch = String.format("%d", grid[row][col].bfsPath % 10) + "|";
                        } else if (key == BLANK) {
                            ch = " |";
                        }
                    } else {
                        ch = " |";
                    }
                    output.append(ch);
                } else if (curCell.isInPath) {
                    if (key == HASH) {
                        ch = "##";
                    } else if (key == NUMBERS) {
                        ch = String.format("%d", grid[row][col].bfsPath % 10) + " ";
                    } else if (key == BLANK) {
                        ch = " ";
                    }
                    output.append(ch);
                } else {
                    output.append("  ");
                }
                //new line when reach end of line
                if (col == size - 1) {
                    output.append("\n");
                }

            }

            //for every row look at south
            for (int col = 0; col < size; col++) {
                Cell curCell = grid[row][col];
                //edge condition col = 0 start with |
                if (col == 0) {
                    output.append("+");
                }
                if (row == size - 1 && col == size - 1) {
                    output.append(" +");
                }
                // check every cell for its south wall, if it exists add "-+", if it doesn't add a " +"
                else if (curCell.southWall) {
                    output.append("-+");
                } else {
                    if (curCell.isInPath) {
                        if (key == HASH) {
                            ch = "#+";
                        } else if (key == NUMBERS) {
                            ch = " +";

                        } else if (key == BLANK) {
                            ch = " +";
                        }
                        output.append(ch);
                    } else {
                        output.append(" +");
                    }

                }
                //new line when reach end of line
                if (col == size - 1) {
                    output.append("\n");
                }

            }

        }
        //printing full maze
        System.out.println(output);
    }

    //populates grid with cell objects and updating labels for each cell
    public void fill() {
        int vertexNumber = 1;

        //This loop creates a new vertex
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Cell v = new Cell(j, i);
                grid[j][i] = v;
            }
        }

        //adds values to vertex
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[j][i].label = vertexNumber;
                grid[j][i].parent = null;
                vertexNumber++;
            }
        }

        //This loop assigns the neighbors
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                findNeighbors(grid[col][row]);
            }
        }
        //    generateMaze();
    }

    // solves maze use bfs. Using adjacency list find all neighbors
    public void bfs() {
        Cell s = this.grid[0][0];
        s.color = GREY;
        s.distance = 0;
        s.parent = null;

        Queue<Cell> queue = new LinkedList<>();
        queue.add(s);

        //  System.out.println("queue s node " + s.label);
        while (!queue.isEmpty()) {
            Cell u = queue.remove();
            Node v = adjacencyList[u.label - 1];
            //   System.out.println("node v " + v.vertex.color);
            while (v != null) {
                if (v.vertex.color == WHITE) {
                    v.vertex.color = GREY;
                    visitBfsOrd++;
                    v.vertex.visitBfsOrder = visitBfsOrd;
                    v.vertex.distance = u.distance + 1;
                    v.vertex.parent = u;
                    //u.child = v.vertex;

                    queue.add(v.vertex);
                }
                v = v.next;
            }
        }
    }

    //finds the shortest path by recursively calling the end nodes parent until reach the start.
    //sets the inPath variable true if cell is part of the path
    //Used by bfs and dfs
    public void findShortestPath(Cell startVtx, Cell endVtx) {
        //base case reached the end. exit
        if (startVtx.label != endVtx.label) {
            if (endVtx.parent == null)
                System.out.print(" No path exists");
            else {
                findShortestPath(startVtx, endVtx.parent);
                endVtx.parent.child = endVtx;
            }
        }
        endVtx.isInPath = true;

        //System.out.print(endVtx.label);


    }

    public int setShortestPathNum(Cell vertex) {
        Cell current = vertex;
        int pathCounter = 0;
        current.bfsPath = pathCounter;

        while (current.child != null) {
            current = current.child;
            pathCounter++;
            current.bfsPath = pathCounter;
        }
        return pathCounter;
    }

    //dfs method. visit each cell and call dfsvisit method to traverse and find path
    public void dfs() {
        time = 0;
        Cell currentNode;// = grid[grid[0].label-1];
        System.out.println();
        for (int i = 0; i < totalCells; i++) {

            currentNode = grid[i / size][i % size];
            if (currentNode.color == WHITE)
                dfsVisit(currentNode);
        }
        System.out.println();
    }

    //for each cell find the dfs path
    public void dfsVisit(Cell u) {
        time = time + 1;
        u.visitedOrder = visitOrd;
        visitOrd = visitOrd + 1;
        u.disTime = time;
        u.color = GREY;
        Node v = adjacencyList[u.label - 1];
        while (v != null) {
            if (v.vertex.color == WHITE) {
                v.vertex.parent = u;
                dfsVisit(v.vertex);
            }
            v = v.next;
        }
        // u.color = BLACK;
        time = time + 1;
        //  u.finalTime = time;
    }


    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j].parent = null;
                grid[i][j].color = WHITE;
                grid[i][j].child = null;
            }
        }
    }

    public void printPath() {
        Cell current = grid[0][0];
        System.out.print("Path: ");
        int counter = 0;
        while (current != null) {
            System.out.print("(" + current.row + ", " + current.col + ")");
            //if(current.child)
            current = current.child;
            counter++;

        }
        System.out.println("\nLength of path: " + counter);
    }
}