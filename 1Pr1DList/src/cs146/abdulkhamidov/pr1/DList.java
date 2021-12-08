
package cs146.abdulkhamidov.pr1;

/**
 * @author Shohin Abdulkhamidov
 */
public class DList<T> {

    public class Node {
        //--- Variables ------------------------------------------------//
        public T data;
        public Node next;
        public Node previous;

        //--- Constructor ----------------------------------------------//
        public Node(T t) {
            this.data = t;
            this.next = null;
            this.previous = null;
        }
    }
    //------------------------------------------------------------------//

    int count;
    Node head;
    Node tail;

    //--- Default Constructor  -----------------------------------------//
    public DList() {
        count = 0;
        head = null;
        tail = null;
    }

    //------------------------------------------------------------------//
    //---@TODO--- Part A - Insert To Tail Of List ----------------------//
    /**
     * @param data is a variable to add number to a node
     * Add a new item to the set. If the item is already in the set then nothing happens.
     */
    public void insert(T data) {
        if (search(data) == null) {
            Node pdata = new Node(data);
            if (tail == null) {
                head = pdata;
                tail = pdata;
            } else {
                pdata.previous = tail;
                tail.next = pdata;
                tail = pdata;
            }
            count++;
        }
    }



    //-----------------------------------------------------------------//
    //---@TODO--- Part B - Deletes A Node From The List ---------------//
    /**
     * @param data is a variable to add number to a node
     * Remove an item from the set.
     */
    public void delete(T data) {
        Node temp = search(data);
        if (temp != null) {
            if (temp.previous != null) // if temp is head then reset head to temp.next
                temp.previous.next = temp.next;
            else {
                head = temp.next;
            }
            if (temp.next != null) // if temp is tail node then reset tail to temp.previous
                temp.next.previous = temp.previous;
            else {
                tail = temp.previous;
            }
            count--;
        }
    }

    //-----------------------------------------------------------------//
    //---@TODO--- Part C - Returns Number Of Nodes In List ------------//
    /**
     * @return this method returns how many nodes in the set
     * Return the number of items in the set.
     */
    public int count() {
        return count;
    }

    //-----------------------------------------------------------------//
    //---@TODO--- Part D - Determines if number Of Nodes In List ------//
    /**
     * @param data is a variable to add number to a node
     * @return true if the item is in the set
     */
    public boolean contains(T data) {
        return search(data) != null;
    }

    //-------------------------------------------------------------------------//
    //---@TODO--- Part E - Finds Data Node And Returns It ---------------------//
    /**
     * @param data is a variable to add number to a node
     * @return "nothing" if the item is NOT in the list of set
     */
    public Node search(T data) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.equals(data))
                break;
            temp = temp.next;
        }
        return temp;
    }

    //-----------------------------------------------------//\
    //---@TODO--- Prints List From Tail Of List -----------//
    /*
    This is output method not necessary for JUnit, but was created to test in the main method
    so that I can print them into console
    */
    public void output() {
        Node driver = head;
        while (driver != null) {
            System.out.print(driver.data + "\t");
            driver = driver.next;
        }
        System.out.println();
    }
    //-----------------------------------------------------//
    /*
    This is toString method not necessary for JUnit, but was created to test in the main method
    so that I can combine String and Integer object in the same println
    */
    @Override
    public String toString() {
        String str = " ";
        Node driver = head;
        while (driver != null) {
            str += driver.data;
            driver = driver.next;
        }
        return str;
    }
    //-----------------------------------------------------//

    //---@TODO--- Part F - Combines 2 sets and applies union method  -----------//
    /**
     * @param applyUnion creates union of sets 1 and 2
     * @return it prints combined version of sets 1 and 2
     */
    public DList<Integer> getUnion(DList<T> applyUnion) {
        DList<Integer> union = new DList<>();

        Node node = this.head;

        while (node != null) {
            union.insert((Integer) node.data);
            node = node.next;
        }

        Node newNode = applyUnion.head;

        while (newNode != null) {
            union.insert((Integer) newNode.data);
            newNode = newNode.next;
        }
        return union;
    }

    //---@TODO--- Part F - Combines 2 sets and applies intersection method  -----------//
    /**
     * @param applyIntersection creates intersection of sets 1 and 2
     * @return it prints combined version of sets 1 and 2
     */

    public DList<Integer> getIntersection(DList<T> applyIntersection) {
        DList<Integer> intersection = new DList<>();
        Node node = this.head;
        while (node != null) {
            if (applyIntersection.search(node.data) != null) {
                intersection.insert((Integer) node.data);
            }
            node = node.next;
        }
        return intersection;
    }
}
//-----------------------------------------------------//
