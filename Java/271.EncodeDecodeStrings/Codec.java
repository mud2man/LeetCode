/* O(n)
 * 1. Encode with the format (str0.length() + "." + str0) + (str1.length() + "." + str1) + ....
 */

import java.util.*; // Stack

/* Definition for binary tree */

public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        String encodedString = "";
        for(String str: strs){
            encodedString += Integer.toString(str.length()) + "." + str;
        }
        return encodedString;
    }

    // Decodes a single string to a list of stringsc List<String> decode(String s) {
    public List<String> decode(String s) {
        List<String> strs = new ArrayList<String>();
        int startIdx = 0;
        
        while(startIdx < s.length()){
            int idx = s.indexOf('.', startIdx);
            int length = Integer.parseInt(s.substring(startIdx, idx));
            strs.add(s.substring(idx + 1, idx + 1 + length));
            startIdx = idx + 1 + length;
        }
        
        return strs;
    }
 
    public static void main(String[] args)
    {
        Codec codec;
        List<String> strs;    
        String encodeStr;
        
        strs = new ArrayList<String>();    
        strs.add("0123");
        strs.add("01#!");
        strs.add("!##!");
        codec = new Codec();
        
        System.out.println("before encode: ");
        for(String str: strs){
            System.out.println(str);
        }

        encodeStr = codec.encode(strs);    
        System.out.println("after encode: " + encodeStr);
        
        System.out.println("after decode: ");
        strs = codec.decode(encodeStr);
        for(String str: strs){
            System.out.println(str);
        }
    }
}
