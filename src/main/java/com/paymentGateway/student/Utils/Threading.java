package com.paymentGateway.student.Utils;

public class Threading {
    public static void main(String[] args) {
        // Creating two threads
        Thread thread1 = new NumberPrinter("Thread 1");
        Thread thread2 = new NumberPrinter("Thread 2");

        // Start the threads
        thread1.start();
        thread2.start();
    }
}

class NumberPrinter extends Thread {
    private final String threadName;

    public NumberPrinter(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(threadName + ": " + i);

            try {
                // Introduce a small delay to simulate some processing time
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
