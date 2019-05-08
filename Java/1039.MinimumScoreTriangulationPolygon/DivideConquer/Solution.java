/* Divide and Conquer: Time:O(n^n), Space:O(n^n)
 */

import java.util.*; // Stack

public class Solution {
    private int divideConquer(Deque<Integer> queue){
        if(queue.size() == 3){
            int score = 1;
            for(Integer i: queue){
                score *= i;
            }
            return score; 
        }
        
        int middel = queue.pollFirst();
        int right = queue.peekFirst();
        int left = queue.peekLast();
        int min = (middel * right * left) + divideConquer(queue);
        Deque<Integer> leftQueue = new LinkedList<>();
        leftQueue.addFirst(middel);
        leftQueue.addFirst(queue.pollLast());
        leftQueue.addFirst(queue.peekLast());
        queue.addFirst(middel);
        while(queue.size() >= 3){
            min = Math.min(min, divideConquer(leftQueue) + divideConquer(queue));
            queue.pollLast();
            leftQueue.addFirst(queue.peekLast());
        }

        leftQueue.pollFirst();
        while(leftQueue.size() > 1){
            queue.add(leftQueue.pollFirst());
        }
        return min;
    }
    
    public int minScoreTriangulation(int[] A) {
        Deque<Integer> queue = new LinkedList<>();
        for(int a: A){
            queue.add(a);
        }
        return divideConquer(queue);
    }
 
    public static void main(String[] args){
        int[] A = {3, 7, 4, 5};
        Solution sol = new Solution();
        System.out.println("A: " + Arrays.toString(A));
        System.out.println("Minimum score: " + sol.minScoreTriangulation(A));
    }
}
