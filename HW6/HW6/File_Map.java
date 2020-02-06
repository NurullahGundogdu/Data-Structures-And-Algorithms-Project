package HW6;

import java.util.*;

public class File_Map implements Map
{

    ArrayList<String> fnames = new ArrayList<>();
    ArrayList<List<Integer>> occurances = new ArrayList<>();

    private int size = 0;

    /**
     *
     * @return
     */
    @Override
    public int size()
    {
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
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(Object key) {

        return fnames.contains(key);
    }

    /**
     *
     * @param value
     * @return
     */
    @Override
    public boolean containsValue(Object value) {

        return occurances.contains(value);
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object get(Object key) {

        if(this.containsKey(key)){

            return  occurances.get(fnames.indexOf(key));

        }else
            return null;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    /*Each put operation will extend the occurance list*/
    public Object put(Object key, Object value) {
        if(containsKey(key)){

            List list = occurances.get(fnames.indexOf(key));
            occurances.set(fnames.indexOf(key),(List<Integer>) value);
            return list;
        }

        fnames.add((String) key);
        occurances.add((List<Integer>) value);

        size++;

        return null;

    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public Object remove(Object key) {
        if(this.containsKey(key)){

            List list = occurances.remove(fnames.indexOf(key));
            fnames.remove(key);

            return list;
        }else
            return null;
    }

    /**
     *
     * @param m
     */
    @Override
    public void putAll(Map m) {

        for (int i = 0; i < m.size(); i++) {

            this.put(((File_Map) m).fnames.get(i),((File_Map) m).occurances.get(i));

        }
    }

    /**
     *
     */
    @Override
    public void clear() {
        fnames.clear();
        occurances.clear();
    }

    /**
     *
     * @return
     */
    @Override
    public Set keySet() {
        Set<String> set = new HashSet<>();

        for(int i=0; i<size; i++)
            set.add(fnames.get(i));

        return set;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection values()
    {
        Collection<List<Integer>> coll = new HashSet<>();

        for(int i=0; i<size; i++)
            coll.add(occurances.get(i));

        return coll;
    }

    /**
     *
     * @return
     */
    @Override
    public Set<Entry> entrySet() {

        Map map = new HashMap<>();

        for (int i = 0;  i < size;  i++) {
            map.put(fnames.get(i), occurances.get(i));

        }

        Set<Entry> set = map.entrySet();

        return set;

    }
}
