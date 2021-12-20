package ss.week5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Predicates {

    public Predicates() {}

    public static <T> void remove(Collection<T> coll, Predicate<T> pred) {
        ArrayList<T> removed = new ArrayList<>();
        for (T t: coll) {
            if (pred.test(t)) {
                removed.add(t);
            }
        }
        for (T r: removed) {
            coll.remove(r);
        }
    }

    public static <T> void retain(Collection<T> coll, Predicate<T> pred) {
        ArrayList<T> removed = new ArrayList<>();
        for (T t: coll) {
            if (!pred.test(t)) {
                removed.add(t);
            }
        }
        for (T r: removed) {
            coll.remove(r);
        }
    }

    public static <T> List<T> collect(Collection<T> coll, Predicate<T> pred) {
        List<T> list = new ArrayList<>();
        for (T t: coll) {
            if (pred.test(t)) {
                list.add(t);
            }
        }
        return list;
    }

    public static <T> int find(ArrayList<T> list, Predicate<T> pred) {
        int i = 0;
        for (T l: list) {
            if (pred.test(l)) {
                return i;
            }
            i++;
        }
        return -1;
    }

}
