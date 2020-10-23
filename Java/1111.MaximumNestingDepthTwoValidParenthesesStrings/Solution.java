/* Greedy: Time:O(n), Space:O(1)
 * 1. Iterate seq with  depth and do classifying by depth % 2
 * 2. Assign type[i] with the classifier
 */     

import java.util.*; // Stack

public class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] type = new int[seq.length()];
        int depth = 0;
        for(int i = 0; i < type.length; ++i){
            if(seq.charAt(i) == '('){
                depth++;
                type[i] = depth % 2;
            }else{
                type[i] = depth % 2;
                depth--;
            }
        }
        return type;
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String seq = "(()())";
        System.out.println("seq:" + seq);
        System.out.println("after split:" + Arrays.toString(sol.maxDepthAfterSplit(seq)));
    }
}
