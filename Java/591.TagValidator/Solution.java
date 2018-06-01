/* Stack: Time:O(n), Space:O(n), However, LeetCode has shorter solution
 * 1. Use stack to store the start tag name
 * 2. Use state machine to parse, wher mode includes 0:tag name start, 1:tag name end, 2:tag_content, 3:cdata content
 * 3. Push tag into stack in mode 0, pop stack in mode 1
 */

import java.util.*;

public class Solution{
    private boolean isWrapTag(String code){
        int startIdx = code.indexOf('>');
        int endIdx = code.lastIndexOf("</") ;
        if(startIdx == -1 || endIdx == -1){
            return false;
        }
        String startTag = code.substring(1, startIdx);
        String endTag = code.substring(endIdx + 2, code.length() - 1);
        for(int i = 0; i < startTag.length(); ++i){
            if(!Character.isUpperCase(startTag.charAt(i)) || !Character.isUpperCase(endTag.charAt(i))){
                return false;
            }
        }
        return (startTag.length() >= 1 && startTag.length() <= 9 && startTag.equals(endTag));
    }
    
    public boolean isValid(String code) {
        if(code == null || code.length() < 1 || code.charAt(0) != '<' || code.charAt(code.length() - 1) != '>'){
            return false;
        }
        
        int idx = 0;
        Stack<String> stack = new Stack<String>();
        int mode = 2; //0:tag name start, 1:tag name end, 2:tag_content, 3:cdata content
        String tagEnd = "</";
        String cdataStart = "<![CDATA["; 
        String cdataEnd = "]]>";
            
        if(!isWrapTag(code)){
            return false;
        }
        code = code.substring(code.indexOf('>') + 1, code.lastIndexOf("</"));
        while(idx < code.length()){
            switch (mode){
                case 0:
                    if(code.charAt(idx++) != '<'){
                        return false;
                    }
                    StringBuilder tagName = new StringBuilder("");
                    while(idx < code.length() && code.charAt(idx) != '>'){
                        if(!Character.isUpperCase(code.charAt(idx))){
                            return false;
                        }
                        else{
                            tagName.append(code.charAt(idx++));
                        }
                    }
                    if(tagName.length() < 1 || tagName.length() > 9 || idx == code.length()){
                        return false;
                    }
                    else{
                        stack.push(tagName.toString());
                    }
                    idx++;
                    mode = 2;
                    break;
                    
                case 1:
                    tagName = new StringBuilder("");
                    while(idx < code.length() && code.charAt(idx) != '>'){
                        if(!Character.isUpperCase(code.charAt(idx))){
                            return false;
                        }
                        else{
                            tagName.append(code.charAt(idx++));
                        }
                    }
                    if(tagName.length() < 1 || tagName.length() > 9 || idx == code.length()){
                        return false;
                    }
                    else{
                        if(stack.isEmpty() || !stack.pop().equals(tagName.toString())){
                            return false;
                        }
                    }
                    idx++;
                    mode = 2;
                    break;
                    
                case 2:
                    if(code.startsWith(tagEnd, idx)){
                        mode = 1;
                        idx += 2;
                    }
                    else if(code.startsWith(cdataStart, idx)){
                        mode = 3;
                        idx += cdataStart.length();
                    }
                    else if(code.charAt(idx) == '<'){
                        mode = 0;
                    }
                    else{
                        idx++;
                    }
                    break;
                    
                case 3:
                    while(idx < code.length() && !code.startsWith(cdataEnd, idx)){
                        idx++;
                    }
                    
                    if(idx < code.length()){
                        idx += cdataEnd.length();
                        mode = 2;
                    }
                    else{
                        return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        System.out.println("code:" + code);
        System.out.println("is valid ? " + sol.isValid(code));
    }
}
