/* Map: Time:O(n), Space:O(n)
 * 1. Use map "index2Num" to store the sparse array
 * 2. In dotProduct, get the sparser vector by comparing index2Num.size() and use it to iterate and multiply
 */     

import java.util.*; // Stack

public class SparseVector {
    Map<Integer, Integer> index2Num;
    SparseVector(int[] nums) {
        index2Num = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            if(nums[i] != 0){
                index2Num.put(i, nums[i]);    
            }
        }
    }
    
    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int product = 0;
        SparseVector moreSparseVec = (vec.index2Num.size() <= this.index2Num.size())? vec: this;
        SparseVector lessSparseVec = (vec.index2Num.size() > this.index2Num.size())? vec: this;
        for(Map.Entry<Integer, Integer> entry: moreSparseVec.index2Num.entrySet()){
            int index = entry.getKey();
            int num = entry.getValue();
            product += num * lessSparseVec.index2Num.getOrDefault(index, 0);
        }
        return product;
    }
  
    public static void main(String[] args){
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 0};
        SparseVector v1 = new SparseVector(nums1);
        SparseVector v2 = new SparseVector(nums2);
        System.out.println("nums1:" + Arrays.toString(nums1));
        System.out.println("nums2:" + Arrays.toString(nums2));
        System.out.println("dotProduct:" + v1.dotProduct(v2));
    }
}
