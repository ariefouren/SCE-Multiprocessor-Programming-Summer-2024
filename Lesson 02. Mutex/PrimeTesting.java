// File: PrimeTesting.java
// This program creates 10 threads, each of which accesses 
// a shared counter and tests whether the counter value is prime.

import java.util.concurrent.locks.ReentrantLock; 

public class PrimeTesting {
    static final int MAX_NUMBER_TO_TEST = 100;
    static final Counter counter = new Counter(0  );
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(new PrimeTestingTask()).start();
        }
    }

    // Shared counter used by all threads to get the next number to test
    // Note the use of synchronized block to protect the shared counter
    static class Counter {
        private int count = 0;
        ReentrantLock lock = new ReentrantLock();
        public Counter(int count) {
            this.count = count;
        }

        public int getAndIncrement() {
            // lock the object
            // without this lock, the threads will interfere with each other
            // try commenting out the synchronized block and see what happens
            synchronized(lock) 
            {
                int temp = count;

                // simulate a delay
                try {
                    Thread.sleep(10); // sleep for 10 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                count = temp + 1;
                return temp;
            }
        }
    }
    // end of Counter class

    // inner class that represents a task that tests for prime numbers
    // defined as a static class, so can be instantiated without an instance of the outer class
    static class PrimeTestingTask implements Runnable {
        int threadId = 0;
        static int nextId = 0;
        PrimeTestingTask() {
            threadId = nextId++;
        }

    @Override
    public void run() {
        int val = counter.getAndIncrement();
        while (val < MAX_NUMBER_TO_TEST) {
                
            if (isPrime(val)) {
                    System.out.println("#" + threadId + ": Prime number: " + val);
                }
                val = counter.getAndIncrement(); // get next value
            }
        }

        private boolean isPrime(int number) {
            if (number <= 1) {
                return false;
            }
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }
    // end of PrimeTestingTask class
    
}
