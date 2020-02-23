/* Tree: Time:O(n), Space:O(n)
 * 1. Have "parentCounts[i]" to record the parent number of node i
 * 2. Return true only if parentCounts[i] <= 0, where 0 <= i <= n - 1, and only one root
 */

import java.util.*;

/* Definition for binary tree */
public class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] parentCounts = new int[n];
        for(int i = 0; i < n; ++i){
            if(leftChild[i] != -1){
                parentCounts[leftChild[i]]++;
            }
            if(rightChild[i] != -1){
                parentCounts[rightChild[i]]++;
            }
        }
        int rootCount = 0;
        for(int parentCount: parentCounts){
            if(parentCount == 0){
               rootCount++; 
            }else if(parentCount > 1){
                return false;
            }
        }
        return (rootCount == 1);
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        int n = 4;
        int[] leftChild = {1, -1, 3, -1};
        int[] rightChild = {2, -1, -1, -1};
        System.out.println("n:" + n + ", leftChild:" + Arrays.toString(leftChild) + ", rightChild:" + Arrays.toString(rightChild));
        System.out.println("is valid tree:" + sol.validateBinaryTreeNodes(n, leftChild, rightChild));
    }
}
