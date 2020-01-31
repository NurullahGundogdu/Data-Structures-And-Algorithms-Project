package PART2;

import java.io.*;

public class MAIN {
    public static void main(String [] args) throws IOException {



        Equation eq=new Equation();

        readFromFile(args[0],eq);

        System.out.println(eq.calculate());

    }


    /**
     *
     * @param filename
     * @param eq
     * @throws IOException
     * read input from file  if file dosent open throw exception
     */
    private static void readFromFile(String filename, Equation eq) throws IOException {

        String str;
        int i=0, j=0, c, lineNum=1;

        File file1 = new File( filename );
        Reader reader1 = new FileReader(file1);

        while( (c = reader1.read()) != -1) {
            if( c == '\n' )
                lineNum++;
        }
        reader1.close();

        String [] Line =new String [lineNum];

        File dosya = new File(filename);

        BufferedReader read = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(dosya), "UTF8"));

        while ((str = read.readLine()) != null) {
            Line[i]=str;
            i++;
        }

        read.close();
        Double [] valu=new Double[i-2];
        String [] paramete=new String[i-2];
        i=0;

        while (i<lineNum){
            if(Line[i].contains("=")){
                String[] temp = Line[i].split("=");
                valu[j]=Double.parseDouble(temp[1]);
                paramete[j]=temp[0];
                j++;
            }

            if(Line[i].contains("(")){
                String[] temp = Line[i].split(" ");
                temp=convert(temp);
                eq.convertToPostfix(temp);
            }
            i++;

        }
        eq.valu=valu;
        eq.paramete=paramete;

    }

    /**
     *
     * @param arr
     * @return String array
     * take string array and if it has sin, cos or abs convert to their mode
     */

    private static String[] convert(String [] arr){

        String temp[]=new String[arr.length];
        int j=0, size=0;
        for (int i=0; i<arr.length; i++){

            if(arr[i].contains("abs") || arr[i].contains("sin") || arr[i].contains("cos") ){
               temp[j]=arr[i];
               i++;
               int k=1, s=1;
               while (k<=s){
                   if(arr[i].equals("(") || arr[i].contains("abs") || arr[i].contains("sin") || arr[i].contains("cos") )
                       s++;
                   if (arr[i].equals(")"))
                       k++;
                   temp[j] = temp[j] +" "+ arr[i];
                   i++;
               }
                i--;
                j++;
            }
            else{
                temp[j]=arr[i];
                j++;
            }
        }

        for(int i=0; i<temp.length;i++)
            if(temp[i]==null)
                size++;
        String [] arr2= new  String[temp.length-size];

        for(int i=0; i<arr2.length;i++)
            arr2[i]=temp[i];

        temp=arr2;

        return temp;

    }

}
