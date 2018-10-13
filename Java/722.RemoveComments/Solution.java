/* Time:O(n), Space:O(n). Need official answer, it's to lenthy
 * 1. Scan the line every two chars, and have a "state" to indicate if during comment "/*"
 * 2. Handle the case given state
 * */

import java.util.*;

public class Solution{
    public List<String> removeComments(String[] source) {
        int state = 0; //0:normal, 1:during comment
        List<String> code = new ArrayList<>();
        boolean append = false;
        
        for(String line: source){
            StringBuilder sb = new StringBuilder("");
            int i = 0;
            while(i < line.length()){
                StringBuilder token = new StringBuilder("");
                token.append(line.charAt(i));
                if(i + 1 < line.length()){
                    token.append(line.charAt(i + 1));
                }
                
                if(state == 0){
                    if(token.toString().equals("/*")){
                        state = 1;
                        i += 2;
                        append = (sb.length() > 0)? true: false;
                    }
                    else if(token.toString().equals("//")){
                        break;
                    }
                    else{
                        sb.append(line.charAt(i));
                        ++i;
                    }
                }
                else{
                    if(token.toString().equals("*/")){
                        //append only if current line "sb" is empty
                        if(append && code.size() > 0 && sb.length() == 0){
                            sb.append(code.get(code.size() - 1));
                            code.remove(code.size() - 1);
                        }
                        append = false;
                        state = 0;
                        i += 2;
                    }
                    else{
                        ++i;
                    }
                }
            }
                                            
            if(sb.toString().length() > 0){
                code.add(sb.toString());
            }
        }
        return code;
    }

    public static void main(String[] args){
        Solution sol;
        String[] source = {"a/*comment", "line", "more_comment*/b"};
        sol = new Solution();
        System.out.println("source: " + Arrays.toString(source));
        System.out.println("code: " + sol.removeComments(source));
    }
}
