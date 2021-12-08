package cs146.project3.AnujShohin;

/**
 * A program that will automatically generate and solve mazes.
 * Each time you run the program, it will generate and print a new random maze and the solution.
 * We used depth-first search (DFS) and breadth-first search (BFS).
 *
 * @author Shohin Abdulkhamidov
 * @author Anuj Kamdar
 */

import java.util.Scanner;

public class MazeRunner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the size of your choice (4-10): ");
        int size; //default value to initialize
        try {
            String s = scanner.nextLine();
            size = Integer.parseInt(s);
            if (!(size >= 4 && size < 11))
                System.out.println("Out of bound try again");
            else {
                Grid mm = new Grid(size);
                mm.fill();
                mm.generateMaze();
                mm.modelMaze(0);

                System.out.println("BFS");
                mm.bfs();
                mm.findShortestPath(mm.grid[0][0], mm.grid[size-1][size-1]);
                mm.setShortestPathNum(mm.grid[0][0]);
                mm.modelMaze(1);
                mm.modelMaze(2);
                mm.printPath();
                System.out.println("Visited cells "+mm.visitBfsOrd);

                System.out.println("DFS");
                mm.clear();
                mm.dfs();
                mm.findShortestPath(mm.grid[0][0], mm.grid[size-1][size-1]);
                mm.setShortestPathNum(mm.grid[0][0]);
                mm.modelMaze(1);
                mm.modelMaze(2);
                mm.printPath();
                System.out.println("Visited cells "+mm.visitOrd);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entered invalid number.");
        }
    }
}