package ss.week5.tictactoe;
/**
 * Tuple class which can be used to create a tuple of two types with getters and setters
 *
 * @param <K> type 1
 * @param <V> type 2
 * @author Jan Willem Nijenhuis
 */
public class Tuple2<K, V> {

    private K first;
    private V second;

    public Tuple2(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return this.first;
    }

    public void setFirst(K k) {
        this.first = k;
    }

    public V getSecond() {
        return this.second;
    }

    public void setSecond(V v) {
        this.second = v;
    }
}
