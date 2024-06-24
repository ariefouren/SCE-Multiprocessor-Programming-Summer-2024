// Peterson's algorithm for 2-processes mutual exclusion

class PetersonMutex {
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;

    public void lock(int i) { // i is the id of the process, i = 0 or 1
        // implement the lock
    }

    public void unlock(int i) {
        // implement the unlock
    }
}
