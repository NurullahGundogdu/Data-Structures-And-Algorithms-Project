package HW4;

import java.util.NoSuchElementException;
import java.util.Iterator;


public class arrayIterator<T> implements Iterator<T>  {

    private T[] data;
    private int index=0;

    public arrayIterator(T[][] dataset) {

        this.data=(T[]) new Object[dataset.length*dataset[0].length];
        helperFunction(dataset,0,0,dataset[0].length-1,dataset.length-1);
        this.index=0;
    }

    private void helperFunction(T[][] matrix, int rowStart, int colStart, int colLength,int rowLength){

        this.firstRecursive(matrix,rowStart,colLength,rowStart);

        this.secondRecursive(matrix, rowStart+1, colLength, rowLength);

        if(rowStart+1 <= rowLength){
            this.thirdRecursive(matrix, colStart, colLength-1, rowLength);
        }

        if(colStart+1 <= colLength){
            this.forthRecursive(matrix, rowStart, colStart, (rowLength-1));
        }
        if(rowStart+1 <= rowLength-1 && colStart+1 <= colLength-1){
            helperFunction(matrix, rowStart+1, colStart+1, colLength-1, rowLength-1);
        }
    }

    private void firstRecursive(T[][] matrix, int rowStart, int colLength, int row){

        if(rowStart<=colLength){
            data[index]=matrix[row][rowStart];
            index++;
            rowStart++;
            firstRecursive(matrix,rowStart,colLength,row);
        }
    }

    private void secondRecursive(T[][] matrix, int rowStart, int colLength,int rowLength){

        if(rowStart<=rowLength){
            data[index]=matrix[rowStart][colLength];
            index++;
            rowStart++;
            secondRecursive(matrix,rowStart,colLength,rowLength);
        }
    }

    private void thirdRecursive(T[][] matrix, int colStart, int colLength,int rowLength){

        if(colLength >= colStart){
            data[index]=matrix[rowLength][colLength];
            index++;
            colLength--;
            thirdRecursive(matrix,colStart,colLength,rowLength);
        }
    }

    private void forthRecursive(T[][] matrix, int rowStart, int colStart,int rowLength){

        if(rowLength>rowStart){
            data[index]=matrix[rowLength][colStart];
            index++;
            --rowLength;
            forthRecursive(matrix,rowStart,colStart,rowLength);
        }
    }
    
    @Override
    public boolean hasNext()
    {
        return index < data.length;
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        int temp=index;
        index++;
        return data[temp];
    }

    @Override
    public void remove() {

        throw new UnsupportedOperationException("Not yet implemented");
    }


}
