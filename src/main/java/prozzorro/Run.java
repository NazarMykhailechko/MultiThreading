package prozzorro;

public class Run {

    public static void main(String[] args) throws InterruptedException {

        Thread main = Thread.currentThread();
        //main.start();

        int counter = 1000;
        long start = System.currentTimeMillis();

        for (int i = 0; i < counter; i++) {
            MyThread myThread = new MyThread("http://soccer365.ru/competitions/12/2017-2018/results/");
            myThread.start();
            myThread.join();
        }

        long end = System.currentTimeMillis();

        //main.join();
        System.out.println("Main thread finished!!!");
        System.out.println(((end - start)/1000) + " seconds last");
        //System.out.println(end - start);
    }
}