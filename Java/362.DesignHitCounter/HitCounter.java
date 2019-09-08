/* MinHeap: O(s), where s is 300 at worst case here
 * 1. Use minimum heap to store the secondary latest hit event(time stamp and count)
 * 2. Use current count to record the count on the current time
 * 3. Hitcount = the sum of all the hic count in minHeap and the hit count of current count
 */

import java.util.*;
import java.util.concurrent.locks.*;

class Counter{
    int timeStamp;
    int number;
    Counter(int t, int n){timeStamp = t; number = n;}
}

public class HitCounter {
    private final LinkedList<int[]> queue;
    private int count;
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;
 
    /** Initialize your data structure here. */
    public HitCounter() {
        count = 0;
        queue = new LinkedList<int[]>();
        readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }
    
    private void updateQueue(int timestamp){        
        while(!queue.isEmpty()){
            if(queue.peekFirst()[0] <= (timestamp - 300)){
                count -= queue.peekFirst()[1];
                queue.pollFirst();
            }else{
                break;
            }
        }
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        writeLock.lock();
        try{
            if(queue.isEmpty()){
                queue.add(new int[]{timestamp, 1});
                count++;
            }else{
                int[] last = queue.peekLast();
                if(last[0] == timestamp){
                    last[1]++;
                }else{
                    queue.add(new int[]{timestamp, 1});
                }
                count++;
                updateQueue(timestamp);
            }
        }finally{
            writeLock.unlock();
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        writeLock.lock();
        try{
            updateQueue(timestamp);
        }finally{
            writeLock.unlock();
        }
        
        readLock.lock();
        try{
            return count;
        }finally{
            readLock.unlock();
        }
    }

    public static void main(String[] args){
        HitCounter obj = new HitCounter();
        int timeStamp = 1;
        
        obj.hit(timeStamp);
        System.out.println("hit @ time:" + timeStamp);
        
        timeStamp = 2;
        obj.hit(timeStamp);
        System.out.println("hit @ time:" + timeStamp);

        timeStamp = 3;
        obj.hit(timeStamp);
        System.out.println("hit @ time:" + timeStamp);
    
        timeStamp = 4;
        System.out.println("get hit:" + obj.getHits(timeStamp) + " @ time:" + timeStamp);

        timeStamp = 300;
        obj.hit(timeStamp);
        System.out.println("hit @ time:" + timeStamp);

        timeStamp = 300;
        System.out.println("get hit:" + obj.getHits(timeStamp) + " @ time:" + timeStamp);

        timeStamp = 301;
        System.out.println("get hit:" + obj.getHits(timeStamp) + " @ time:" + timeStamp);
	}
}
