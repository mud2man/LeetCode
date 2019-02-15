/* Heap: O(logn)
 * 1. Have a maxHeap to store the smaller half
 * 2. Have a minHeap to store the bigger half
 * 3. When insert, if maxHeap.size() <= minHeap.size(), take top of minHeap, and put small to maxHeap, big to minheap
 * 4. Otherwise, take top of maxHeap, and put small to maxHeap, big to minheap
 * 5. When get median, find if maxHeap.size() == minHeap.size(), return (maxHeapTop + minHeapTop) / 2
 * 6. Otherwise, return the top of heap, which has the bigger size
 *
 * For follow up, use bucket, where bucket[i] is the count of number i. We can iterate from i = 0 to 100 to accumulatet the count. It's O(1) solution
 */

import java.util.*;

public class MedianFinder{
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    static private class HeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer x, Integer y){
            return (x > y)? -1: (x < y)? 1 : 0;
        }
    }
        
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>(new HeapComparator());
        maxHeap.add(Integer.MIN_VALUE);
        minHeap = new PriorityQueue<Integer>();
        minHeap.add(Integer.MAX_VALUE);
    }
    
    public void addNum(int num) {
        if(maxHeap.size() <= minHeap.size()){
            int small = Math.min(num, minHeap.peek());
            int big = Math.max(num, minHeap.poll());
            maxHeap.add(small);
            minHeap.add(big);
        }
        else if(maxHeap.size() > minHeap.size()){
            int small = Math.min(num, maxHeap.peek());
            int big = Math.max(num, maxHeap.poll());
            maxHeap.add(small);
            minHeap.add(big);
        }
    }
    
    public double findMedian() {
        if(maxHeap.size() == minHeap.size()){
            return ((double)maxHeap.peek() + (double)minHeap.peek()) / 2.0;
        }
        else if(maxHeap.size() > minHeap.size()){
            return (double)maxHeap.peek();
        }
        else{
            return (double)minHeap.peek();
        }
    }
 
    public static void main(String[] args){
        MedianFinder sol = new MedianFinder() ;
        int num;
        
        num = 1;
        sol.addNum(num);
        System.out.println("add(" + num +")");

        num = 2;
        sol.addNum(num);
        System.out.println("add(" + num +")");
        
        System.out.println("findMedian() -> " + sol.findMedian());
        
        num = 3;
        sol.addNum(num);
        System.out.println("add(" + num +")");
        
        System.out.println("findMedian() -> " + sol.findMedian());
    }
}
