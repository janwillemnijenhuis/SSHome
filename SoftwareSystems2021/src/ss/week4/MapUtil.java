package ss.week4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtil {
    // check waarom kan ik dit niet zo kort doen als bij 4.13? containskey returned al true als er een key bestaat
    public static <K, V> boolean isOneOnOne(Map<K, V> map) {
        Set<K> keys;
        Set<V> values = new HashSet<V>();
        if (!map.isEmpty()) {
            keys = map.keySet();
            boolean first = true;
            for (K k : keys) {
                V v = map.get(k);
                if (!first) {
                    if (values.contains(v)) {
                        return false;
                    }
                }
                values.add(v);
                first = false;
            }
        }
        return true;
    }

    public static <K, V> boolean isSurjectiveOnRange(Map<K, V> map, Set<V> range) {
        if (!map.isEmpty()) {
            for (V v : range) {
                if (!map.containsValue(v)) {
                    return false;
                }
            }
        }
        return true;
    }

    // map should be injective
    public static <K, V> Map<V, Set<K>> inverse(Map<K, V> map) {
        Map<V, Set<K>> inverse = new HashMap<>();
        Set<V> values = new HashSet<V>(map.values());
        Set<K> keys = new HashSet<K>(map.keySet());
        if (!map.isEmpty()) {
            for (V v : values) {
                Set<K> valuekeys = new HashSet<K>();
                for (K k : keys) {
                    if (v == map.get(k)) {
                        valuekeys.add(k);
                    }
                }
                inverse.put(v, valuekeys);
            }
        }
        return inverse;
    }

    // map should be injective and surjective
    public static <K, V> Map<V, K> inverseBijection(Map<K, V> map) {
        Map<V, K> bijection = new HashMap<>();
        Set<V> values = new HashSet<V>(map.values());
        Set<K> keys = new HashSet<K>(map.keySet());
        if (!map.isEmpty()) {
            for (V v : values) {
                for (K k : keys) {
                    if (v == map.get(k)) {
                        bijection.put(v, k);
                    }
                }
            }
        }
        return bijection;
    }
	
    public static <K, V, W> boolean compatible(Map<K, V> f, Map<V, W> g) {
         // TODO: implement, see exercise P-4.13
         return false;
    }
	
    public static <K, V, W> Map<K, W> compose(Map<K, V> f, Map<V, W> g) {
         // TODO: implement, see exercise P-4.13
         return null;
    }
	
}
