/* MinHeap: O(s), where s is 300 at worst case here
 * 1. Use minimum heap to store the secondary latest hit event(time stamp and count)
 * 2. Use current count to record the count on the current time
 * 3. Hitcount = the sum of all the hic count in minHeap and the hit count of current count
 */

import java.util.*;

class Counter{
    int timeStamp;
    int number;
    Counter(int t, int n){timeStamp = t; number = n;}
}

public class HitCounter {
    private int count;
    private Counter currCount;
    private PriorityQueue<Counter> minHeap;
    
    private class HitComparator implements Comparator<Counter>{
        @Override
        public int compare(Counter o1, Counter o2){
            return o1.timeStamp - o2.timeStamp;
        }
    }

    /** Initialize your data structure here. */
    public HitCounter() {
        minHeap = new PriorityQueue<Counter>(new HitComparator());
        count = 0;
        currCount = null;
    }
    
    /** Record a hit.
        @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        count = getHits(timestamp);
        
        if(currCount == null){
            currCount = new Counter(timestamp, 1);   
        }
        else if(currCount.timeStamp == timestamp){
            currCount.number++;
        }
        else{
            minHeap.add(currCount);
            currCount = new Counter(timestamp, 1); 
        }
        
        count++;
    }
    
    /** Return the number of hits in the past 5 minutes.
        @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        if((currCount != null) && (currCount.timeStamp <= (timestamp - 300))){
            count = count - currCount.number;
            currCount = null;
        }
        
        while((!minHeap.isEmpty()) && (minHeap.peek().timeStamp <= (timestamp - 300))){
            count = count - minHeap.poll().number;
        }
        
        return count;
    }

    public static void main(String[] args){
        int timeStamp;
        HitCounter obj = new HitCounter();

        timeStamp = 1;
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
