/* Merge Sort: Time:O(nlogn), Space:O(n)
 * 1. Transform nums to nodes, where node[0] = nums[i], node[1] = i, nodes[2] = count of smaller numbers after self
 * 2. Use merge sort to accumulate nodes[i][2], in the case of start <= i <= mid < j <= end
 * 3. Transform nodes by the given information index = nodes[i][1]
 */

import java.util.*;

public class Solution{
    private void mergeSort(int[][] nodes, int start, int end){
        if(start >= end){
            return;
        }
        
        int mid = (start + end) / 2;
        mergeSort(nodes, start, mid);
        mergeSort(nodes, mid + 1, end);
        Deque<int[]> queue = new LinkedList<>();
        int i = start;
        int j = mid + 1;
        int count = 0;
        while(i <= mid || j <= end){
            int[] left = (i <= mid)? nodes[i]: null;
            int[] right = (j <= end)? nodes[j]: null;
            if(left != null && right != null){
                if(left[0] > right[0]){
                    count++;
                    j++;
                    queue.add(right);
                }
                else{
                    i++;
                    left[2] += count;
                    queue.add(left);
                }
            }
            else if(left == null){
                j++;
                queue.add(right);
            }
            else{
                i++;
                left[2] += count;
                queue.add(left);
            }
        }
        
        for(int k = start; k <= end; ++k){
            nodes[k] = queue.pollFirst();
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        int[][] nodes = new int[nums.length][3];
        List<Integer> counts = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i){
            nodes[i][0] = nums[i];
            nodes[i][1] = i;
            counts.add(0);
        }
        mergeSort(nodes, 0, nodes.length - 1);
        for(int[] node: nodes){
            counts.set(node[1], node[2]);
        }
        return counts;
    }

    public static void main(String[] args){
        int[] nums = {5, 2, 6, 1};
        Solution sol = new Solution();
        
        System.out.println("nums:" + Arrays.toString(nums));
        System.out.println("count: " + sol.countSmaller(nums));
    }
}
