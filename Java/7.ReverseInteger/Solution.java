/* Time:O(n), Space:O(1)
 * 1. Use (y - tail) / 10 != prevY to check if y overflow
 */         

import java.util.*;

public class Solution {
    public int reverse(int x) {
        boolean isNegative = (x < 0)? true: false;
        x = (x < 0)? -x: x;
        int y = 0;
        while(x > 0){
            int tail = x % 10;
            int prevY = y;
            x /= 10;
            y = 10 * y + tail;
            if((y - tail) / 10 != prevY){
                return 0;
            }
        }
        return (isNegative)? -y: y;
    }
    
    public static void main(String[] args){
        Solution sol;
        int x = 123;
        sol = new Solution();
        System.out.println("x: " + x);
        System.out.println("reverse: " + sol.reverse(x));
    }
}
