/* Greedy: Time:O(nlogn), Space:O(n)
 * 1. If we consider the largest number among positives, there is only one way to get the smaller partner of the largest number
 * 2. So we can sort the positive and negative number. And record the count of all numbers
 * 3. For positive numbers, we check from largest number. For negative numbers, we check from smallest number
 */

import java.util.*;

public class Solution{
    private boolean check(List<Integer> nums, Map<Integer, Integer> num2Count, int start, int end, int offset){
        for(int i = start; i != end && !nums.isEmpty(); i += offset){
            int num = nums.get(i);
            if(num2Count.get(num) > 0){
                if(num % 2 == 1){
                    return false;
                }
                num2Count.put(num, num2Count.get(num) - 1);
                int halfNum = num / 2;
                if(num2Count.containsKey(halfNum) && num2Count.get(halfNum) > 0){
                    num2Count.put(halfNum, num2Count.get(halfNum) - 1);
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean canReorderDoubled(int[] A) {
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        Map<Integer, Integer> num2Count = new HashMap<>();
        for(int i = 0; i < A.length; ++i){
            num2Count.putIfAbsent(A[i], 0);
            num2Count.put(A[i], num2Count.get(A[i]) + 1);
            if(A[i] < 0){
                negative.add(A[i]);
            }else{
                positive.add(A[i]);
            }
        }
        Collections.sort(positive);
        Collections.sort(negative);
        
        if(positive.size() % 2 == 1 || negative.size() % 2 == 1){
            return false;
        }
        return check(positive, num2Count, positive.size() - 1, 0, -1) & check(negative, num2Count, 0, negative.size() - 1, 1);
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {4, -2, 2, -4};
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("can reorder: " + sol.canReorderDoubled(nums));
    }
}
