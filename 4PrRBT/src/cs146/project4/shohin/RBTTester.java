package cs146.project4.shohin;

import static org.junit.Assert.*;
import org.junit.Test;



public class RBTTester {


    public static double getRunTime(long startTime) {
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000.0;
    }

    public static long getStartTime() {
        return System.nanoTime();
    }

    @Test
    //Test the Red Black Tree
    public void test() {


        RedBlackTree rbt = new RedBlackTree();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str = "Color: 1, Key:D Parent: \n" +
                "Color: 1, Key:B Parent: D\n" +
                "Color: 1, Key:A Parent: B\n" +
                "Color: 1, Key:C Parent: B\n" +
                "Color: 1, Key:F Parent: D\n" +
                "Color: 1, Key:E Parent: F\n" +
                "Color: 0, Key:H Parent: F\n" +
                "Color: 1, Key:G Parent: H\n" +
                "Color: 1, Key:I Parent: H\n" +
                "Color: 0, Key:J Parent: I\n";
        assertEquals(str, makeStringDetails(rbt));

        rbt.printTree();

        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println(" output has runtime of " + runTime + " ms");


    }

    //add tester for spell checker

    public static String makeString(RedBlackTree t) {
        class MyVisitor implements Visitor {
            String result = "";

            public void visit(Node visitKeyNode) {
                result = result + visitKeyNode.key;
            }

        }

        MyVisitor v = new MyVisitor();
        t.preOrderVisit(v);

        long startTime = getStartTime();
        double runTime = getRunTime(startTime);
        System.out.println("makeString method has runtime of " + runTime + " ms");

        return v.result;


    }

    public static String makeStringDetails(RedBlackTree t) {
        {
            class MyVisitor implements Visitor {
                String result = "";

                public void visit(Node visitKeyNode) {

                    //changed method to account for parent of n being null

//                     if(!(n.key).equals(""))
//                     result = result +"Color: "+n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";


                    if (visitKeyNode.parent == null) {
                        result = result + "Color: " + visitKeyNode.color + ", Key:" + visitKeyNode.key + " Parent: " + "\n";
                    } else {
                        result = result + "Color: " + visitKeyNode.color + ", Key:" + visitKeyNode.key + " Parent: " + visitKeyNode.parent.key + "\n";
                    }

                }
            }


            MyVisitor v = new MyVisitor();
            t.preOrderVisit(v);

            long startTime = getStartTime();
            double runTime = getRunTime(startTime);
            System.out.println("makeStringDetail method has runtime of " + runTime + " ms");

            return v.result;
        }
    }




}


