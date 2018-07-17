/* Binary search: Time:O((m+n)log(m*n)), Space:O(1)
 * 1. Binary search the number x s.t. the count of numbers(<= x) is the least number larger than k
 * 2. Find the closest of x, whcich is less than or equal to x
 */

import java.util.*;

public class Solution{
    private int getLessEqualCount(int row, int col, int val){
        int count = 0;
        int y = row;
        for(int x = 1; x <= col; x++){
            while((x * y) > val){
                y--;
            }
            if(y > 0){
                count += y;
            }
            else{
                break;
            }
        }
        return count;
    }
    
    private int getClosetNumber(int row, int col, int val){
        int y = row;
        int max = 1;
        for(int x = 1; x <= col && y > 0; x++){
            while((x * y) > val){
                y--;
            }
            if(y > 0){
                max = Math.max(max, x * y);
            }
            else{
                break;
            }
        }
        return max;
    }
    
    public int findKthNumber(int m, int n, int k) {
        int lb = 1;
        int hb = m*n;
        
        while(lb <= hb){
            int mid = (lb + hb) / 2;
            int count = getLessEqualCount(m, n, mid);
            if(count < k){
                lb = mid + 1;
            }
            else{
                hb = mid - 1;
            }
        }
        
        return getClosetNumber(m, n, lb);
    }
 
    public static void main(String[] args){
        int m = 3;
        int n = 3;
        int k = 5;
        Solution sol = new Solution();

        System.out.println("m:" + m + ", n:" + n + ", k:" + k);
        System.out.println(k + "-th value: " + sol.findKthNumber(m, n, k));
    }
}
