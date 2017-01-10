/* O(n), where n = the number of characters, but optimal solution is on leetcode
 * 1. Use "#" to insert in between every character
 * 2. Use "!##!" to append every string
 */

import java.util.*; // Stack

/* Definition for binary tree */

public class Codec {
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder encodeStr;
        int i;
        
        encodeStr = new StringBuilder("");
        for(String str: strs){
            encodeStr.append("!##!");
            for(i = 0; i < (str.length() - 1); ++i) {
                encodeStr.append(str.charAt(i) + "!");  
            }
            //tail handling
            if(i < str.length()){
               encodeStr.append(str.charAt(i)); 
            }
        }
        return encodeStr.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> decodeStrs;
        String[] strs;
        String decodeStr;
        StringBuilder sb;
        int headIdx;
        int tailIdx;
        int i;
        
        decodeStrs = new ArrayList<String>();
        
        if((s == null) || (s.length() < 4 )){
            return decodeStrs;
        }
        
        headIdx = 4;
        tailIdx = 0;
        while(tailIdx >= 0){
            tailIdx = s.indexOf("!##!", headIdx);
            if(tailIdx >= 0){
                decodeStr = s.substring(headIdx, tailIdx);
                headIdx = tailIdx + 4;
            }
            else{
                decodeStr = s.substring(headIdx);
            }
            
            sb = new StringBuilder("");
            for(i = 0; i < decodeStr.length(); i+=2){
                sb.append(decodeStr.charAt(i)); 
            }
            decodeStrs.add(sb.toString());
        }
        
        return decodeStrs;
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
