package cs146.abdulkhamidov.pr1;

import org.junit.Test;
import static org.junit.Assert.*;

public class JUnit {

    /**
     * @return a separate sub-method to print run-time of each method created in DList class
     */
    public static long getStartTime() {return System.nanoTime();}

    // ---------------------------------------------------------------//
    /**
     * @param startTime begins counting the runtime of each method
     * @return prints run-time of each method after execution
     */

    public static double getRunTime(long startTime) {
        long endTime = System.nanoTime();
        return (endTime-startTime)/1000000.0;
    }
    // ---------------------------------------------------------------//

    // --- Tested insert() method for correctness --------------------//
    @Test
    public void insert () {

        DList<Integer> set1 = new DList<>();
        set1.insert(5);
        set1.insert(6);
        set1.insert(7);
        assertTrue(set1.contains(5));
        assertTrue(set1.contains(6));
        assertTrue(set1.contains(7));
        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("insert run-time: "+runTime+" ms");
    }

    // --- Tested count() method for correctness --------------------//
    @Test
    public void count () {
        DList<Integer> set1 = new DList<>();
        set1.insert(5);
        set1.insert(6);
        set1.insert(7);
        assertEquals(3, set1.count());

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("count run-time: "+runTime+" ms");
    }

    // --- Tested delete() method for correctness --------------------//
    @Test
    public void delete () {
        DList<Integer> set1 = new DList<>();
        set1.insert(5);
        set1.insert(6);
        set1.insert(7);
        set1.delete(5);
        set1.delete(6);
        assertTrue(set1.contains(7));
        assertEquals(1, set1.count());

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("delete run-time: "+runTime+" ms");
    }

    // --- Tested search() method for correctness --------------------//
    @Test
    public void search () {

        DList<Integer> set1 = new DList<>();

        set1.insert(5);
        set1.insert(6);
        set1.insert(7);

        Integer n1 = set1.search(5).data;
        Integer n2 = set1.search(6).data;
        Integer n3 = set1.search(7).data;

        assertEquals(5,n1.intValue());
        assertEquals(6,n2.intValue());
        assertEquals(7,n3.intValue());

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("search run-time: "+runTime+" ms");

    }

    // --- Tested getUnion() method for correctness --------------------//
    @Test
    public void getUnion () {
        DList<Integer> set1 = new DList<>();
        DList<Integer> set2 = new DList<>();
        DList<Integer> set3 = new DList<>();
        set1.insert(5);
        set1.insert(6);
        set1.insert(7);
        set2.insert(7);
        set2.insert(8);
        set2.insert(9);
        set3 = set1.getUnion(set2);
        assertTrue(set3.contains(5));
        assertTrue(set3.contains(6));
        assertTrue(set3.contains(7));
        assertTrue(set3.contains(8));
        assertTrue(set3.contains(9));
        assertEquals(5, set1.getUnion(set2).count());

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("getUnion run-time: "+runTime+" ms");
    }

    // --- Tested getIntersection() method for correctness --------------------//
    @Test
    public void getIntersection () {
        DList<Integer> set1 = new DList<>();
        DList<Integer> set2 = new DList<>();
        DList<Integer> set3 = new DList<>();
        set1.insert(5);
        set1.insert(6);
        set1.insert(7);
        set2.insert(7);
        set2.insert(8);
        set2.insert(9);
        set3 = set1.getIntersection(set2);
        assertTrue(set3.contains(7));
        assertEquals(1, set1.getIntersection(set2).count());

        // Print Run-Time
        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("getIntersection run-time: "+runTime+" ms");
    }
}
