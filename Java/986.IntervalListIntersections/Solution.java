/* Merget sort: Time:O(n + m), Space:O(n + m).
 * 1. Have two pointers ptr0 for A, ptr1 for B, and do operation like merge sort to find the intersections
 */

import java.util.*;

public class Solution{
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> intersections = new ArrayList<>();
        int ptr0 = 0;
        int ptr1 = 0;
        while(ptr0 < A.length && ptr1 < B.length){
            int[] a = A[ptr0];
            int[] b = B[ptr1];
            //check if overlap between a and b
            if(!(a[1] < b[0] || b[1] < a[0])){
                intersections.add(new int[]{Math.max(a[0], b[0]), Math.min(a[1], b[1])});
            }
            
            if(a[1] >= b[1]){
                ptr1++;    
            }else{
                ptr0++;
            }
        }
        
        int[][] ret = new int[intersections.size()][2]; 
        for(int i = 0; i < intersections.size(); ++i){
            ret[i] = intersections.get(i);
        }
        return ret;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};      
        System.out.print("A: ");
        for(int[] a: A){
            System.out.print(Arrays.toString(a));
        }
        System.out.println("");
        
        for(int[] b: B){
            System.out.print(Arrays.toString(b));
        }
        System.out.println("");
        
        int[][] C = sol.intervalIntersection(A, B);
        System.out.print("C: ");
        for(int[] c: C){
            System.out.print(Arrays.toString(c));
        }
        System.out.println("");
    }
}
