// File: TestGo.java
// illustrates the use of threads in Java
// This program creates 8 threads, each of which counts down from 10 to 0
// and prints a message. 
// The main program starts the threads, waits for them to finish, and then
// prints a message when all threads are done.

class Go implements Runnable {
  protected int countDown = 10;
  private static int threadCount = 0;
  private int threadNumber;

  public Go() {
    threadNumber = threadCount++;
  } ;

  public void run() {
    while(countDown-- > 0) {
      System.out.print("#" + threadNumber + "(" + 
      (countDown > 0 ? countDown : "Go !") + ")  ");
      Thread.yield(); // force switch to another thread
    }

  }
}

public class TestGo {
  
  public TestGo() {}
  
  public static void main(String[] args) throws InterruptedException {
    Thread[] thread = new Thread[8];
    // create threads
    for (int i = 0; i < thread.length; i++) {
      thread[i] = new Thread(new Go());
    }

    // start threads
    for (int i = 0; i < thread.length; i++) {
      thread[i].start();
    }
    
    // wait for them to finish
    for (int i = 0; i < thread.length; i++) {
      thread[i].join();
    }
    
    System.out.println("\nall threads are done!");
  }
  
}
