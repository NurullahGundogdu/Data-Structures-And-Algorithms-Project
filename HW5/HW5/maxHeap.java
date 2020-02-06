package HW5;

import java.util.Comparator;

public class maxHeap {

    private int [] MH;
    private int size;
    private int maxSize;
    private Comparator comp;

    /**
     *
     * @param comp comparator object
     * @param maxSize maximum size of heap
     */
    public maxHeap(Comparator comp, int maxSize){

        this.comp=comp;
        this.maxSize = maxSize;
        this.size = 0;
        MH = new int[maxSize + 1];
        MH[0] = Integer.MAX_VALUE;

    }

    /**
     *
     * @param element
     */
    public void insert(int element){

        if(size == maxSize){
            System.out.println("Heap is full!!!");
            return;
        }


        MH[++size] = element;

        int tempSize = size;

        while ( comp.compare(MH[tempSize], MH[tempSize / 2]) == 1){

            swap(tempSize, tempSize / 2);
            tempSize = tempSize / 2;

        }

    }

    /**
     * remove smallest element from heap
     * @return first element
     */
    public int remove(){

        int element = MH[1];

        MH[1] = MH[size--];

        heapify(1);

        return  element;
    }

    /**
     *
     * @param position
     * @return
     */
    private boolean isLeaf(int position){

        if( position >= (size / 2) && position <= size)
            return true;

        return false;
    }

    /**
     *
     * @param FirstPosition
     * @param SecondPosition
     */
    private void swap(int FirstPosition, int SecondPosition){

        int value = MH[FirstPosition];
        MH[FirstPosition] = MH[SecondPosition];
        MH[SecondPosition] = value;

    }

    /**
     *
     * @param position
     */
    private void heapify(int position){

        if(isLeaf(position))
            return;

        if(comp.compare(MH[position], MH[ 2 * position ]) == -1 || comp.compare(MH[position], MH[ ( 2 * position ) + 1 ]) == -1)

            if(comp.compare(MH[ 2 * position ],  MH[ ( 2 * position ) + 1 ]) == 1){
                swap(position,2 * position );
                heapify(2 * position );

            }else{
                swap(position,  ( 2 * position ) + 1 );
                heapify( ( 2 * position ) + 1 );

            }

    }

}
