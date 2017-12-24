/* Sliding window: Time:O(n), Space:O(n)
 * 1. Translate flowers into days, where days[i] = x, means the flower i bloom at day x
 * 2. Have two index head and tail as the window
 * 3. Traverse the array days, if (days[i] < days[head] or days[i] <= days[tail]), update minDay, head, tail
 */

import java.util.*;

public class Solution{
    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for(int i = 0; i < flowers.length; ++i){
            days[flowers[i] - 1] = i + 1;
        }
        
        int head = 0;
        int tail = k + 1; 
        
        int minDay = Integer.MAX_VALUE;
        for(int i = 0; i < days.length && tail < days.length; ++i){
            if(days[i] < days[head] || days[i] <= days[tail]){
                if(i == tail){
                    minDay = Math.min(minDay, Math.max(days[head], days[tail]));
                }
                head = i;
                tail = i + k + 1;
            }
        }
        
        return (minDay == Integer.MAX_VALUE)? -1: minDay;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] flowers = {1, 3, 2};
        int k = 1;
        
        System.out.println("flowers: " + Arrays.toString(flowers));
        System.out.println("k: " + k);
        System.out.println("minimum day: " + sol.kEmptySlots(flowers, k));
    }
}
