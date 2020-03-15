/* Binary serach:Time:O((n + m) * logn), Space:O(n), where n is profit# and m is worker#
 * 1. Pair difficulty and profit, and sort them by difficulty and then profit
 * 2. Iterate work's capacity, and binary search the max profit given the worker's capacity
 */

import java.util.*;

public class Solution {
    private int binarySearch(List<int[]> increseDifficultyProfits, int capacity){
        int l = 0;
        int r = increseDifficultyProfits.size() - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            int diffculty = increseDifficultyProfits.get(mid)[0];
            if(diffculty <= capacity){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return (r >= 0)? increseDifficultyProfits.get(r)[1]: 0;
    }
    
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] difficultyProfits = new int[difficulty.length][2];
        for(int i = 0; i < difficulty.length; ++i){
            difficultyProfits[i][0] = difficulty[i];
            difficultyProfits[i][1] = profit[i];
        }
        Arrays.sort(difficultyProfits, (x, y) -> ((x[0] != y[0])? x[0] - y[0]: y[1] - x[1]));
        List<int[]> increseDifficultyProfits = new ArrayList<>();
        for(int[] difficultyProfit: difficultyProfits){
            if(increseDifficultyProfits.isEmpty()){
                increseDifficultyProfits.add(difficultyProfit);
            }else{
                int[] last = increseDifficultyProfits.get(increseDifficultyProfits.size() - 1);
                if(difficultyProfit[1] > last[1]){
                    increseDifficultyProfits.add(difficultyProfit);
                }
            }
        }
        int maxProfit = 0;
        for(int capacity: worker){
            maxProfit += binarySearch(increseDifficultyProfits, capacity);
        }
        return maxProfit;
    }
  
    public static void main(String[] args){
        int[] difficulty = {2, 4, 6, 8, 10};
        int[] profit = {10, 20, 30, 40, 50};
        int[] worker = {4, 5, 6, 7};
        Solution sol = new Solution();
        System.out.println("difficulty:" + Arrays.toString(difficulty));
        System.out.println("profit:" + Arrays.toString(profit));
        System.out.println("worker:" + Arrays.toString(worker));
        System.out.println("max profit:" + sol.maxProfitAssignment(difficulty, profit, worker));
    }
}
