package HW6;

import java.io.*;
import java.util.*;

public class NLP
{
    public Word_Map wmap = new Word_Map();
    private float totalNumberOfDocument;

    /**
     *
     * @param dir
     * @throws FileNotFoundException
     */
    public void readDataset(String dir) throws FileNotFoundException {

        List<String> results = new ArrayList<String>();

        File[] files = new File(dir).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                results.add(dir + "\\" +file.getName());
            }
        }

        totalNumberOfDocument = results.size();

        for(int i = 0;  i < results.size();  i++) {

            int index = 0;

            File file = new File(results.get(i));
            Scanner allFile = new Scanner(file);

            while (allFile.hasNext()) {

                String word = allFile.next().trim().replaceAll("\\p{Punct}", "");


                if(word.equals(""))
                    continue;

                if(wmap.containsKey(word)) {

                    if(((File_Map) wmap.get(word)).containsKey(results.get(i))){
                        ((List<Integer>)((File_Map) wmap.get(word)).get(results.get(i))).add(index);

                    }else{

                        List<Integer> tempList = new ArrayList<>();

                        tempList.add(index);

                        ((File_Map) wmap.get(word)).put(results.get(i),tempList);

                    }

                }else{
                    File_Map file_map = new File_Map();

                    List<Integer> tempList = new ArrayList<>();

                    tempList.add(index);

                    file_map.put(results.get(i),tempList);

                    wmap.put(word, file_map);
                }

                index++;

            }
            allFile.close();
        }

    }

    /**
     *
     * @throws IOException
     */
    public void getcommands() throws IOException {


        String str;

        File dosya = new File("input.txt");

        BufferedReader read = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(dosya), "UTF8"));

        while ((str = read.readLine()) != null) {

            String tmp [] = str.split(" ");

            if(tmp.length == 2){

                printBigrams(bigrams(tmp[1]),tmp[1]);

            }else if(tmp.length == 3) {
                float TFIDFValue = tfIDF(tmp[1],tmp[2]);

                if(TFIDFValue != -1)
                    System.out.printf("%." + "6f\n", TFIDFValue);


            }

            System.out.println();
        }

        read.close();

    }

    /**
     *
     * @param word
     * @return
     */
    public List<String> bigrams(String word){

        List<String> listOfBigram = new ArrayList<>();


        if(wmap.containsKey(word)){
            for(int i = 0;  i < ((File_Map)wmap.get(word)).size();  i++){

                List<Integer> list = (List<Integer>)((File_Map) wmap.get(word)).get(((File_Map)wmap.get(word)).fnames.get(i));

                int size = list.size();

                Word_Map.Node node = wmap.getNode();

                while(node != null){

                    if(node.getfMap().containsKey(((File_Map)wmap.get(word)).fnames.get(i))){
                        List<Integer> list2 = (List<Integer>) node.getfMap().get(((File_Map)wmap.get(word)).fnames.get(i));
                        for (int k = 0;  k < list.size();  k++ ){

                            if (list2.contains(list.get(k) + 1)){
                                size--;

                                if(!listOfBigram.contains(node.getWord()))
                                    listOfBigram.add(node.getWord());

                            }
                            if(size == 0)
                                break;

                        }
                    }
                    if(size == 0)
                        break;

                    node = node.next;
                }

            }

        }else
            System.out.println("There is no bigram of " + word + ".");

        return listOfBigram;
    }

    /**
     *
     * @param word
     * @param fileName
     * @return
     */
    public float tfIDF(String word, String fileName)
    {
        float fileTermNumber = 0;
        float numberOfTerm;

        if(wmap.containsKey(word)){

            if(((File_Map) wmap.get(word)).containsKey(fileName)){

                numberOfTerm = ((List<Integer>)((File_Map) wmap.get(word)).get(fileName)).size();
                Word_Map.Node node = wmap.getNode();

                while(node != null){
                    if(node.getfMap().containsKey(fileName)){
                        fileTermNumber +=((List<Integer>) node.getfMap().get(fileName)).size();

                    }

                    node = node.next;
                }

            }else {
                System.out.println("There is no " + fileName + " in dataset." );
                return -1;
            }

        }else{
            System.out.println("There is no " + word + " in " + fileName  );
            return -1;
        }


        float TF = numberOfTerm / fileTermNumber;

        float IDF = (float) Math.log( totalNumberOfDocument / (float) ( (File_Map) wmap.get(word)).size() );


        return TF * IDF;
    }

    public  void printWordMap() {

        Iterator iter = wmap.iterator();

        while (iter.hasNext()){

            Word_Map.Node node = (Word_Map.Node) iter.next();

            for(int i = 0;  i < node.getfMap().size();  i++ )
                System.out.println("Word\t:" + node.getWord() + "\t\t" +"File\t:" + node.getfMap().fnames.get(i) + "\t\t" + "Index of word\t:" + node.getfMap().occurances.get(i));

            System.out.println();

        }
    }

    /**
     *
     * @param list
     * @param word
     */
    private void printBigrams(List<String> list, String word){

        System.out.print("[");

        for (int i = 0;  i < list.size(); i++ ) {
            System.out.print(word + " " + list.get(i));

            if(i != list.size()-1)
                System.out.print(", ");
        }

        System.out.println("]");
    }

}
