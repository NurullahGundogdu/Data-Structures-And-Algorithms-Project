package HW5;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {

    private String path;

    PriorityQueues firstVector;
    PriorityQueues secondVector;
    PriorityQueues thirdVector;

    private Semaphore semCon = new Semaphore(0);
    private Semaphore semProd = new Semaphore(1000);

    /**
     *
     * @param path
     */
    public Producer(String path){
        this.path = path;

    }

    /**
     *
     */
    @Override
    public void run(){



        File f = new File(path);
        BufferedImage img = null;
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int image[][] = new int[img.getWidth()][img.getHeight()];

        for(int x=0; x<img.getWidth(); x++){
            for(int y=0; y<img.getHeight(); y++) {
                image[x][y] = img.getRGB(x, y);
            }
        }

        LEXComparator lex = new LEXComparator();
        EUCComparator euc = new EUCComparator();
        BMXComparator bmx = new BMXComparator();

        firstVector = new PriorityQueues(lex, (img.getHeight() * img.getWidth()));
        secondVector = new PriorityQueues(euc, (img.getHeight() * img.getWidth()));
        thirdVector = new PriorityQueues(bmx, (img.getHeight() * img.getWidth()));


        Thread t1 = null;
        Thread t2 = null;
        Thread t3 = null;

        int num = 0;


        for(int x=0; x<img.getWidth(); x++){
            for(int y=0; y<img.getHeight(); y++) {

                synchronized (firstVector) {
                    produce(image[x][y], 1);
                }
                synchronized (secondVector) {
                    produce(image[x][y], 2);
                }
                synchronized (thirdVector) {
                    produce(image[x][y], 3);
                }

                num++;
                if(num == 100){
                    Consumer first = new Consumer(firstVector,2, semCon, semProd);
                    Consumer second = new Consumer(secondVector, 3, semCon, semProd);
                    Consumer third = new Consumer(thirdVector, 4, semCon, semProd);

                    t1 = new Thread(first,"Consumer");
                    t2 = new Thread(second,"Consumer");
                    t3 = new Thread(third,"Consumer");

                    t1.start();
                    t2.start();
                    t3.start();

                }

            }
        }


        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void produce(int value, int vectorNum)
    {
        try {
            semProd.acquire();
        }
        catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }

        if(vectorNum == 1)
            firstVector.offer(value);
        else if (vectorNum == 2)
            secondVector.offer(value);
        else if(vectorNum == 3)
            thirdVector.offer(value);

        System.out.println("Thread 1: [" + ((value >> 16) & 0xff) + ", " + ((value >> 8) & 0xff) + ", " + (value & 0xff) + "]");

        semCon.release();
    }



}
