package database;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 8/28/2015.
 */
public class Models {

    private class AbstractModel implements Queryable {

        @Override
        public AbstractModel find(QueryParameters parameters) {
            return null;
        }
    }

    private interface Queryable {

        /**
         *
         * @param parameters
         * @return
         */
        AbstractModel find(QueryParameters parameters);
    }

    private interface Updatable {

        /**
         *
         * @param parameters
         * @return
         */
        AbstractModel update(QueryParameters parameters);
    }

    public static class QueryParameters<K, V> implements Map<K, V> {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public V get(Object key) {
            return null;
        }

        @Override
        public V put(K key, V value) {
            return null;
        }

        @Override
        public V remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<K> keySet() {
            return null;
        }

        @Override
        public Collection<V> values() {
            return null;
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
    static class Champion {
        private int id;
        private String name;

        public Champion(String name, int id) {
            this.name = name;
            this.id = id;
        }
    }
}