/* Wait and notify:
 * 1. For enqueue, wait until queue has space. Then notifyAll after enqueue
 * 2. For dequeue, wait until queue has something. Then notifyAll after dequeue
 */

import java.util.*;

public class H2O {
    int[] HydrogenOxygen;
    public H2O() {
        HydrogenOxygen = new int[2];
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (this){
            if(HydrogenOxygen[0] == 2 && HydrogenOxygen[1] == 1){
                Arrays.fill(HydrogenOxygen, 0);
                notifyAll();
            }
            while(HydrogenOxygen[0] == 2){
                wait();
            }
            HydrogenOxygen[0]++;
        }
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (this){
            if(HydrogenOxygen[0] == 2 && HydrogenOxygen[1] == 1){
                Arrays.fill(HydrogenOxygen, 0);
                notifyAll();
            }
            while(HydrogenOxygen[1] == 1){
                wait();
            }
            HydrogenOxygen[1]++;
        }
        // releaseOxygen.run() outputs "H". Do not change or remove this line.
        releaseOxygen.run();
    }
}
