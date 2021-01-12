/* Lock:
 * 1. Only when philosopher get left and right forks {philosopher, (philosopher + 1) % 5}, the can perform all the operations
 * 2. Release forks {philosopher, (philosopher + 1) % 5} after operations are finished
 * 3. Resource ordering is not working, because it's a dead lock if all philosopher grap their right fork
 */

import java.util.*;

public class DiningPhilosophers {
    boolean[] isForkUsed; 
    public DiningPhilosophers() {
        isForkUsed = new boolean[5];
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int rightFork = philosopher;
        int leftFork = (philosopher + 1) % 5;
        synchronized(this) {
            while(isForkUsed[rightFork] || isForkUsed[leftFork]){
                Thread.sleep(1);
            }
            isForkUsed[rightFork] = true;
            isForkUsed[leftFork] = true;
        }
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        isForkUsed[rightFork] = false;
        isForkUsed[leftFork] = false;
    }
}
