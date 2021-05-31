/* Two Pointers: Time:O(n + m), Space:O(1)
 * 1. Track on both arrays with index i and j
 * 2. Caculate and merge the last product with {product, minFreq} 
 */

import java.util.*; // Stack


public class Solution {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int i = 0;
        int j = 0;
        List<List<Integer>> products = new ArrayList<>();
        while(i < encoded1.length || j < encoded2.length){
            int minFreq = Math.min(encoded1[i][1], encoded2[j][1]);
            int product = encoded1[i][0] * encoded2[j][0];
            if(products.isEmpty() || products.get(products.size() - 1).get(0) != product){
                products.add(Arrays.asList(product, minFreq));
            }else{
                products.get(products.size() - 1).set(1, products.get(products.size() - 1).get(1) + minFreq);
            }
            encoded1[i][1] -= minFreq;
            i +=(encoded1[i][1] == 0)? 1: 0;
            encoded2[j][1] -= minFreq;
            j +=(encoded2[j][1] == 0)? 1: 0;
        }
        return products;
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] encoded1 = {{1, 3}, {2, 3}};
        int[][] encoded2 = {{6, 3}, {3, 3}};;
        System.out.println("products:" + sol.findRLEArray(encoded1, encoded2));
    }
}
