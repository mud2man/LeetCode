/* Binary Indexd Tree: Time:O(logn), Space:O(n).
 * 1. When update, call getNext to update tree[]
 * 2. When sum, call getParent to accumulate the answer
 * 3. In region sum, answer = sum(j) - sum(i - 1)
 */

import java.util.*;

public class Solution{
    int[] bitTree;
    int[] nums;
    
    public Solution(int[] nums) {
        bitTree = new int[nums.length + 1];
        this.nums = new int[nums.length];
        for(int i = 0; i < nums.length; ++i){
            update(i, nums[i]);
        }
    }
    
    private int getNext(int x){
        return x + (x & (-x));
    }
    
    private int getParent(int x){
        return x - (x & (-x));
    }
    
    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        for(int x = i + 1; x < bitTree.length; x = getNext(x)){
            bitTree[x] += diff;
        }
    }
    
    private int sum(int i){
        if(i < 0){
            return 0;
        }
        
        int sum = 0;
        for(int x = i + 1; x > 0; x = getParent(x)){
            sum += bitTree[x];
        }
        return sum;
    }
    
    public int sumRange(int i, int j) {
        return sum(j) - sum(i - 1);
    }
 
    public static void main(String[] args){
        Solution sol;
        int[] nums = {1, 3, 5};
        sol = new Solution(nums);
        System.out.println("nums: " + Arrays.toString(nums));
        
        System.out.println("sumRange(0 ,2):" + sol.sumRange(0, 2));
        System.out.println("update(1, 2)");
        sol.update(1, 2);
        System.out.println("sumRange(0 ,2):" + sol.sumRange(0, 2));
    }
}
