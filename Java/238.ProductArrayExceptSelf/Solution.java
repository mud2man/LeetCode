/* : O(n)
 * 1. Let products[i] = nums[0] * nums[1] * ... nums[i - 1] 
 * 2. Reassign products, s.t. products[j] = products[j - 1] * rightProduct;
 */         

import java.util.*;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] products = new int[nums.length];
        
        int product = 1;
        for(int i = 0; i < nums.length; ++i){
            product = product * nums[i];
            products[i] = product;
        }
        
        products[nums.length - 1] = products[nums.length - 2];
        int rightProduct = nums[nums.length - 1];
        for(int j = nums.length - 2; j > 0 ; --j){
            products[j] = products[j - 1] * rightProduct;
            rightProduct = rightProduct * nums[j];
        }
        products[0] = rightProduct;
        
        return products;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[] nums = {1, 2, 3, 4};
        
        System.out.println("nums: " + Arrays.toString(nums));
        int[] products = sol.productExceptSelf(nums);
        System.out.println("products: " + Arrays.toString(products));
    }
}
