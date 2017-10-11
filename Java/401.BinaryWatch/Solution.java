/* Backtrack: O(2^n)
 * 1. Have a array bits, bits[0:5] = minutes, bits[6:9] = hours
 * 2. Call backtracker recursively to get the next status, one is with the current bit = 1, the other is with the current bit = 0
 * 3. If remain = 0, retrieve the clock from "bits", and add it into clocks. Then return
 */

import java.util.*;

public class Solution {
    private void backtracker(int[] bits, int remain, List<String> clocks, int index){
        if(remain == 0){
            int minutes = 0;
            int weight = 1;
            for(int i = 0; i < 6; ++i){
                minutes += bits[i] * weight;
                weight = weight << 1;
            }
            
            if(minutes > 59){
                return;
            }
            
            int hours = 0;
            weight = 1;
            for(int i = 6; i < 10; ++i){
                hours += bits[i] * weight;
                weight = weight << 1;
            }
            
            if(hours > 11){
                return;
            }
            
            String hourString = Integer.toString(hours);
            String minuteString = Integer.toString(minutes);
            if(minutes < 10){
                minuteString = "0" + minuteString;
            }
            String clockString = hourString + ":" + minuteString;
            clocks.add(clockString);
            return;
        }
        
        if((bits.length - index) < remain){
            return;
        }
        
        //take the current bit
        bits[index] = 1;
        backtracker(bits, remain - 1, clocks, index + 1);
        
        //not take the current bit
        bits[index] = 0;
        backtracker(bits, remain, clocks, index + 1);
    }
    
    public List<String> readBinaryWatch(int num) {
        int[] bits = new int[10];
        List<String> clocks = new ArrayList<String>();
        backtracker(bits, num, clocks, 0);
        return clocks;
    }

    public static void main(String[] args){
        Solution sol;
        int n = 1;

        sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("clocks: " + sol.readBinaryWatch(n));
    }
}
