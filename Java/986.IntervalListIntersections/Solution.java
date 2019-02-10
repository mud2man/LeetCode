/* Merget sort: Time:O(n + m), Space:O(n + m).
 * 1. Have two pointers ptr0 for A, ptr1 for B, and do operation like merge sort to find the intersections
 */

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}

public class Solution{
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> intersections = new ArrayList<>();
        int ptr0 = 0;
        int ptr1 = 0;
        while(ptr0 < A.length && ptr1 < B.length){
            Interval a = A[ptr0];
            Interval b = B[ptr1];
            //check if overlap between a and b
            if(!(a.end < b.start || b.end < a.start)){
                intersections.add(new Interval(Math.max(a.start, b.start), Math.min(a.end, b.end)));
            }
            
            if(a.end >= b.end){
                ptr1++;    
            }
            else{
                ptr0++;
            }
        }
        
        Interval[] ret = new Interval[intersections.size()]; 
        for(int i = 0; i < intersections.size(); ++i){
            ret[i] = intersections.get(i);
        }
        return ret;
    }
   
    public static void main(String[] args){
        Solution sol = new Solution();
        Interval[] A = {new Interval(0, 2), new Interval(5, 10),new Interval(13, 23), new Interval(24, 25)};
        Interval[] B = {new Interval(1, 5), new Interval(8, 12),new Interval(15, 24), new Interval(25, 26)};
        
        System.out.print("A: ");
        for(Interval i: A){
            System.out.print("(" + i.start + ", " + i.end + "), ");
        }
        System.out.println("");
        
        System.out.print("B: ");
        for(Interval i: B){
            System.out.print("(" + i.start + ", " + i.end + "), ");
        }
        System.out.println("");
        
        Interval[] C = sol.intervalIntersection(A, B);
        System.out.print("C: ");
        for(Interval i: C){
            System.out.print("(" + i.start + ", " + i.end + "), ");
        }
        System.out.println("");
    }
}
