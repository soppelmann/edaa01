package map;

public class SimpleHashMap<K,V> implements Map<K,V> {

    private Entry<K, V>[] table;
    private static final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private int capacity = 16;


    public static void main(String[] args) {

        SimpleHashMap<Integer, Integer> map = new SimpleHashMap<Integer, Integer>(10);


    }

    /**
     * Constructs an empty hashmap with the default initial capacity (16) and the
     * default load factor (0.75).
     * @param i
     */
    public SimpleHashMap() { //similar to prev. lab w/ private helper function
        table = (Entry<K,V>[]) new Entry[capacity];
    }

    /**
     * Constructs an empty hashmap with the specified initial capacity and the
     * default load factor (0.75).
     */
    @SuppressWarnings("unchecked")
    public SimpleHashMap(int capacity) {
        this.capacity = capacity;
        this.table = (Entry<K, V>[]) new Entry[capacity];
    }


    @Override
    public V get(Object key) {
        Entry<K, V> e = find(index((K) key), (K) key);

        if (e != null) {
            return e.getValue();
        } else {
            return null;
        }

    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public V put(K key, V value) {
        Entry<K,V> e = find(index(key), key);

        if(e == null){
            e = table[index(key)];
            size++;
            if(e == null){
                table[index(key)] = new Entry<K,V>(key, value);
            }else{
                while(e.next != null) e = e.next;
                e.next = new Entry<K,V>(key, value);
            }
        }else{
            V old_value = e.value;
            e.value = value;
            return old_value;
        }


        // If higher than load factor, rehash
        if (this.size() >= LOAD_FACTOR * this.table.length) {
            this.rehash();
        }

        return null;
    }

    // Private help functions

    private void rehash() {
        Entry<K,V>[] old_table = table; //this.table or just table

        //double length
        table = (Entry<K,V>[]) new Entry[table.length*2];

        size = 0; // reset size //garbage collector does this

        // for each old value add to new table
        for (Entry<K, V> e : old_table) {
            Entry<K, V> entry = e;

            while (entry != null) {
                // Place them back using put logic
                this.put(entry.getKey(), entry.getValue());
                entry = entry.next;
            }
        }
    }

    private int index(K key) {
        int index = key.hashCode() % table.length;
        if (index < 0) { //kan detta ens hända
            return index + table.length;
        }
        return index;
    }

    private Entry<K,V> find(int index, K key) {
        Entry<K,V> e = table[index];

        while(e != null && !e.key.equals(key)){
            e = e.next;
        }

        if (e != null) {
            return e;
        } else {
            return null;
        }

    }


    @Override
    public V remove(Object arg0) {
        K key = (K) arg0;
        int index = this.index(key);

        Entry<K,V> node = table[index];

        if(this.isEmpty()) {
            return null;
        }

        if(node == null) {
            return null;
        }

        if(node.key.equals(key)) {
            table[index] = node.next;
            size--;
            return node.value;
        }

        Entry<K,V> prevnode = node;
        node = node.next;

        while(node != null) {
            if(node.key.equals(key)) {
                prevnode.next = node.next;
                size--;
                return node.value;

            }
            prevnode = node;
            node = node.next;

        }
        return null;

    }

    @Override
    public int size() {
        return size;
    }

    public String show() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                str.append(i + "\t" + table[i].toString());

                Entry<K, V> temp = table[i];

                // Loop through all keys in map
                while (temp.next != null) {
                    str.append("\t" + table[i].next.toString());
                    temp = temp.next;
                }

                str.append("\n");
            }
            else {
                str.append(i + "\t null \n");
            }
        }

        return str.toString();
    }


    private static class Entry<K,V> implements Map.Entry<K,V> {

        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }


    }



}
