/* Time:O(n^2), Space:O(n)
 * 1. Use the equation (Math.max(start1, start2) < Math.min(end1, end2)) to determine if the two section overlap
 * 2. Have two lists, books is to track books, and overlaps is to track overlap
 * 3. If there is no two overlaps overlap each other, then the new overlap is valid 
 */

import java.util.*;

public class Solution{
    List<int[]> books;
    List<int[]> overlaps;
    
    public Solution() {
        books = new ArrayList<int[]>();
        overlaps = new ArrayList<int[]>();
    }
    
    public boolean book(int start, int end) {
        List<int[]> newOverlaps = new ArrayList<int[]>();
        for(int[] book: books){
            int overlapStart = Math.max(book[0], start);
            int overlapEnd = Math.min(book[1], end);
            // new overlap exists
            if(overlapStart < overlapEnd){
                int[] newOverlap = new int[]{overlapStart, overlapEnd};
                newOverlaps.add(newOverlap);
                for(int[] overlap: overlaps){
                    int doubleOverlapStart = Math.max(overlap[0], newOverlap[0]);
                    int doubleOverlapEnd = Math.min(overlap[1], newOverlap[1]);
                    // double overlap exists
                    if(doubleOverlapStart < doubleOverlapEnd){
                        return false;
                    }
                }
            }
        }
        overlaps.addAll(newOverlaps);
        books.add(new int[]{start, end});
        return true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] intervals = {{10, 20}, {50, 60}, {10, 40}, {5, 15}, {5, 10}, {25, 55}};
        
        for(int[] interval: intervals){
            System.out.println(Arrays.toString(interval));
        	System.out.println("insert success: " + sol.book(interval[0], interval[1]));
        }
    }
}
