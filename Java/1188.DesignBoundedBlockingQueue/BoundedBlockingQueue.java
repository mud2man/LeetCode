/* Wait and notify:
 * 1. For enqueue, wait until queue has space. Then notifyAll after enqueue
 * 2. For dequeue, wait until queue has something. Then notifyAll after dequeue
 */

import java.util.*;

public class BoundedBlockingQueue {
    Deque<Integer> queue;
    int capacity;
    public BoundedBlockingQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        synchronized(this){
            while(queue.size() == capacity){
                wait();
            }
            queue.add(element);
            notifyAll();
        }
    }
    
    public int dequeue() throws InterruptedException {
        int ret = 0;
        synchronized(this){
            while(queue.size() == 0){
                wait();
            }
            ret = queue.pollFirst();
            notifyAll();
        }
        return ret;
    }
    
    public int size() {
        synchronized(this){
            return queue.size();
        }
    }
}
