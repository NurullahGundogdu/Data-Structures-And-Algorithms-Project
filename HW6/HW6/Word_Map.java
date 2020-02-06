package HW6;

import java.util.*;

public class Word_Map implements Map, Iterable
{

    final static int INITCAP=10;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[];
    private int size;
    private int nextIndex;
    private int indexOfStart;

    public Word_Map() {
        nextIndex = -1;
        size = 0;
        this.table = new Node[INITCAP];
    }

    /**
     *
     * @return
     */
    @Override
    public Iterator iterator() {

        Iterator iter = new Iterator() {

            Node temp = table[indexOfStart];


            @Override
            public boolean hasNext() {

                return temp != null;
            }

            @Override
            public Object next() {

                Node temp2 = temp;
                temp = temp.next;

                return temp2;
            }
        };

        return iter;
    }

    /**
     *
     */
    static class Node {

        private String word;
        private File_Map fMap;
        public Node next = null;

        public Node(String word, File_Map fMap){
            this.fMap = fMap;
            this.word = word;
        }

        public String getWord(){return word;}
        public File_Map getfMap(){return fMap;}

    }

    /**
     *
     * @return
     */
    @Override
    public int size() {

        return size;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {

        return size == 0;

    }

    /**
     *
     * @return
     */
    public Node getNode(){
        return table[indexOfStart];
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(Object key) {

        Iterator iter = this.iterator();

        while (iter.hasNext()){

            Node node = (Node) iter.next();
            String s = (String) key;
            if(node.word.equals(s)) {
                return true;
            }
        }
        return false;

    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(Object value) {
        Iterator iter = this.iterator();

        while (iter.hasNext()){

            Node node = (Node) iter.next();

            if(node.fMap.fnames.containsAll(((File_Map) value).fnames) && node.fMap.occurances.containsAll(((File_Map) value).occurances))
                return true;

        }

        return false;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {

        int index = findIndexOfObject(key);

        if( table[index] != null)
            return table[index].fMap;
        else
            return null;

    }

    /**
     *
     * @param key
     * @return
     */
    private int findIndexOfObject(Object key){

        int index = key.hashCode() % CURRCAP;

        if(index < 0)
            index += CURRCAP;


        while ((table[index] != null) && (! (key.equals(table[index].word)))){

            index++;
            if (index >= CURRCAP)
                index = 0;

        }

        return  index;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public Object put(Object key, Object value) {

        if((size / CURRCAP) > LOADFACT)
            rehashTable();

        int index = findIndexOfObject(key);

        if(table[index] == null){

            table[index] = new Node((String) key,(File_Map) value);
            size++;

            if(nextIndex == -1) {

                indexOfStart = index;
                nextIndex = index;

            }else {

                table[nextIndex].next = table[index];
                nextIndex = index;

            }

            return null;
        }

        File_Map fMap = table[index].fMap;
        table[index].fMap = (File_Map) value;

        return fMap;
    }

    /**
     *
     */
    private void rehashTable(){

        Node newTable[] = table;

        CURRCAP = 2 * CURRCAP + 1;

        table = new Node[CURRCAP];

        this.nextIndex = -1;

        this.size = 0;

        Node node = newTable[indexOfStart];


        while(node != null){
            put(node.word, node.fMap);
            node = node.next;
        }

    }

    /**
     *
     * @param m
     */
    @Override
    public void putAll(Map m) {

        Iterator iter = ((Word_Map) m).iterator();

        while (iter.hasNext()){

            Node node = (Node) iter.next();

            put(node.word, node.fMap);

        }

    }

    /**
     *
     */
    @Override
    public void clear() {

        Arrays.fill(table,null);

        size = 0;
        nextIndex = -1;
    }

    /**
     *
     * @return
     */
    @Override
    public Set keySet() {

        Set<String> set = new HashSet<>();

        Iterator iter = iterator();

        while (iter.hasNext()){
            Node node = (Node) iter.next();

            set.add(node.word);

        }

        return set;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection values() {

        Collection<String> coll = new HashSet<>();

        Iterator iter = iterator();

        while (iter.hasNext()){
            Node node = (Node) iter.next();

            coll.add(node.word);

        }


        return coll;
    }

    /**
     *
     * @return
     */
    @Override
    public Set<Entry> entrySet() {
        Map map = new HashMap<>();

        for (Object o : this) {

            Node node = (Node) o;

            map.put(node.word, node.fMap);

        }


        Set<Entry> set = map.entrySet();

        return set;
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object remove(Object key) {

        if(containsKey(key)){
            File_Map file_map = (File_Map) this.get(key);

            int index = findIndexOfObject(key);

            if(index == indexOfStart)
                indexOfStart = findIndexOfObject(table[indexOfStart].next.word);

            table[index] = null;

            size--;

            if(size == 0)
                nextIndex = -1;

            return file_map;

        }else
            return null;
    }
}
