import java.util.Arrays;

public class WFSnapshotTest {
    public static void main(String[] args) {
    // creates a new WFSnapshot object with capacity 8 and initial value 0
    // creates 8 threads
    // each thread updates the value of the WFSnapshot object once (update method),
    // performs a scan operation on the WFSnapshot object (scan method)
    // and prints the snapshot it get from the scan operation

    WFSnapshot<Integer> wfSnapshot = new WFSnapshot<Integer>(8, 0);
    Thread[] threads = new Thread[8];
    for (int i = 0; i < 8; i++) {
      
      threads[i] = new Thread(() -> {
        wfSnapshot.update(1);
        Object[] snapshot = (Object[]) wfSnapshot.scan();
        // create a string representation of the snapshot
        String snapshotString = Arrays.toString(snapshot);
        // print the snapshot
        System.out.println("Thread " + 
        ThreadID.get() + " snapshot: " +snapshotString);
          
      });

      // start thread i
        threads[i].start();
    }
    // wait for all threads to finish
    for (int i = 0; i < 8; i++) {
      try {
        threads[i].join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    }
}
