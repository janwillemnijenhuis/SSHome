package ss.week4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MapUtil {
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
         // TODO: implement, see exercise P-4.11
         return false;
    }
    
    public static <K, V> Map<V, Set<K>> inverse(Map<K, V> map) {
         // TODO: implement, see exercise P-4.12
         return null;
    }
    
    public static <K, V> Map<V, K> inverseBijection(Map<K, V> map) {
         // TODO: implement, see exercise P-4.12
         return null;
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
