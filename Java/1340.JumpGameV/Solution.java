/* Dynamic programming + monotonous stack: Time:O(n), Space:O(n). 
 * 1. Collect left indexes with highest but low than arr[i] for 0 <= i <= arr.length - 1 by using monotonous decreasing stack
 * 2. Collect right ones by visit arr reversingly
 * 3. Remeber the longest path in dp[i] for index i
 * 4. Call dfs to get the longest path starting from arr[i] and update max
 */

import java.util.*; // Stack

public class Solution {
    private int dfs(int idx, int[] dp, Map<Integer, List<int[]>> idx2HighestLowerLefts, Map<Integer, List<int[]>> idx2HighestLowerRights){
        if(dp[idx] > 0){
            return dp[idx];
        }
        int max = 0;
        List<int[]> idx2HighestLows = new ArrayList<>();
        idx2HighestLows.addAll(idx2HighestLowerLefts.getOrDefault(idx, new LinkedList<>()));
        idx2HighestLows.addAll(idx2HighestLowerRights.getOrDefault(idx, new LinkedList<>()));
        for(int[] highestLowerLefts: idx2HighestLows){
            max = Math.max(max, dfs(highestLowerLefts[0], dp, idx2HighestLowerLefts, idx2HighestLowerRights));
        }
        dp[idx] = max + 1;
        return dp[idx];
    }
    
    private Map<Integer, List<int[]>> getIdx2HighestLow(int[] arr, int start, int end, int shift, int d){
        Map<Integer, List<int[]>> idx2HighestLowers = new HashMap<>();
        Deque<int[]> decreaseStack = new LinkedList<>();
        for(int i = start; i != end; i += shift){
            if(!decreaseStack.isEmpty() && Math.abs(i - decreaseStack.peekFirst()[0]) > d){
                decreaseStack.pollFirst();
            }
            while(!decreaseStack.isEmpty() && decreaseStack.peekLast()[1] < arr[i]){
                int[] top = decreaseStack.pollLast(); //top[0] = idx, top[1] = hight
                if(!idx2HighestLowers.containsKey(i)){
                    idx2HighestLowers.put(i, new ArrayList<>());
                    idx2HighestLowers.get(i).add(new int[]{top[0], top[1]});
                }else if(idx2HighestLowers.get(i).get(0)[1] == top[1]){
                    idx2HighestLowers.get(i).add(new int[]{top[0], top[1]});
                }else if(idx2HighestLowers.get(i).get(0)[1] < top[1]){
                    idx2HighestLowers.get(i).clear();
                    idx2HighestLowers.get(i).add(new int[]{top[0], top[1]});
                }
            }
            decreaseStack.add(new int[]{i, arr[i]});
        }
        return idx2HighestLowers;
    }
    
    public int maxJumps(int[] arr, int d) {
        Map<Integer, List<int[]>> idx2HighestLowerLefts = getIdx2HighestLow(arr, 0, arr.length, 1, d);
        Map<Integer, List<int[]>> idx2HighestLowerRights = getIdx2HighestLow(arr, arr.length - 1, -1, -1, d);
        int[] dp = new int[arr.length];
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(max, dfs(i, dp, idx2HighestLowerLefts, idx2HighestLowerRights));
        }
        return max;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {6, 4, 14, 6, 8, 13, 9, 7, 10, 6, 12}; 
        int d = 5; 
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("d:" + d);
        System.out.println("max jumps:" + sol.maxJumps(arr, d));
    }
}
