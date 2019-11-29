/* Merge Sort: Time:O(nlogn), Space:O(n)
 * 1. Transform nums to pairs, where pair[0] = nums[i], pair[1] = i,
 * 2. Use merge sort to accumulate counts[i], in the case of start <= i <= mid < j <= end
 */

import java.util.*;

public class Solution{
    private List<int[]> mergeSort(List<int[]> pairs, int start, int end, List<Integer> counts){
        List<int[]> mergedPairs = new ArrayList<>();
        if(start >= end){
            if(start == end){
                mergedPairs.add(pairs.get(start));  
            }
            return mergedPairs;
        }
        int mid = (start + end) / 2;
        List<int[]> leftPairs = mergeSort(pairs, start, mid, counts);
        List<int[]> rightPairs = mergeSort(pairs, mid + 1, end, counts);
        for(int i = 0, leftIdx = 0, rightIdx = 0; i < end - start + 1; ++i){
            int[] leftPair = (leftIdx < leftPairs.size())? leftPairs.get(leftIdx): null;
            int[] rightPair = (rightIdx < rightPairs.size())? rightPairs.get(rightIdx): null;
            if(leftPair == null){
                mergedPairs.add(rightPair);
                rightIdx++;
            }else if(rightPair == null){
                mergedPairs.add(leftPair);
                leftIdx++;
            }else if(leftPair[0] > rightPair[0]){
                int count = rightPairs.size() - rightIdx;
                int idx = leftPair[1];
                counts.set(idx, counts.get(idx) + count);
                mergedPairs.add(leftPair);
                leftIdx++;
            }else{
                mergedPairs.add(rightPair);
                rightIdx++;
            }
        }
        return mergedPairs;
    }
    
    public List<Integer> countSmaller(int[] nums) {
        List<int[]> pairs = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i){
            pairs.add(new int[]{nums[i], i});
            counts.add(0);
        }
        mergeSort(pairs, 0, pairs.size() - 1, counts);
        return counts;
    } 
    public static void main(String[] args){
        int[] nums = {5, 2, 6, 1};
        Solution sol = new Solution();
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("count: " + sol.countSmaller(nums));
    }
}
