package PART1;


import java.io.*;

public class MAIN {


    private static int num;
    private static int num2;
    public static void main(String [] args) throws IOException {



        CHARACTER array[][]=readFromFile(args[0]);

        int temp=0;

        for (int i=0; i<num; i++)
            for (int j=0; j< num2; j++) {
                if (array[i][j].getName() == '1') {
                    array = changeToLetter(array[i][j], array, (char)('A' + temp));
                    temp++;
                }

            }


        for (int i=0; i<num; i++) {
            for (int j = 0; j < num2; j++)
                System.out.print(array[i][j].getName()+" ");
            System.out.println();
        }

        System.out.println();
        System.out.println("Number of white components "+temp);

    }

    /**
     *
     * @param obj
     * @param arr
     * @param character
     * @return character array
     * take character array and alphabetical chaacter
     * find '1' and change with character
     */
    private static CHARACTER[][] changeToLetter(CHARACTER obj, CHARACTER[][] arr,Character character){

        STACK<CHARACTER> temp=new STACK<>();

        temp.push(obj);

        while (!temp.empty()){

            CHARACTER ch=temp.pop();
            arr[ch.getRow()][ch.getColumn()].setName(character);
            if(ch.getColumn()!=num2-1)
                if(arr[ch.getRow()][ch.getColumn()+1].getName()=='1'){
                    temp.push(arr[ch.getRow()][ch.getColumn()+1]);
                }
            if(ch.getColumn()!=0)
                if(arr[ch.getRow()][ch.getColumn()-1].getName()=='1'){
                    temp.push(arr[ch.getRow()][ch.getColumn()-1]);
                }
            if(ch.getRow()<num-1)
                if(arr[ch.getRow()+1][ch.getColumn()].getName()=='1'){
                    temp.push(arr[ch.getRow()+1][ch.getColumn()]);
                }
            if(ch.getRow()!=0)
                if(arr[ch.getRow()-1][ch.getColumn()].getName()=='1'){
                    temp.push(arr[ch.getRow()-1][ch.getColumn()]);
                }

        }
        return arr;

    }

    /**
     *
     * @param filename
     * @return character array
     * @throws IOException
     * read input file and find row and column number
     * if file cannot open throw exception
     */
    private static CHARACTER[][] readFromFile(String filename) throws IOException {


        File file = new File( filename );
        Reader reader = new FileReader(file);

        int c;
        int row=1,column=0;
        while( (c = reader.read()) != -1) {
            if( c == '\n' ){
                row++;
            }

            if(c==48 || c==49) {
                if(row==1)
                    column++;
            }
        }
        reader.close();

        CHARACTER array[][]=new CHARACTER[row][column];


        File file1 = new File( filename);
        Reader reader1 = new FileReader(file1);

        int i=0,j=0;
        while( (c = reader1.read()) != -1) {
            if( c == '\n' ){
                i++;
                j=0;
            }

            if(c==48 || c==49) {
                CHARACTER temp=new CHARACTER((char)c,i,j);
                array[i][j]=temp;
                j++;
            }

        }
        reader1.close();

        num=row;
        num2=column;

        return array;
    }

}
