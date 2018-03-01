/* Binary search: O(log(n)*n), where n = number of intervals
 * 1. Have a array "indexdIntervals" to store the interval and its index
 * 2. Sort indexdIntervals" by the "start" of interval
 * 3. Iterate "indexdIntervals" and use binary search to find its minimum right index with left = i, and right = length - 1
 */

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution{
    private class IndexdInterval{
        Interval interval;
        int index;
        IndexdInterval(Interval itrl, int idx){interval = itrl; index = idx;}
    }
    
    private class IntervalComparator implements Comparator<IndexdInterval>{
        public int compare(IndexdInterval x, IndexdInterval y){
            return x.interval.start - y.interval.start;
        }     
    }
    
    private int binarySearch(int target, int left, int right, IndexdInterval[] indexdIntervals){
        while(left <= right){
            int mid = (left + right) / 2;
            if(indexdIntervals[mid].interval.start < target){
                left = mid + 1;
            }
            else{
                right = mid - 1;
            }
        }
        
        return (left < indexdIntervals.length && indexdIntervals[left].interval.start >= target)? indexdIntervals[left].index: -1;
    }
    
    public int[] findRightInterval(Interval[] intervals) {
        int length = intervals.length;
        IndexdInterval[] indexdIntervals = new IndexdInterval[length];
        for(int i = 0; i < length; ++i){
            indexdIntervals[i] = new IndexdInterval(intervals[i], i);
        }
        
        Arrays.sort(indexdIntervals, new IntervalComparator());
        
        int[] rightIndex = new int[length];
        for(int i = 0; i < length; ++i){
            int currentIndex = indexdIntervals[i].index;
            int minimumRightIndex = binarySearch(indexdIntervals[i].interval.end, i + 1, length - 1, indexdIntervals);
            rightIndex[currentIndex] = minimumRightIndex;
        }
        return rightIndex;
    }

    public static void main(String[] args){
        Solution sol;
        Interval[] intervals = new Interval[3];
        intervals[0] = new Interval(3, 4);
        intervals[1] = new Interval(2, 3);
        intervals[2] = new Interval(1, 2);
        sol = new Solution();
        
        System.out.println("intervals: ");
        for(int i = 0; i < intervals.length; ++i){
            System.out.println("(" + intervals[i].start + ", " + intervals[i].end + ")");
        }
        
        System.out.println("minimum right indexs:" + Arrays.toString(sol.findRightInterval(intervals))); 
    }
}
