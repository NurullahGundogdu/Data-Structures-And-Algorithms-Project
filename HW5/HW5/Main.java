package HW5;



public class Main{


    public static void main(String[] args) throws InterruptedException {

        String path = args[0];

        Producer produ = new Producer(path);

        Thread t1 = new Thread(produ,"Producer");
        t1.start();

        t1.join();


    }
}
