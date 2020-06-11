/* Greedy: Time:O(n), Space:O(K)
 * 1. We have a queue to store the right ends of flip interval
 * 2. Scan from left, and we flip findZero when we add a new flipping interval
 * 3. Flip findZero wwhen we reach the right ends of one flipping interval 
 *
 * Proof: 
 * 1. Assume the minimum set of flipping intervals I, there exists a leftest flipping interval L
 * 2. Remove the L from I, we can said I is L + the minimum set of flipping intervals covering {A[i], A[i + 1], ..., A[n - 1]}, 
 *    where i is the first index with value 1
 * 3. Statement 2 stands for the size of the minimum set of flipping intervals covering {A[i], A[i + 1], ..., A[n - 1]} is smaller than
 *    the size of the minimum set of flipping intervals covering {A[j], A[j + 1], ..., A[n - 1], where j > i 
 */     


import java.util.*; // Stack

public class Solution {
    public int minKBitFlips(int[] A, int K) {
        int count = 0;
        boolean findZero = true;
        Deque<Integer> ends = new LinkedList<>();
        for(int i = 0 ; i < A.length; ++i){
            if(!ends.isEmpty() && i > ends.peekFirst()){
                ends.pollFirst();
                findZero = !findZero;
            }
            if((A[i] == 0 && findZero) || (A[i] == 1 && !findZero)){
                count++;
                findZero = !findZero;
                if(i + K != A.length){
                    ends.add(i + K - 1);
                }
            }
        }
        return ends.isEmpty()? count: -1;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {0, 0, 0, 1, 0, 1, 1, 0};
        int K = 3;
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("K:" + K);
        System.out.println("minimum number of flips:" + sol.minKBitFlips(A, K));
    }
}
