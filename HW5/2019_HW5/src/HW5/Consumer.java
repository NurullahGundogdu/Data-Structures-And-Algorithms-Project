package HW5;

import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {

    private PriorityQueues PQ;
    private int threadNum;
    Semaphore semCon;
    Semaphore semProd;

    /**
     *
     * @param temp
     * @param threadNum
     * @param semCon
     * @param semProd
     */
    public Consumer(PriorityQueues temp, int threadNum, Semaphore semCon, Semaphore semProd){
            this.PQ = temp;
            this.threadNum = threadNum;
            this.semCon = semCon;
            this.semProd = semProd;
    }

    /**
     *
     */
    @Override
    public void run() {

        int element;
        while (true) {
            try {

                semCon.acquire();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
            if(PQ.Size() == 0)
                break;
            synchronized (PQ) {
                element = PQ.poll();
            }


            if (threadNum == 2)
                System.out.println("Thread 2-PQLEX: [" + ((element >> 16) & 0xff) + ", " + ((element >> 8) & 0xff) + ", " + (element & 0xff) + "]");
            else if (threadNum == 3)
                System.out.println("Thread 3-PQBMX: [" + ((element >> 16) & 0xff) + ", " + ((element >> 8) & 0xff) + ", " + (element & 0xff) + "]");
            else if (threadNum == 4)
                System.out.println("Thread 4-PQEUC: [" + ((element >> 16) & 0xff) + ", " + ((element >> 8) & 0xff) + ", " + (element & 0xff) + "]");

            semProd.release();
        }
    }
}

