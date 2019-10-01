/* Rejection Sampling: Average time:O(1), worst time:O(infi), Space:O(1). LeetCode has a shorter solution
 * 1. We can decide between1and5 by get the rand from rand7(). between1and5 is true if rand < 4
 * 2. Then we call rand until it return the number between 1 and 5
 * 3. Then return rand or (rand + 5) according between1and5 
 */

import java.util.*;

public class Solution {
    private int rand7(){
        Random rand = new Random();
        return rand.nextInt(7) + 1;
    }

    public int rand10() {
        int rand = rand7();
        while(rand == 4){
            rand = rand7();
        }
        boolean between1and5 = (rand < 4)? true: false;
        rand = rand7();
        while(rand > 5){
            rand = rand7();
        }
        return between1and5? rand: rand + 5;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        for(int i = 0; i < 10; ++i){
            System.out.println("rand10: " + sol.rand10());
        }
    }
}
