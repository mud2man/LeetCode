/* Math: Time:O(1), Space:O(1)
 * 1. Find the count s.t. (1 + 2 + 3 + ... count) >= target
 * 2. if (goal - longTarget) % 2 == 0, we can find a number between 1 and count (diff / 2), s.t. the sum = target
 * 3. Otherwise, if (count + 1) % 2 == 1, we can add count + 1 and subtract (diff / 2) to reach target, so steps number is count + 1
 * 4. Otherwise, we need to add count + 1, subtract count + 2, and subtract (diff /2) to reach target, so steps number is count + 2
 */

import java.util.*; // Stack

public class Solution {
    public int reachNumber(int target) {
        long longTarget = (long)Math.abs(target);
        long count = (-1 + (long)Math.sqrt(8*longTarget + 1)) / 2;
        long goal = count * (count + 1) / 2;
        count = (goal < longTarget)? (count + 1): count;
        goal = count * (count + 1) / 2;

        if((goal - longTarget) % 2 == 0){
            return (int)count;
        }
        else{
            return ((count + 1) % 2 == 0)? (int)(count + 2): (int)(count + 1);
        }
    }

    public static void main(String[] args){
        Solution sol;
        int target = 2;

        sol = new Solution();
        System.out.println("target: " + target);
        System.out.println("minimum steps: " + sol.reachNumber(target));
    }
}
