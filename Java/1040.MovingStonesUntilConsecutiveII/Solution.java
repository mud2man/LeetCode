/* Sliding window: Time:O(nlogn), Space:O(n)
 * 1. For "getMinMoves", we use sliding window "queue" to get the minimun moves by caculating the empty spaces in the queue
 * 2. For "getMaxMoves", we can get the maximum moves by getting the empty spaces if leftGap > 1 and rightGap > 1, since every move reduce the length by 1
 * 3. Otherwise, we get the empty spaces from the larger part
 */

import java.util.*; // Stack

public class Solution {
    private int getMinMoves(int[] stones){
        int minMoves = Integer.MAX_VALUE;
        Deque<Integer> queue = new LinkedList<>();
        int idx = 0;
        queue.add(stones[idx++]);
        while(idx < stones.length){
            int endValue = (!queue.isEmpty())? stones.length + queue.peekFirst() - 1: 0;
            while(idx < stones.length && stones[idx] <= endValue){
                queue.addLast(stones[idx++]);
            }
            if(queue.peekLast() == endValue 
               || idx < (stones.length - 1) 
               || (queue.peekFirst() != stones[0] && idx < stones.length)){
                minMoves = Math.min(minMoves, stones.length - queue.size());
            }
            queue.pollFirst();
            if(queue.isEmpty()){
                queue.addLast(stones[idx++]);
            }
        }
        return minMoves;
    }
    
    private int getMaxMoves(int[] stones){
        int leftGap = stones[1] - stones[0];
        int rightGap = stones[stones.length - 1] - stones[stones.length - 2];
        if(leftGap > 1 && rightGap > 1){
            return (leftGap > rightGap)? 
                (stones[stones.length - 2] - stones[0] + 1) - (stones.length - 1):
                (stones[stones.length - 1] - stones[1] + 1) - (stones.length - 1);
        }else{
            return (stones[stones.length - 1] - stones[0] + 1) - stones.length;
        }
    }
    
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int[] movies = new int[2];
        movies[0] = getMinMoves(stones);
        movies[1] = getMaxMoves(stones);
        return movies;
    }
  
    public static void main(String[] args){
        int[] stones = {6, 5, 4, 3, 10};
        Solution sol = new Solution();
        System.out.println("stones: " + Arrays.toString(stones));
        System.out.println("moves: " + Arrays.toString(sol.numMovesStonesII(stones)));
    }
}
