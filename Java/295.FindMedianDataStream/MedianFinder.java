/* Heap: O(logn)
 * 1. Have a maxHeap to store the smaller half
 * 2. Have a minHeap to store the bigger half
 * 3. When insert, if num <= maxHeapTop, put num into minHeap, and do balance between two heaps
 * 4. Otherwise, put num into minHeap, and do balance between two heaps
 * 5. When get median, find if maxHeap.size() == minHeap.size(), return (maxHeapTop + minHeapTop) / 2
 * 6. Otherwise, return the top of heap, which has the bigger size
 */

import java.util.*;

public class MedianFinder{
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    static private class HeapComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer x, Integer y){
            return y - x;
        }
    }
        
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>(new HeapComparator());
        minHeap = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        int maxHeapTop = (!maxHeap.isEmpty())? maxHeap.peek(): Integer.MAX_VALUE;
        int minHeapTop;
        
        if(num <= maxHeapTop){
            maxHeap.add(num);
            if(maxHeap.size() > 1 && (maxHeap.size() - 2) >= minHeap.size()){
                maxHeapTop = maxHeap.poll();
                minHeap.add(maxHeapTop);
            }
        }
        else{
            minHeap.add(num);
            if(minHeap.size() > 1 && (minHeap.size() - 2) >= maxHeap.size()){
                minHeapTop = minHeap.poll();
                maxHeap.add(minHeapTop);
            }
        }
    }
    
    public double findMedian() {
        if(maxHeap.size() > minHeap.size()){
            return (double)maxHeap.peek();
        }
        else if(maxHeap.size() < minHeap.size()){
            return (double)minHeap.peek();
        }
        else{
            double maxHeapTop = (double)maxHeap.peek();
            double minHeapTop = (double)minHeap.peek();
            return (maxHeapTop + minHeapTop) / 2.0;
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
