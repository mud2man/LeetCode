/* Sliding window: Time:O(n), Space:O(1)
 * 1. Shift sliding's right end, while keep the window has at most 2 kinds of fruit
 * 2. Remember the longest window length
 */     

import java.util.*; // Stack

public class Solution {
    public int totalFruit(int[] tree) {
        int max = 0;
        Map<Integer, Integer> fruit2Count = new HashMap<>();
        for(int start = 0, end = 0; end < tree.length; ++end){
            int addFruit = tree[end];
            if(fruit2Count.size() == 2 && !fruit2Count.containsKey(addFruit)){
                while(fruit2Count.size() == 2){
                    int deleteFruit = tree[start++];
                    fruit2Count.put(deleteFruit, fruit2Count.get(deleteFruit) - 1);
                    if(fruit2Count.get(deleteFruit) == 0){
                        fruit2Count.remove(deleteFruit);
                    }
                }
            }
            fruit2Count.put(addFruit, fruit2Count.getOrDefault(addFruit, 0) + 1);
            max = Math.max(max, end - start + 1);
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] tree = {0, 1, 2, 2};
        System.out.println(Arrays.toString(tree));
        System.out.println("fruit max#:" + sol.totalFruit(tree));
    }
}
