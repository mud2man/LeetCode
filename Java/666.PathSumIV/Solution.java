/* Tree: Time:O(n) Space:O(n)
 * 1. Have a array "tree" to represnt the full binary tree with 4 levels
 * 2. Transfer the input "nums" into the array "tree"
 * 3. Accumulate the parent's value to its child
 * 4. If the current node is leave (tree[left] == -1 && tree[right] == -1), accumulate its value to "sum"
 */

import java.util.*;

public class Solution{
    public int pathSum(int[] nums) {
        int[] tree = new int[15];
        Arrays.fill(tree, -1);
        for(int num: nums){
            int level = num / 100;
            int base = (int)Math.pow(2, level - 1) - 1;
            num = num % 100;
            int index = base + (num / 10) - 1;
            int value = num % 10;
            tree[index] = value;
        }
        
        int sum = 0;
        for(int i = 0; i < 15; ++i){
            if(tree[i] == -1){
                continue;
            }
            
            int left = (i + 1) * 2 - 1;
            int right = (i + 1) * 2;
            if(left > 14){
                sum += tree[i]; 
            }
            else{
                if(tree[left] == -1 && tree[right] == -1){
                    sum += tree[i]; 
                }
                else{
                    tree[left] += (tree[left] >= 0)? tree[i]: 0;
                    tree[right] += (tree[right] >= 0)? tree[i]: 0;
                }
            }
        }
        
        return sum;
    }

    public static void main(String[] args){
        int[] nums = {113, 215, 221};
        Solution sol = new Solution();
        System.out.println("nums: " + Arrays.toString(nums));
        System.out.println("path sum: " + sol.pathSum(nums));
    }
}
