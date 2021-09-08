class StackOfTwo {
    int[] arr;
    int size;
    int topOfFirstStack, topOfSecondStack;

    // Constructor
    StackOfTwo(int n) {
        size = n;
        arr = new int[n];
        topOfFirstStack = n / 2 + 1;
        topOfSecondStack = n / 2;
    }

    // Method to push an element x to stack1
    void firstStackPush(int x) {

        // There is at least one empty
        // space for new element
        if (topOfFirstStack > 0) {
            topOfFirstStack--;
            arr[topOfFirstStack] = x;
        } else {
            System.out.print("Stack Overflow"
                    + " By element :" + x + "\n");
            return;
        }
    }

    // Method to push an element
    // x to stack2
    void secondStackPush(int x) {

        // There is at least one empty
        // space for new element
        if (topOfSecondStack < size - 1) {
            topOfSecondStack++;
            arr[topOfSecondStack] = x;
        } else {
            System.out.print("Stack Overflow"
                    + " By element :" + x + "\n");
            return;
        }
    }

    // Method to pop an element from first stack
    int firstStackPop() {
        if (topOfFirstStack <= size / 2) {
            int x = arr[topOfFirstStack];
            topOfFirstStack++;
            return x;
        } else {
            System.out.print("Stack UnderFlow");
            System.exit(1);
        }
        return 0;
    }

    // Method to pop an element
    // from second stack
    int secondStackPop() {
        if (topOfSecondStack >= size / 2 + 1) {
            int x = arr[topOfSecondStack];
            topOfSecondStack--;
            return x;
        } else {
            System.out.print("Stack UnderFlow");
            System.exit(1);
        }
        return 1;
    }
};

class Driver {
    /* Main Class to test two Stacks */
    public static void main(String[] args) {
        StackOfTwo ts = new StackOfTwo(3);
        ts.firstStackPush(3);
        ts.secondStackPush(8);
        ts.secondStackPush(13);
        ts.firstStackPush(9);
        ts.secondStackPush(5);
        System.out.print("Popped element from firstStack is " + ts.firstStackPop() + "\n");
        ts.secondStackPush(38);
        System.out.print("Popped element from secondStack is " + ts.secondStackPop()
                + "\n");
    }
}
