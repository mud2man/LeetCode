/* Greedy: Time:O(nlogn), Space:O(n)
 * 1. Construct num2Count and sort the counts using increasing order
 * 2. Subtract k with counts.get(i) until k <= 0
 */     

import java.util.*; // Stack

public class Solution {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> num2Count = new HashMap<>();
        for(int num: arr){
            num2Count.put(num, num2Count.getOrDefault(num, 0) + 1);
        }
        List<Integer> counts = new ArrayList<>(num2Count.values());
        Collections.sort(counts);
        for(int i = 0; i < counts.size(); ++i){
            if(k == 0){
                return counts.size() - i;
            }else if(k < 0){
                return counts.size() - i + 1;
            }else{
                k -= counts.get(i);
            }
        }
        return (k >= 0)? 0: 1;
    } 
    
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {5, 5, 4};
        int k = 1;
        System.out.println("arr:" + Arrays.toString(arr));
        System.out.println("k:" + k);
        System.out.println("least number of uniques:" + sol.findLeastNumOfUniqueInts(arr, k));
    }
}
