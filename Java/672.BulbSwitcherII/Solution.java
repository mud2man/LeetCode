/* : Math + BFS: O(m)
 * 1. No matter how large n is, lights repeat every 6 lights, because the flip is 111111..., 010101..., 101010..., 100100...
 * 2. And we can use BFS to solve it
 * 3. In the beginning of every loop, we pick configurations from combinationList
 * 4. Flip by  xor the mask = {63, 42, 21, 9} to get the next configuration
 * 5. If the next configuration is never be used, put it on the tail of combinationList
 * 6. The size of combinationList is the answer
 */

import java.util.*;


public class Solution{
    public int flipLights(int n, int m) {
        int[] masks = {63, 42, 21, 9};
        int window = 0;
        
        if(n == 0){
            return 0;
        }
        
        for(int i = 0; i < Math.min(6, n); ++i){
            window |= (1 << i);
        }
        
        int maxCount = (int)Math.pow(2, Math.min(6, n));
        LinkedList<Integer> combinationList = new LinkedList<Integer>();
        combinationList.add(63 & window);
        for(int i = 0; i < m && combinationList.size() < maxCount; ++i){
            int size = combinationList.size();
            Set<Integer> combinationSet = new HashSet<Integer>();
            for(int j = 0; j < size; ++j){
                int combination = combinationList.pollFirst();
                for(int mask: masks){
                    int newCombination = (combination ^ mask) & window;
                    if(!combinationSet.contains(newCombination)){
                        combinationList.add(newCombination);
                        combinationSet.add(newCombination);
                    }
                }
            }
        }
        
        return combinationList.size();
    }
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 3;
        int m = 1;
        
        System.out.println("n: " + n);
        System.out.println("m: " + m);
        System.out.println("number of status " + sol.flipLights(n, m));
    }
}
