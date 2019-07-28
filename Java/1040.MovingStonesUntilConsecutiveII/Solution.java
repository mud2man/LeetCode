/* Sliding window: Time:O(nlogn), Space:O(n)
 * 1. For "getMinMoves", we use sliding window "queue" to get the minimun moves by caculating the empty spaces in the queue
 * 2. For "getMaxMoves", we can get the maximum moves by getting the empty spaces if leftGap > 1 and rightGap > 1, since every move reduce the length by 1
 * 3. Otherwise, we get the empty spaces from the larger part
 */

import java.util.*; // Stack

public class Solution {
    private int getMinMoves(int[] stones){
        int moves = Integer.MAX_VALUE;
        Deque<Integer> queue = new LinkedList<>();
        int idx = 0;
        while(idx < stones.length){
            if(queue.isEmpty()){
                queue.add(stones[idx++]);
            }
            int front = queue.peekFirst();
            while(idx < stones.length && stones[idx] - front + 1 <= stones.length){
                queue.add(stones[idx++]);
            }
            if((queue.size() <= stones.length - 2) || (queue.size() != queue.peekLast() - queue.peekFirst() + 1)){
                moves = Math.min(moves, stones.length - queue.size());
            }
            queue.pollFirst();
        }
        return (moves == Integer.MAX_VALUE)? 0: moves;
    }
    
    private int getMaxMoves(int[] stones){
        int leftGap = stones[1] - stones[0];
        int rightGap = stones[stones.length - 1] - stones[stones.length - 2];
        int length = 0;
        if(leftGap > 0 && rightGap > 0){
            if(leftGap < rightGap){
                length = stones[stones.length - 1] - stones[1] + 1;
            }else{
                length = stones[stones.length - 2] - stones[0] + 1;
            }
        }else if(leftGap == 0){
            length = stones[stones.length - 2] - stones[0] + 1;
        }else{
            length = stones[stones.length - 1] - stones[1] + 1;
        }
        return length - stones.length + 1;
    }
    
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        return new int[]{getMinMoves(stones), getMaxMoves(stones)};
    }
  
    public static void main(String[] args){
        int[] stones = {6, 5, 4, 3, 10};
        Solution sol = new Solution();
        System.out.println("stones: " + Arrays.toString(stones));
        System.out.println("moves: " + Arrays.toString(sol.numMovesStonesII(stones)));
    }
}
