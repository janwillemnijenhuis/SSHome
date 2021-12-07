package ss.week4;

public class DoublyLinkedList<E> {

    private int size;
    private Node head;

    public static void main(String[] args) {
        DoublyLinkedList<Object> list = new DoublyLinkedList<>();
        Object object1 = new Object();
        list.add(0, object1);
        System.out.println(list.size);
    }

    //@ ensures size == 0;
    public DoublyLinkedList() {
        size = 0;
        head = new Node(null);
        head.next = head;
        head.previous = head;
    }

    /**
     * Inserts a new element at a given index in the list.
     * @param index The index at which to insert the element
     * @param element The element to add
     */
    /*@ requires element != null;
    requires index <= size;
    ensures size == \old(size) + 1;
    ensures get(index) == element;
     @*/
    public void add(int index, E element) {
        Node newNode = new Node(element);
        // element will be added inbetween q and p
        if(index == 0) {
            newNode.previous = head;
            newNode.next = head.next;
            head.next = newNode;
        } else if(index == this.size) {
            // index is last item or more, add to end of linked list
            Node q = getNode(this.size - 1);
            q.next = newNode;
            newNode.previous = q;
            newNode.next = null;
        } else if(index > this.size) {
            System.err.println("Index out of bounds");
            this.size -= 1;
        } else {
            // add item in between nodes
            Node q = getNode(index - 1);
            Node p = getNode(index);
            newNode.next = p;
            newNode.previous = q;
            p.previous = newNode;
            q.next = newNode;
        }
        this.size += 1;
    }

    /**
     * Removes an element at a given index
     * @param index the index to remove the element at
     */
    /*@ requires index <= size;
    ensures size == \old(size) - 1;
     @*/
    public void remove(int index) {
        if(index == 0) {
            // if you want to remove the first element
            head.next = head.next.next;
            head.next.previous = head;
        } else if(index == this.size - 1) {
            // if you want to remove the last element
            Node q = getNode(this.size - 2);
            q.next = null;
        } else if(index > this.size - 1) {
            // if the index is too large
            System.err.println("Index out of bounds");
            this.size += 1;
        } else {
            // remove item in between nodes
            Node q = getNode(index - 1);
            Node p = getNode(index + 1);
            q.next = p;
            p.previous = q;
        }
        this.size -= 1;
    }

    //@ requires index <= size;
    public E get(int index) {
        Node p = getNode(index);
        return p.element;
    }

    /**
     * The node containing the element with the specified index.
     * The head node if the specified index is -1.
     */
    //@ requires i > -1 && i < size;
    public Node getNode(int i) {
        Node p = head;
        int pos = -1;
        while (pos < i) {
            p = p.next;
            pos = pos + 1;
        }
        return p;
    }

    public int size() {
        return this.size;
    }
    public class Node {
        public Node(E element) {
            this.element = element;
            this.next = null;
            this.previous = null;
        }

        private E element;
        public Node next;
        public Node previous;

        public E getElement() {
            return element;
        }
    }
}
