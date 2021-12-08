package cs146.project3.AnujShohin;

/**
 * A program that will automatically generate and solve mazes.
 * Each time you run the program, it will generate and print a new random maze and the solution.
 * We used depth-first search (DFS) and breadth-first search (BFS).
 *
 * @author Shohin Abdulkhamidov
 * @author Anuj Kamdar
 */


public class Cell {
    int row;
    int col;

    boolean northWall;
    boolean southWall;
    boolean westWall;
    boolean eastWall;

    int visitBfsOrder;
    Cell parent;
    Cell child;

    public int label;

    int disTime;
    int color = Grid.WHITE;


    boolean isVisited = false;
    boolean isInPath = false;
    int distance;
    public int bfsPath;
    public int visitedOrder;


    public Cell (int row, int col) {
        this.row = row;
        this.col = col;

        northWall = true;
        southWall = true;
        westWall = true;
        eastWall = true;
        label = 0;
    }

    public boolean hasIntegrity() {
        return this.eastWall == this.northWall && this.southWall == this.westWall && this.eastWall == this.southWall;
    }
    public void knockWall (Cell ney) {
        //if north
        if(this.row -1==ney.row && this.col == ney.col) {
            this.northWall = false;
            ney.southWall = false;

        }
        //if south
        if(this.row +1 == ney.row && this.col == ney.col) {
            this.southWall=false;
            ney.northWall = false;
        }
        //if east
        if(this.row==ney.row && this.col+1 == ney.col) {
            this.eastWall = false;
            ney.westWall = false;
        }
        //if west
        if(this.row == ney.row && this.col-1 == ney.col) {
            this.westWall = false;
            ney.eastWall = false;
        }
    }
}