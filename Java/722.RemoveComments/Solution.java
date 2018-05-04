/* Time:O(n), Space:O(n). Need official answer, it's to lenthy
 * 1.  
 * */

import java.util.*;

public class Solution{
    private void helper(List<String> code, boolean inComment, String[] source, int index, boolean append){
        if(index == source.length){
            return;
        }
        
        String line = source[index++];
        int length = line.length();
        int starStart = line.indexOf("/*");
        starStart = (starStart != -1)? starStart: length;
        int starEnd = (starStart < length)? line.indexOf("*/", starStart + 2): line.indexOf("*/");
        starEnd = (starEnd != -1)? starEnd: length;
        int lineStart = line.indexOf("//");
        lineStart = (lineStart != -1)? lineStart: length;

        if(line.length() == 0){
            helper(code, inComment, source, index, append);
            return;
        }
        
        if(inComment){
            starEnd = line.indexOf("*/");
            starEnd = (starEnd != -1)? starEnd: length;
            if(starEnd < length){
                index--;
                line = line.substring(starEnd + 2);
                source[index] = line;
                if(starEnd == length - 2){
                    helper(code, false, source, index, false);
                }
                else{
                    helper(code, false, source, index, append);
                }
            }
            else{
                helper(code, true, source, index, append);
            }
        }
        else{
            if(starStart < length || lineStart < length){
                if(starStart < lineStart){
                    if(starEnd < length){
                        line = line.substring(0, starStart) + line.substring(starEnd + 2);
                        index--;
                        source[index] = line;
                        helper(code, false, source, index, false);
                    }
                    else{
                        line = line.substring(0, starStart);
                        if(line.length() > 0){
                            code.add(line);
                        }
                        
                        if(starStart > 0){
                            helper(code, true, source, index, true);
                        }
                        else{
                            helper(code, true, source, index, false);
                        }
                    }
                }
                else{
                    line = line.substring(0, lineStart);
                    if(line.length() > 0){
                        if(append){
                            String lastLine = code.get(code.size() - 1);
                            code.set(code.size() - 1, lastLine + line);
                        }
                        else{
                            code.add(line);
                        }
                    }
                    helper(code, false, source, index, false);
                }
            }
            else{
                if(line.length() > 0){
                    if(append){
                        String lastLine = code.get(code.size() - 1);
                        code.set(code.size() - 1, lastLine + line);
                    }
                    else{
                        code.add(line);
                    }
                }
                helper(code, false, source, index, false);
            }
        }
    }
    
    public List<String> removeComments(String[] source) {
        List<String> code = new ArrayList<String>();
        helper(code, false, source, 0, false);
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
