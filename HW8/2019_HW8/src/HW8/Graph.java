package HW8;

import java.util.Scanner;

public class Graph {

    private int numV;
    private int NumberOfPeoplePopularByEvery;
    private int size;
    private int orderRelation;
    private int maxEdge = 0;

    private Edge edges[] = null;


    public class Edge{

        private int value;
        private int popularity;
        private Edge next;

        /**
         * constructur
         * @param value
         */
        public Edge(int value){
            popularity = 0;
            this.value = value;
            next = null;
        }

        /**
         * add next to vertex
         * @param value
         */
        private void addNextEdge(int value){
            Edge temp = this;

            while(temp.next != null)
                temp = temp.next;

            temp.next = new Edge(value);
        }

    }

    /**
     * constructur
     * @param numV
     */
    public Graph(int numV){
        this.numV = numV;
        this.size = 0;
        this.NumberOfPeoplePopularByEvery = 0;
        edges = new Edge[numV];
    }

    /**
     * constructur
     */
    public Graph(){
        this.numV = 0;
        this.size = 0;
        this.NumberOfPeoplePopularByEvery = 0;
    }

    /**
     * add vertex to graph
     * @param people
     * @return
     */
    public boolean add(int people){

        if(size < numV){
            Edge edge = new Edge(people);

            edges[getIndex(Integer.toString(people))] = edge;

            size++;

            return true;
        }

        return false;
    }

    /**
     * check the value if is in graph
     * @param value
     * @return boolen value
     */
    private boolean contains(int value){

        int index = getIndex(Integer.toString(value));

        if(edges[index] == null)
            return false;

        return true;
    }

    /**
     * find index of vertex
     * @param value
     * @return boolean
     */
    private int getIndex(String value){

        int index = value.hashCode() % numV;

        if(index < 0)
            index += numV;


        while ((edges[index] != null) && (edges[index].value != Integer.parseInt(value)) ){

            index++;
            if (index >= numV)
                index = 0;

        }

        return  index;
    }

    /**
     * read file and add vertex to graph
     * @param scan
     */
    public void loadEdgesFromFile(Scanner scan){

        numV = Integer.parseInt(scan.next());
        edges = new Edge[numV];

        orderRelation = Integer.parseInt(scan.next());

        while (scan.hasNext()) {

            int people1 = Integer.parseInt(scan.next());

            int people2 = Integer.parseInt(scan.next());

            if(people1 > maxEdge)
                maxEdge = people1;

            if (people2 > maxEdge)
                maxEdge = people2;

            if(!contains(people1)){
                this.add(people1);
            }

            edges[getIndex(Integer.toString(people1))].addNextEdge(people2);

            if(!contains(people2)) {
                this.add(people2);
            }
        }

    }

    /**
     * helper function for findLikeNumberOfEveryVertex
     * @param people
     * @param like
     */
    private void helperFunction(int people,boolean like[]){

        like[people] = true;

        edges[getIndex(Integer.toString(people))].popularity++;

        if(edges[getIndex(Integer.toString(people))].popularity == numV) {
            NumberOfPeoplePopularByEvery++;
        }

        Edge edge = edges[getIndex(Integer.toString(people))].next;

        while (edge != null)
        {
            int temp = edge.value;
            edge = edge.next;
            if (!like[temp])
                helperFunction(temp, like);
        }

    }

    /**
     * find every persons like
     */
    public void findLikeNumberOfEveryVertex(){

        for(int i = 0; i < numV; i++) {

            boolean like[] = new boolean[maxEdge + 1];

            helperFunction(edges[i].value, like);

        }

    }

    /**
     *
     * @return the number of people who are considered popular by every other person
     */
    public int PopularPeople(){

        return NumberOfPeoplePopularByEvery;
    }


}
