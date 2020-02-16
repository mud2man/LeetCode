/* MinHeap: Time:O(c), Space:O(c), where c is 300 at worst case
 * 1. Use queue "times" to store the hit events(time stamp and count)
 * 2. Use current count to record the count on the current time
 * 3. Hitcount = the sum of all the hic count in minHeap and the hit count of current count
 */

import java.util.*;
import java.util.concurrent.locks.*;

public class HitCounter {
    int count;
    Deque<int[]> times;
    
    /** Initialize your data structure here. */
    public HitCounter() {
        count = 0;   
        times = new LinkedList<>();
    }
    
    private void reduce(int timestamp){
        while(!times.isEmpty() && timestamp - times.peekFirst()[0] >= 300){
            int frontTime = times.peekFirst()[0];
            int frontCount = times.pollFirst()[1];
            count -= frontCount;
        }
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        synchronized(this){
            reduce(timestamp);
            count++;
            if(times.isEmpty() || times.peekLast()[0] != timestamp){
                times.add(new int[]{timestamp, 0});
            }
            times.peekLast()[1]++;
        }
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        synchronized(this){
            reduce(timestamp);
            return count;
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
