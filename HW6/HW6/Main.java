package HW6;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {


        NLP nlp = new NLP();

        nlp.readDataset(args[0]);

       // nlp.printWordMap();

        nlp.getcommands();

    }
}
