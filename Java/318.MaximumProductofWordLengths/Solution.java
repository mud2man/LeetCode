/* Bit manupulation: O(n^2)
 * 1. Sort string array by their length
 * 2. Caculate their signature
 * 3. Find the maximum product among all pairs, and with smart breaks
 */

import java.util.*;

class LenComparator implements Comparator<String>{
 
    @Override
    public int compare(String o1, String o2) {
        return o2.length() - o1.length();
    }
}

public class Solution{
    public int maxProduct(String[] words) {
        int signature;
        int[] wordSig;
        int i;
        int maxProduct;
        int currProduct;
        int j;
        
        wordSig = new int[words.length];
        maxProduct = 0;
        
        Arrays.sort(words, new LenComparator());
        
        signature = 0;
        for(i = 0; i < words.length; ++i){
            signature = 0;
            for(Character c: words[i].toCharArray()){
                signature = signature | (1 <<(c - 'a'));  
            }
            wordSig[i] = signature;
        }
        
        for(i = 0; i < (words.length - 1); ++i){
            if(maxProduct >= (words[i].length() * words[i].length())){
                break;
            }
            for(j = i + 1; j < words.length; ++j){
                if((wordSig[i] & wordSig[j]) == 0){
                    currProduct = words[i].length() * words[j].length();
                    maxProduct = Math.max(currProduct, maxProduct);
                    break;
                }
            }  
        }
        return maxProduct;
    }

    public static void main(String[] args){
        Solution sol;
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        int maxProduct;

        System.out.println("words[]: " + Arrays.asList(words));
        
        sol = new Solution();    
        maxProduct = sol.maxProduct(words);

        System.out.println("maximum product: " + maxProduct);
    }
}
