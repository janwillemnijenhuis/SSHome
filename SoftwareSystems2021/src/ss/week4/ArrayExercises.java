package ss.week4;

public class ArrayExercises {

    /**
     * counts the number of negative numbers
     * @param arr
     * @return amount of negative numbers in the array
     */
    public static int countNegativeNumbers(int[] arr) {
        int negCount = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] < 0) {
                negCount += 1;
            }
        }
        return negCount;
    }

    /**
     * reverses an array without creating a new one.
     * @param ints
     */
    public static void reverseArray(int[] ints) {
        for(int i = 0; i < ints.length / 2; i ++) {
            int temp = ints[i];
            ints[i] = ints[ints.length - i - 1];
            ints[ints.length - i - 1] = temp;
        }
    }

    class SimpleList {
        public class Element {}

        public class Node {
            public Node next;
            public Element element;
        }

        private Node first;

        private Node find(Element element) {
            Node p = first;
            if (p == null) {
                return null;
            }
            while (p.next != null && !p.next.element.equals(element)) {
                p = p.next;
            }
            if (p.next == null) {
                return null;
            } else {
                return p;
            }
        }

        public void remove(Element element) {
            Node p = find(element);
            if (p != null) {
                p.next = p.next.next;
            }
        }
    }
}
