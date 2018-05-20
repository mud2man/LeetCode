/* Dynamic programming: Time:O(n^3), Space:O(n^2)
 * 1. Have a class "Node" to store encode string, decode string, encode count, shortest string 
 * 2. dp[y][x] = the node considering s.substring(x, y + 1)
 * 3. dp[y][x] = the best of optimal of dp[z][x] and dp[y][z + 1], where z is between x and y
 * 
 * ex: s = "abababd"
 * (1,a, a, a), 
 * (1,ab, ab, ab), (1,b, b, b), 
 * (1,aba, aba, aba), (1,ba, ba, ba), (1,a, a, a), 
 * (2,ab, abab, abab), (1,bab, bab, bab), (1,ab, ab, ab), (1,b, b, b), 
 * (1,ababa, ababa, ababa), (2,ba, baba, baba), (1,aba, aba, aba), (1,ba, ba, ba), (1,a, a, a), 
 * (3,ab, ababab, 3[ab]), (1,babab, babab, babab), (2,ab, abab, abab), (1,bab, bab, bab), (1,ab, ab, ab), (1,b, b, b), 
 * (1,3[ab]d, abababd, 3[ab]d), (1,bababd, bababd, bababd), (1,ababd, ababd, ababd), (1,babd, babd, babd), (1,abd, abd, abd), (1,bd, bd, bd), (1,d, d, d), 
 */         

import java.util.*;

public class Solution {
    private class Node{
        int count;
        String encode;
        String decode;
        String shortest;
        Node(int c, String e, String d, String s){count = c; encode = e; decode = d; shortest = s;}
    }
    
    public String encode(String s) {
        int length = s.length();
        Node[][] dp = new Node[length][length];
        
        //x: head, y: tail
        for(int y = 0; y < length; ++y){
            for(int x = y; x >= 0; --x){
                dp[y][x] = new Node(1, s.substring(x, y + 1), s.substring(x, y + 1), s.substring(x, y + 1));
                for(int z = x; z < y; ++z){
                    Node temp = new Node(1, "", s.substring(x, y + 1), "");
                    Node left = dp[z][x];
                    Node right = dp[y][z + 1];
                    String leftDecode = left.decode;
                    String leftEncode = (left.count > 1)? Integer.toString(left.count) + "[" + left.encode+"]": left.encode;
                    String rightDecode = right.decode;
                    String rightEncode = (right.count > 1)? Integer.toString(right.count) + "[" + right.encode + "]":right.encode;
                    if(!left.encode.equals(right.encode)){
                        String leftStr = (leftDecode.length() < leftEncode.length())? leftDecode: leftEncode;
                        String rightStr = (rightDecode.length() < rightEncode.length())? rightDecode: rightEncode;
                        temp.encode = leftStr + rightStr;
                    }
                    else{
                        temp.count = left.count + right.count;
                        temp.encode = left.encode;
                    }
                    String encodeStr = (temp.count > 1)? Integer.toString(temp.count) + "[" + temp.encode + "]": temp.encode;
                    temp.shortest = (encodeStr.length() <= temp.decode.length())? encodeStr: temp.decode;
                    
                    if(temp.shortest.length() < dp[y][x].shortest.length()){
                        dp[y][x] = temp;
                    }
                    else if(temp.shortest.length() == dp[y][x].shortest.length()){
                        if(temp.encode.length() < dp[y][x].encode.length()){
                            dp[y][x] = temp;
                        }
                    }
                }
            }
        }
        return dp[length - 1][0].shortest;
    }
 
    public static void main(String[] args){
        Solution sol; 
        String s = "abababd";
        sol = new Solution();
        System.out.println("s: " + s);
        System.out.println("encoded string: " + sol.encode(s));
    }
}
