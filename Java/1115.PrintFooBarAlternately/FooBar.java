/* Wait and notify:
 * 1. Use step as signal: call foo when step = 0, call bar when step = 1
 */

import java.util.*;

public class FooBar {
    private int n;
    int step;

    public FooBar(int n) {
        this.n = n;
        this.step = 0;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this){
                while(step != 0){
                    wait();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                step = 1;
                notifyAll();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized (this){
                while(step != 1){
                    wait();
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                step = 0;
                notifyAll();
            }
        }
    }
}
