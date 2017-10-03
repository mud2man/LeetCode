/* Hash: O(1)
 * 1. Use encodeMap and decodeMap to track both directions
 */

import java.util.*;

public class Codec{
    HashMap<String, String> encodeMap = new HashMap<String, String>();;
    HashMap<String, String> decodeMap = new HashMap<String, String>();;
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(encodeMap.containsKey(longUrl)){
            return encodeMap.get(longUrl);
        }
        else{
            String shortUrl = "http://tinyurl.com/";
            shortUrl = shortUrl + Integer.toString(encodeMap.size());
            encodeMap.put(longUrl, shortUrl);
            decodeMap.put(shortUrl, longUrl);
            return shortUrl;
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return decodeMap.get(shortUrl);
    }

    public static void main(String[] args){
        Codec sol;
        String longUral = "https://leetcode.com/problems/design-tinyurl";

        sol = new Codec();
        System.out.println("longUral: " + longUral);
        System.out.println("after encode: " + sol.encode(longUral));
        System.out.println("after decode: " + sol.decode(sol.encode(longUral)));
    }
}
