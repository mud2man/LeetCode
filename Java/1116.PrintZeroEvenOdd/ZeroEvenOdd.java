/* Wait and notify:
 * 1. Use step as signal: call zero when step = 0 or 2, call even when step = 3, and call odd when step = 1
 */

import java.util.*;

interface IntConsumer{
    public void accept(int num);
}

public class ZeroEvenOdd {
    private int n;
    private int step;
    public ZeroEvenOdd(int n) {
        this.n = n;
        this.step = 0;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while(this.step < 2 * n){
           synchronized(this){
                while(this.step % 4 != 0 && this.step % 4 != 2){
                    wait();
                }
                if(this.step < 2 * n){
                    printNumber.accept(0);
                }
                this.step++;
                notifyAll();
            } 
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while(this.step < 2 * n){
            synchronized(this){
                while(this.step % 4 != 3){
                    wait();
                }
                if(this.step < 2 * n){
                    printNumber.accept((this.step + 1 ) / 2);
                }
                this.step++;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while(this.step < 2 * n){
            synchronized(this){
                while(this.step % 4 != 1){
                    wait();
                }
                if(this.step < 2 * n){
                    printNumber.accept((this.step + 1 ) / 2);
                }
                this.step++;
                notifyAll();
            }
        }
    }
}
