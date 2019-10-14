/* Queue: Time:O(n), Space:O(n)
 * 1. Have a "counts" to store the info of num to count 
 * 2. Maintain a "queue" to store pairs (number, duplicates#)
 * 3. When we see counts[i] == 0 or i >= 40_000, we take the front of queue, add (i - front[0]) to increment
 * 4. When we see counts[i] > 1, add (i, counts[i] - 1) to the tail of queue
 */

import java.util.*;

public class Solution{
    public int minIncrementForUnique(int[] A) {
        int[] counts = new int[40_000];
        for(int a: A){
            counts[a]++;
        }
        
        Deque<int[]> queue = new LinkedList<>();
        int increment = 0;
        for(int i = 0; i < 80_000; ++i){
            if(!queue.isEmpty() && (i >= counts.length || counts[i] == 0)){
                int[] front = queue.pollFirst();
                increment += (i - front[0]);
                front[1]--;
                if(front[1] > 0){
                    queue.addFirst(front);
                }
            }else if(i < counts.length && counts[i] > 1){
                queue.add(new int[]{i, counts[i] - 1});
            }else{
                continue;
            }
        }
        return increment;
    }
  
    public static void main(String[] args){
        int[] A = {1, 2, 2};
        Solution sol = new Solution();
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("minimum increment:" + sol.minIncrementForUnique(A));
    }
}
