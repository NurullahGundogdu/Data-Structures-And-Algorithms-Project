package PART1;

public class STACK<E> {

    private Object array[];

    private int size=0;
    private int row=1;
    private int column=0;

    public int getRow() { return row; }
    public void setRow(int row) { this.row+=row; }
    public int getColumn() {
        return column;
    }
    public void setColumn(int column) { this.column += column; }
    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }


    public void push(E item){

        if(size==0){
            array=new Object[1];
            array[0]=item;
            size++;
        }else{
            Object arr[]=new Object[size+1];
            for (int i=0; i<size; i++)
                arr[i]=array[i];

            arr[size]=item;
            array=arr;
            size++;
        }
    }

    public E peek(){
        if(this.empty())
            throw new IndexOutOfBoundsException();
        if(size-1>=0)
            return (E)array[size-1];

        return (E)array[0];

    }

    public E pop(){

        if(this.empty())
            throw new IndexOutOfBoundsException();

        E item=(E)" ";
        if(size>0)
            item=(E)array[size-1];

        Object arr[]=new Object[size-1];
        for (int i=0; i<size-1; i++)
            arr[i]=array[i];

        array=arr;
        size--;

        return item;

    }

    public boolean empty(){

        return size == 0;
    }


    public void reverse(){

        if(!this.empty()) {
            Object arr[] = new Object[size];

            for (int i = size - 1; i >= 0; i--)
                arr[(size-1)-i]=array[i];

            array=arr;
        }

    }

}
