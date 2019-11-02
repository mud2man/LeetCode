/* Heap: O(logn)
 * 1. Have a maxHeap to store the smaller half
 * 2. Have a minHeap to store the bigger half
 * 3. When insert, if maxHeap.size() == minHeap.size(), put the num to maxHeap if num <= maxHeap.peek()
 * 4. If maxHeap.size() > minHeap.size(), add num to maxHeap and poll the top and add it to minHeap, which make two heap have the same size
 * 5. Otherwise, vice versa
 *
 * For follow up, use bucket, where bucket[i] is the count of number i. We can iterate from i = 0 to 100 to accumulatet the count. It's O(1) solution
 */

import java.util.*;

public class MedianFinder{
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        minHeap = new PriorityQueue<>(); 
    }
    
    public void addNum(int num) {
        if(maxHeap.isEmpty()){
            maxHeap.add(num);
        }else if(maxHeap.size() == minHeap.size()){
            if(num <= maxHeap.peek()){
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }
        }else if(maxHeap.size() > minHeap.size()){
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }else{
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }
    
    public double findMedian() {
        if((maxHeap.size() + minHeap.size()) % 2 == 0){
            return (double)(maxHeap.peek() + minHeap.peek()) / 2.0;
        }else{
            if(maxHeap.size() > minHeap.size()){
                return (double)(maxHeap.peek());
            }else{
                return (double)(minHeap.peek());
            }
        }
    }
 
    public static void main(String[] args){
        MedianFinder sol = new MedianFinder();
        int num = 1;
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
