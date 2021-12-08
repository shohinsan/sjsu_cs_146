package cs146.project3.AnujShohin;

/**
 * A program that will automatically generate and solve mazes.
 * Each time you run the program, it will generate and print a new random maze and the solution.
 * We used depth-first search (DFS) and breadth-first search (BFS).
 *
 * @author Shohin Abdulkhamidov
 * @author Anuj Kamdar
 */


import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JUnit {

    Grid grid1;
    Grid grid2;
    Grid grid3;

    public static double getRunTime(long startTime) {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000.0;
    }

    public static long getStartTime() {
        return System.nanoTime();
    }

    @Test
    //generate random number with a seed
    public void testGenerate() {



        int seed = 40;
        int min = 4;
        int max = 10;
        Random rand = new Random(seed);
        grid1 = new Grid(rand.nextInt(max-min+1)+min);
        grid2 = new Grid(rand.nextInt(max-min+1)+min);
        grid3 = new Grid(rand.nextInt(max-min+1)+min);
        //testModelMaze(grid1);
        System.out.println("grid 1" + grid1.size);
        // Print Run-Time


        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("testGenerate runtime: " + runTime + " ms");

    }


    @Test
    public void testModelMaze(){
        testGenerate();
        grid1.fill();
        grid1.generateMaze();
        //check each element to make sure each element has been explored
        for(int row = 0; row<grid1.grid.length; row++) {
            for(int col = 0; col < grid1.grid[row].length; col++){
                //System.out.println("row col" + row + " " + col);
                boolean flag = verifyCell(grid1.grid[row][col]);
                //System.out.println("flag " + flag);
                Assertions.assertTrue(flag);
            }
        }

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("testModelMaze runtime: " + runTime + " ms");
    }
    //method to verify cells
    public boolean verifyCell (Cell c) {
        //System.out.println(" " + c.northWall + "  " + c.southWall + "  " + c.westWall + "  " + c.eastWall);
        return !(c.eastWall && c.northWall && c.southWall && c.westWall); //means at least one wall is broken hence it is explored
    }

    //check bfs dfs for 3 diff grids
    @Test
    public void gridTest() {

        testGenerate();
        grid1.fill();
        grid1.generateMaze();

        testBfsDfs(grid1);

        grid2.fill();
        grid2.generateMaze();
        testBfsDfs(grid2);

        grid3.fill();
        grid3.generateMaze();
        testBfsDfs(grid3);

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("gridTest runtime: " + runTime + " ms");

    }
    //checking if the shortest path is the same for bfs and dfs
    //c
    public void testBfsDfs(Grid gr) {
        //run bfs and dfs.

        gr.bfs();
        gr.findShortestPath(gr.grid[0][0], gr.grid[gr.size-1][gr.size-1]);
        int bfsPath = gr.setShortestPathNum(gr.grid[0][0]);
        gr.modelMaze(1);

        gr.clear();
        gr.dfs();
        gr.findShortestPath(gr.grid[0][0], gr.grid[gr.size-1][gr.size-1]);
        int dfsPath = gr.setShortestPathNum(gr.grid[0][0]);
        gr.modelMaze(1);

        Assertions.assertSame(bfsPath, dfsPath);

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("testBfsDfs runtime: " + runTime + " ms");

    }
}