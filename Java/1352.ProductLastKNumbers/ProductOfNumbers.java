/* Map: Time:O(1), Space:O(n)
 * 1. Have a map "firstKNums2Product" to remember the product of first k number
 * 2. Reset "firstKNums2Product" when seeing 0
 * 3. The product of last numbers is firstKNums2Product.get(totalCount) / firstKNums2Product.get(totalCount - k);
 */     

import java.util.*; // Stack

public class ProductOfNumbers {
    Map<Integer, Integer> firstKNums2Product;
    public ProductOfNumbers() {
        firstKNums2Product = new HashMap<>();
    }
    
    public void add(int num) {
        if(num == 0){
            firstKNums2Product = new HashMap<>();
        }else{
            int count = firstKNums2Product.size() + 1;
            firstKNums2Product.put(count, firstKNums2Product.getOrDefault(count - 1, 1) * num);
        }
    }
    
    public int getProduct(int k) { 
        if(firstKNums2Product.size() < k){
            return 0;
        }else{
            int totalCount = firstKNums2Product.size();
            int unnecessaryCount = totalCount - k;
            return firstKNums2Product.get(totalCount) / firstKNums2Product.getOrDefault(unnecessaryCount, 1);
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        CBTInserter tree = new CBTInserter(root);
        System.out.println("insert:" + tree.insert(2));
        System.out.println("get_root:" + tree.get_root().val);
    }
}
