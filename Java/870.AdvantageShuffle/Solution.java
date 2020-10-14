/* Sort + Map: Time:O(nlogn), Space:O(n)
 * 1. Sort A nad B
 * 2. Iterate A from left, accumulate idxB and put cloneB[idxB] into b2As, when cloneA[idxA] > cloneB[idxB]
 * 3. Put the unassigned numbers to cloneA
 */     

import java.util.*; // Stack

public class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        int[] cloneA = Arrays.copyOf(A, A.length);
        int[] cloneB = Arrays.copyOf(B, B.length);
        Arrays.sort(cloneA);
        Arrays.sort(cloneB);
        Map<Integer, Deque<Integer>> b2As = new HashMap<>();
        Map<Integer, Integer> numInA2Count = new HashMap<>();
        for(int idxA = 0, idxB = 0; idxA < A.length; idxA++){
            if(cloneA[idxA] > cloneB[idxB]){
                b2As.computeIfAbsent(cloneB[idxB++], key -> new LinkedList<>()).add(cloneA[idxA]);
            }   
            numInA2Count.put(cloneA[idxA], numInA2Count.getOrDefault(cloneA[idxA], 0) + 1);
        }
        
        Arrays.fill(cloneA, -1);
        for(int i = 0; i < B.length; ++i){
            int b = B[i];
            Deque<Integer> as = b2As.getOrDefault(b, new LinkedList<>());
            if(!as.isEmpty()){
                cloneA[i] = as.poll();
                numInA2Count.put(cloneA[i], numInA2Count.get(cloneA[i]) - 1);
            }
        }
        
        Deque<Integer> queue = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: numInA2Count.entrySet()){
            int num = entry.getKey();
            int count = entry.getValue();
            for(int i = 0; i < count; ++i){
                queue.add(num);
            }
        }
        for(int i = 0; i < A.length; ++i){
            cloneA[i] =(cloneA[i] == -1)? queue.poll(): cloneA[i];
        }
        return cloneA;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {2, 7, 11, 15};
        int[] B = {1, 10, 4, 11};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("B:" + Arrays.toString(B));
        System.out.println("A after shuffled:" + Arrays.toString(sol.advantageCount(A, B)));
    }
}
