package HW5;

import java.util.Comparator;


public class PriorityQueues {

    private int size;
    private int maxSize;
    private maxHeap HEAP;

    /**
     *
     * @param comp
     * @param maxSize
     */
    public PriorityQueues(Comparator comp, int maxSize){

        HEAP = new maxHeap(comp, maxSize);
        this.size=0;
        this.maxSize=maxSize;

    }

    /**
     *
     * @param element
     * @return
     */
    public boolean offer(int element){

        if(size==maxSize)
            return false;

        HEAP.insert(element);

        size++;

        return true;
    }

    /**
     *
     * @return
     */
    public int poll(){
        if(isEmpty())
            return Integer.parseInt(null);

        int element = HEAP.remove();

        size--;

        return element;
    }

    /**
     *
     * @return
     */
    private boolean isEmpty(){

        return size == 0;

    }

    /**
     *
     * @return
     */
    public int Size(){
        return size;
    }
}
