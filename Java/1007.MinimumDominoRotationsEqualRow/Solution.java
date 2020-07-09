/* Map: Time:O(n), Space:O(n)
 * 1. Have upperEndNum2Count/lowerEndNum2Count to remember the smallest count to make upper/lower row equal
 * 2. Update upperEndNum2Count/lowerEndNum2Count as we shift index to right
 */     

import java.util.*; // Stack

public class Solution {
    public int minDominoRotations(int[] A, int[] B) {
        Map<Integer, Integer> upperEndNum2Count = new HashMap<>();
        Map<Integer, Integer> lowerEndNum2Count = new HashMap<>();
        upperEndNum2Count.put(A[0], 0);
        upperEndNum2Count.put(B[0], 0);
        lowerEndNum2Count.put(A[0], 0);
        lowerEndNum2Count.put(B[0], 0);
        for(int i = 0; i < A.length; ++i){
            Map<Integer, Integer> nextUpperEndNum2Count = new HashMap<>();
            if(upperEndNum2Count.containsKey(A[i])){
                nextUpperEndNum2Count.put(A[i], upperEndNum2Count.get(A[i]));
            }
            if(upperEndNum2Count.containsKey(B[i]) && A[i] != B[i]){
                nextUpperEndNum2Count.put(B[i], upperEndNum2Count.get(B[i]) + 1);
            }
            Map<Integer, Integer> nextLowerEndNum2Count = new HashMap<>();
            if(lowerEndNum2Count.containsKey(B[i])){
                nextLowerEndNum2Count.put(B[i], lowerEndNum2Count.get(B[i]));
            }
            if(lowerEndNum2Count.containsKey(A[i]) && A[i] != B[i]){
                nextLowerEndNum2Count.put(A[i], lowerEndNum2Count.get(A[i]) + 1);
            }
            upperEndNum2Count = nextUpperEndNum2Count;
            lowerEndNum2Count = nextLowerEndNum2Count;
            if(nextUpperEndNum2Count.isEmpty() && nextLowerEndNum2Count.isEmpty()){
                return -1;
            }
        }
        
        int min = A.length + 1;
        for(Map.Entry<Integer, Integer> entry: upperEndNum2Count.entrySet()){
            min = Math.min(min, entry.getValue());
        }
        for(Map.Entry<Integer, Integer> entry: lowerEndNum2Count.entrySet()){
            min = Math.min(min, entry.getValue());
        }
        return min;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] A = {2, 1, 2, 4, 2, 2};
        int[] B = {5, 2, 6, 2, 3, 2};
        System.out.println("A:" + Arrays.toString(A));
        System.out.println("B:" + Arrays.toString(B));
        System.out.println("minimum rotation#:" + sol.minDominoRotations(A, B));
    }
}
