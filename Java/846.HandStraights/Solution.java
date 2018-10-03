/* Map: Time:O(nlogn), Space:O(n)
 * 1. Use tree map to arrange the number-count pair with increasing order 
 * 2. Take W pair each time, and check if the number is consectuve. Otherwise retunr false
 * 3. In the end, return true
 */

import java.util.*; // Stack

public class Solution {
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length % W != 0){
            return false;
        }
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int n: hand){
            map.putIfAbsent(n, 0);
            map.put(n, map.get(n) + 1);
        }
        
        List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(map.entrySet());
        while(!entries.isEmpty()){
            int val = entries.get(0).getKey();
            Iterator<Map.Entry<Integer, Integer>> itr = entries.iterator();
            for(int i = 0; i < W; ++i){
                if(entries.size() <= i){
                    return false;
                }
                else{
                    Map.Entry<Integer, Integer> entry = itr.next();
                    int key = entry.getKey();
                    if(key != val){
                        return false;
                    }
                    map.put(val, entry.getValue() - 1);
                }
                val++;
            }
            
            while(!entries.isEmpty() && entries.get(0).getValue() == 0){
                entries.remove(0);
            }
        }
        return true;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};        
        int W = 3;;        
        System.out.println("W: " + W);
        System.out.println("hand: " + Arrays.toString(hand));
        System.out.println("straights exist: " + sol.isNStraightHand(hand, W));
    }
}
