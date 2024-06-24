class Process extends Thread {
    private final int id;
    private final TreeLock treeLock;

    // Constructor
    // parameters: id of the process and the tree lock
    // treeLock: the tree lock that the process uses to enter the critical section
    public Process(int id, TreeLock treeLock) {
        this.id = id;
        this.treeLock = treeLock;
    }

    @Override
    public void run() {
        System.out.println("Process " + id + " starts enter section.");
        treeLock.lock(id);

        // Critical section
        System.out.println("Process " + id + " is in the critical section.");
        // Simulate some work
        try {
           Thread.sleep((int)Math.random() * 100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Process " + id + " leaves the critical section.");

        treeLock.unlock(id);
        
    }
}
