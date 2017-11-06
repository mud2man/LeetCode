/* Hash: O(1)
 * 1. Use encodeMap and decodeMap to track both directions
 */

import java.util.*;
class Main {
    HashMap<String, String> map;
    long currentIndex;
    
    public Main(){
        map = new HashMap<String, String>();  
        currentIndex = 0;
    }
    
    private char digit10ToDigit62(long digit10){
        if(digit10 < 10){
            return (char)((long)'0' + digit10);
        }
        else if(digit10 < 36){
            digit10 -= 10;
            return (char)((long)'a' + digit10);
        }
        else{
            digit10 -= 36;
            return (char)((long)'A' + digit10);
        }
    }
    
    private String base10ToBase62(long currentIndex){
        StringBuilder number62 = new StringBuilder("");
        for(int i = 0; i < 6; ++i){
            long digit10 = currentIndex % 62;
            number62.insert(0, digit10ToDigit62(digit10));
            currentIndex = currentIndex / 62;
        }
        return number62.toString();
    }
    
    public String getShortURL(String longURL){
        String prefix = "http://tinyurl.com/";
        
        if(map.containsKey(longURL)){
            return prefix + map.get(longURL);
        }
        else{
            String postfix = base10ToBase62(currentIndex++);
            map.put(longURL, postfix);
            return prefix + postfix;
        }
    }
    
    public static void main(String[] args) {
        Main sol = new Main();
        String longURL = "https://leetcode.com/problems/design-tinyurl";
        
        for(int i = 0; i < 1000; ++i){
            StringBuilder version = new StringBuilder("");
            version.append(i);
            System.out.println("long URL:" + longURL + version.toString());
            System.out.println("short URL:" + sol.getShortURL(longURL + version.toString()));
        }
    }
}
