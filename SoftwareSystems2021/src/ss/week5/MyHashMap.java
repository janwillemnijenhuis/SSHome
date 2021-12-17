package ss.week5;
import ss.week4.LinkedList;
import ss.week5.tictactoe.Tuple2;

/**
 * This program is my own implementation of a HashMap<String,String>. It contains al the necessary methods and
 * functionality to compute the hash function for a certain key value pair
 * @author Jan Willem Nijenhuis
 */
public class MyHashMap {
    LinkedList<Tuple2<Integer, String>> linkedList = new LinkedList<>(); // the elements of the linked list are the hashcode and the value

    public MyHashMap() {} // constructor

    /**
     * convert the key to a hashcode
     * @param key
     * @return the hashcode
     */
    private int hash(String key) {
        return key.hashCode();
    }

    /**
     * gets the value corresponding to some key
     * @return the value
     */
    public String get(String key) {
        if (linkedList.size() > 0) {
            Tuple2<Boolean, Integer> contained = containsKey(key);
            if (contained.getFirst()) {
                Tuple2<Integer, String> element = new Tuple2<>(hash(key), linkedList.get(contained.getSecond()).getSecond());
                return element.getSecond();
            }
        }
        return "error: element does not exist";
    }

    /**
     * puts the key-value pair in the map
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        Tuple2<Integer, String> element = new Tuple2<>(hash(key), value);
        if (linkedList.size() > 0) {
            Tuple2<Boolean, Integer> contained = containsKey(key);
            if (contained.getFirst()) {
                linkedList.remove(new Tuple2<>(hash(key), value)); // if key is in hashmap already remove it
            }
        }
        linkedList.add(0, element); // add key in front of linkedlist
    }

    /**
     * removes the key-value pair corresponding to this key from the hashmap
     * @param key
     */
    public void remove(String key) {
        Tuple2<Boolean, Integer> contained = containsKey(key);
        if (contained.getFirst()) {
            Tuple2<Integer, String> element = new Tuple2<>(hash(key), linkedList.get(contained.getSecond()).getSecond());
            linkedList.remove(element);
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public Tuple2<Boolean, Integer> containsKey(String key) {
        int i = 0;
        int hashCode = hash(key);
        LinkedList.Node runner = linkedList.getFirst();
        do {
            Tuple2<Integer, String> element = (Tuple2<Integer, String>) runner.getElement();
            if (element.getFirst().equals(hashCode)) {
                return new Tuple2<>(true, i);
            }
            runner = runner.next;
            i++;
        } while (runner!= null);
        return new Tuple2<>(false, -1);
    }

    /**
     * checks the size of the hashmap
     * @return the number of key-value pairs
     */
    public int size() {
        return linkedList.size();
    }

}
