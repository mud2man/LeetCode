/* Heap: Time:O(k * (n * m + nlogm)) = O(k * n * m), Space:O(m^n)
 * 1.Store the array = {sum, index-0, index-1, ... index-n} to min heap sorted by sum
 * 2.Each time, we pick the smallest sum and shift index-0, index-1, ... index-n right one for each. And store the next indexs to heap
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    private String getHash(int[] sumIndexs){
        StringBuilder hash = new StringBuilder("");
        for(int i = 1; i < sumIndexs.length; ++i){
            hash.append(Integer.toString(sumIndexs[i]) + "-");
        }
        return hash.toString();
    }
    
    public int kthSmallest(int[][] mat, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((x, y) -> (x[0] - y[0]));
        Set<String> used = new HashSet<>();
        int matLength = mat.length;
        int firstSum = 0;
        for(int[] row: mat){
            firstSum += row[0];
        }
        int[] firstSumIndexs = new int[matLength + 1];
        firstSumIndexs[0] = firstSum;
        minHeap.add(firstSumIndexs);
        used.add(getHash(firstSumIndexs));
        for(int i = 0; i < k - 1; ++i){
            int[] top = minHeap.poll();
            for(int j = 0; j < matLength; ++j){
                int nextIdx = top[j + 1] + 1;
                if(nextIdx < mat[j].length){
                    int[] nextSumIndexs = Arrays.copyOf(top, top.length);
                    nextSumIndexs[0] += (mat[j][nextIdx] - mat[j][nextIdx - 1]);
                    nextSumIndexs[j + 1]++;
                    String hash = getHash(nextSumIndexs);
                    if(!used.contains(hash)){
                        used.add(hash);
                        minHeap.add(nextSumIndexs);
                    }
                }
            }
        }
        return minHeap.peek()[0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] mat = {{1, 3, 11}, {2, 4, 6}};
        int k = 5;
        System.out.println("mat:");
        for(int[] row: mat){
            System.out.println(Arrays.toString(row));
        }
        System.out.println("k:" + k);
        System.out.println("k-th smallest sum:" + sol.kthSmallest(mat, k));
    }
}
