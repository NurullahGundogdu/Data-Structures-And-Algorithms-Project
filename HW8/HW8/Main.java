package HW8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Graph graph = new Graph();

        File file = new File("input.txt");
        Scanner scan = new Scanner(file);

        graph.loadEdgesFromFile(scan);

        scan.close();

        graph.findLikeNumberOfEveryVertex();

        System.out.println(graph.PopularPeople());

    }



}
