/* Wait and notify:
 * 1. Use step as signal: call foo when step = 0, call bar when step = 1
 */

import java.util.*;

interface IntConsumer {
    public void accept(int num);
}

public class FizzBuzz {
    private int n;
    private int i;
    public FizzBuzz(int n) {
        this.n = n;
        this.i = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while(i <= n){
            synchronized(this){
                while((i % 3 != 0 || i % 15 == 0) && i <= n){
                    wait();
                }
                if(i <= n){
                    printFizz.run();
                }
                i++;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while(i <= n){
            synchronized(this){
                while((i % 5 != 0 || i % 15 == 0) && i <= n){
                    wait();
                }
                if(i <= n){
                    printBuzz.run();
                }
                i++;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while(i <= n){
            synchronized(this){
                while(i % 15 != 0 && i <= n){
                    wait();
                }
                if(i <= n){
                    printFizzBuzz.run();    
                }
                i++;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while(i <= n){
            synchronized(this){
                while((i % 3 == 0 || i % 5 == 0) && i <= n){
                    wait();
                }
                if(i <= n){
                    printNumber.accept(i);
                }
                i++;
                notifyAll();
            }
        }
    }

    public static void main(String[] args){
        System.out.println("No test case");
    }
}
