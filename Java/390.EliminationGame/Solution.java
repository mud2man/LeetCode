/* Math: O(logn)
 * 1. Keep tracking the first element of the list "first" 
 * 2. If "first" is out of range (first <= n), then the previous first is the answer
 * 3. Also, we have a variable "interval" to track the steps between first and second, it will double every while loop
 * 4. In the even loop, just accumulate "first" by interval, and check if it out of range
 * 5. In the odd loop, update "first" based on if the the remaining list length is even or odd
 */

import java.util.*;

public class Solution {
    public int lastRemaining(int n) {
        int first = 1;
        int interval = 1;
        int loopCount = 0;
        int listLength = n;
        
        while(first <= n){
            int cutLength = (listLength + 1) / 2;
            if(loopCount % 2 == 0){
                if((first + interval) <= n){
                    first += interval;
                }
                else{
                    break;
                }
            }
            else{
                if(listLength == (cutLength * 2)){
                    first = first;
                }
                else{
                    if((first + interval) <= n){
                        first += interval;
                    }
                    else{
                        break;
                    }
                }
            }
            loopCount++;
            interval = interval * 2;
            listLength -= cutLength;
        }
        return first;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 9;

        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("lastRemaining: " + sol.lastRemaining(n));
    }
}
