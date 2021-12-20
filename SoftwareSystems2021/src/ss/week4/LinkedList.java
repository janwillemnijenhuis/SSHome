package ss.week4;

public class LinkedList<E> {

    private int size;
    private Node first;

    //@ ensures size == 0 && first == null;
    public LinkedList() {
        size = 0;
        first = null;
    }

    public Node getFirst() {
        return this.first;
    }

    public void add(int index, E element) {
        Node newNode = new Node(element);
        if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else {
            Node p = getNode(index - 1);
            newNode.next = p.next;
            p.next = newNode;
        }
        size = size + 1;
    }

    /**
     * @param element the element to remove
     */
    //@ ensures first.getElement() == element || findBefore(element) != null ==> size == \old(size) - 1;
    public void remove(E element) {
        Node q = findBefore(element);
        if (q != null) {
            Node p = q.next.next;
            q.next = p;
        } else if (q == null) {
            this.first = this.first.next;
        }
        this.size -= 1;
    }

    public int find(E element) {
        Node runner = this.first;
        int i = 0;
        while (runner.getElement() != element) {
            runner = runner.next;
            if (runner == null) {
                return -1;
            }
            i++;
        }
        return i;
    }

    public Node findBefore(E element) {
        // improve
        Node runner = this.first;
        E check = runner.getElement();
        int i = 0;
        while (check != element) {
            runner = runner.next;
            if (runner != null) {
                check = runner.getElement();
                i += 1;
            } else {
                check = null;
                break;
            }
        }
        if (check != null && i >= 1) {
            return getNode(i - 1);
        } else {
            return null;
        }
    }

    /**
     * @param index the index to get the element at
     * @return the element at index index
     */
    //@ requires index >= 0 && index < size;
    public E get(int index) {
        Node p = getNode(index);
        return p.element;
    }

    /**
     * @param i the index to get the Node at
     * @return the Node at index i
     */
    //@ requires i >= 0 && i < size;
    private Node getNode(int i) {
        Node p = first;
        int pos = 0;
        while (pos != i) {
            p = p.next;
            pos = pos + 1;
        }
        return p;
    }

    /**
     * @return the size of the list
     */
    //@ ensures \result >= 0;
    public int size() {
        return size;
    }

    public class Node {
        private E element;
        public Node next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }

        public E getElement() {
            return element;
        }
    }
}
