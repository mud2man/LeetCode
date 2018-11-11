/* Time:O(n), Space:O(n). Need official answer, it's to lenthy
 * 1. Scan the line every two chars, and have a "inBlock" to indicate if during comment "/*"
 * 2. Handle the case given state
 */

import java.util.*;

public class Solution{
   public List<String> removeComments(String[] source) {
        List<String> codes = new ArrayList<>();
        boolean inBlock = false;
        StringBuilder code = new StringBuilder("");
        for(String line: source){
            char[] array = line.toCharArray();
            for(int i = 0; i < line.length(); ++i){
                if(!inBlock && i + 1 < line.length() && array[i] == '/' && array[i + 1] == '*'){
                    inBlock = true;
                    ++i;
                }
                else if(inBlock && i + 1 < line.length() && array[i] == '*' && array[i + 1] == '/'){
                    inBlock = false;
                    ++i;
                }
                else if(!inBlock && i + 1 < line.length() && array[i] == '/' && array[i + 1] == '/'){
                    break;
                }
                else if(!inBlock){
                    code.append(array[i]);
                }
            }
            
            if(!inBlock && code.length() > 0){
                codes.add(code.toString());
                code = new StringBuilder("");
            }
        }
        return codes;
    }
 
    public static void main(String[] args){
        Solution sol;
        String[] source = {"a/*comment", "line", "more_comment*/b"};
        sol = new Solution();
        System.out.println("source: " + Arrays.toString(source));
        System.out.println("code: " + sol.removeComments(source));
    }
}
