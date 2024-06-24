public class TournamentTreeTest {
    public static final int NUM_PROCESSES = 4;

    public static void main(String[] args) {
        // Create a tournament tree of Peterson locks
        TreeLock tree = new TreeLock(NUM_PROCESSES);

        // Create and start n processes
        for (int i = 0; i < NUM_PROCESSES; i++) {
            new Process(i, tree).start();
        }
    }
}
