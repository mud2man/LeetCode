/* Map: Time:O(n * m * l), Space:O(m * l). n = text length, m = number of marks, l = key's longest length
 * 1. Scan text and match the sub string to see if we match the mark
 */          

import java.util.*; 
public class Solution {
    public String entityParser(String text) {
        Map<String, String> dic = new HashMap<>();
        dic.put("&quot;", "\"");
        dic.put("&apos;", "'");
        dic.put("&amp;", "&");
        dic.put("&gt;", ">");
        dic.put("&lt;", "<");
        dic.put("&frasl;", "/");
        StringBuilder sb = new StringBuilder("");
        int idx = 0;
        while(idx < text.length()){
            boolean hit = false;
            for(String mark: dic.keySet()){
                if(text.startsWith(mark, idx)){
                    sb.append(dic.get(mark));
                    idx += mark.length();
                    hit = true;
                    break;
                };
            }
            if(!hit){
                sb.append(text.charAt(idx++));
            }
        }
        return sb.toString();
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        String text = "&amp; is an HTML entity but &ambassador; is not.";
        System.out.println("text:" + text);
        System.out.println("output:" + sol.entityParser(text));
    }
}
