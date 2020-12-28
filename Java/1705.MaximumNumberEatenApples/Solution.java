/* Greedy + Heap: Time:O(nlogn), Space:O(n). Note: the O(n) claimed solution on LeetCode is actually O(n^2)
 * 1. Store the map of date-to-count into the sorted map
 * 2. Update expireDate2Count if apples[date] > 0
 * 3. Remove the rotten apples as we scan date and pick the apple with the earliest expired date 
 * 4. After loop, we pick the apples by expired date chunk by chunk, then update date and expireDate2Count
 */

import java.util.*; // Stack

public class Solution {
    private void removeExpiredApple(int date, TreeMap<Integer, Integer> expireDate2Count){
        while(!expireDate2Count.isEmpty() && expireDate2Count.firstKey() <= date){
            expireDate2Count.remove(expireDate2Count.firstKey());
        }
    }
    
    public int eatenApples(int[] apples, int[] days) {
        TreeMap<Integer, Integer> expireDate2Count = new TreeMap<>();
        int count = 0;
        for(int date = 0; date < apples.length; date++){
            if(apples[date] > 0){
                if(!expireDate2Count.containsKey(date + days[date])){
                    expireDate2Count.put(date + days[date], 0);
                }
                expireDate2Count.put(date + days[date], expireDate2Count.get(date + days[date]) + apples[date]);
            }
            removeExpiredApple(date, expireDate2Count);
            if(!expireDate2Count.isEmpty()){
                count++;
                int earliestDate = expireDate2Count.firstKey();
                expireDate2Count.put(earliestDate, expireDate2Count.get(earliestDate) - 1);
                if(expireDate2Count.get(earliestDate) == 0){
                    expireDate2Count.remove(earliestDate);
                }
            }
        }
        
        int date = apples.length;
        while(!expireDate2Count.isEmpty()){
            removeExpiredApple(date, expireDate2Count);
            int nextExpiredDate = expireDate2Count.firstKey();
            count += Math.min(nextExpiredDate - date, expireDate2Count.get(nextExpiredDate));
            expireDate2Count.remove(nextExpiredDate);
            date = nextExpiredDate;
        }
        return count;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] apples = {1, 2, 3, 5, 2}; 
        int[] days = {3, 2, 1, 4, 2}; 
        System.out.println("apples:" + Arrays.toString(apples));
        System.out.println("days:" + Arrays.toString(days));
        System.out.println("max eaten apples:" + sol.eatenApples(apples, days));
    }
}
