package com.shreya.singleton;

public class TestSingleton implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        // Singleton s1 = Singleton.getInstance();
        // System.out.println(s1.hashCode());
        
        long startTime = System.currentTimeMillis();
        Thread[] users = new Thread[10];
        
        for (int i = 0; i < 10; i++) {
            users[i] = new Thread(new TestSingleton());
        }

        for (int i = 0; i < 10; i++) {
            users[i].start();
        }

        for (int i = 0; i < 10; i++) {
            users[i].join();
        }

        System.out.println("Total time taken = " + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void run() {
        System.out.println((Singleton.getInstance()).hashCode());
    }
}

