package HW4;

public class Main{

    public static void main(String[] args) {

       test1();
       test2();
       test3();

    }

    static void test1(){

        System.out.println("__________________TEST1__________________");
        System.out.println();
        Integer [] row1 = {1, 2, 3, 4};
        Integer [] row2 = {5, 6, 7, 8};
        Integer [] row3 = {9, 10, 11, 12};
        Integer [] row4 = {13, 14, 15, 16};
        Integer [] row5 = {17, 18, 19, 20};
        Integer[][] dataset = {row1, row2, row3, row4,row5};

        arrayIterator<Integer> provider = new arrayIterator<>(dataset);

        while (provider.hasNext()) {
            int data = provider.next();
            System.out.print(data+" ");
        }

        System.out.println();
        System.out.println();
    }

    static void test2(){

        System.out.println("__________________TEST2__________________");
        System.out.println();

        Integer [] row1 = {1, 2, 3, 4};
        Integer [] row2 = {5, 6, 7, 8};
        Integer [] row3 = {9, 10, 11, 12};
        Integer [] row4 = {13, 14, 15, 16};

        Integer[][] dataset = {row1, row2, row3, row4};

        arrayIterator<Integer> provider = new arrayIterator<>(dataset);

        while (provider.hasNext()) {
            int data = provider.next();
            System.out.print(data+" ");
        }

        System.out.println();
        System.out.println();
    }

    static void test3(){

        System.out.println("__________________TEST3__________________");
        System.out.println();

        Integer [] row1 = {1, 2, 3, 4, 5};
        Integer [] row2 = {6, 7, 8, 9, 10};
        Integer [] row3 = {11, 12, 13, 14, 15};
        Integer [] row4 = {16, 17, 18, 19, 20};

        Integer[][] dataset = {row1, row2, row3, row4};

        arrayIterator<Integer> provider = new arrayIterator<>(dataset);

        while (provider.hasNext()) {
            int data = provider.next();
            System.out.print(data+" ");
        }
        System.out.println();
        System.out.println();

    }

}
