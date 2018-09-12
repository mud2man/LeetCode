/* Greedy: Time:O(n)), Space:O(1)
 */

import java.util.*;

public class Solution{
    private String getLine(String[] words, int maxWidth, int left, int right){
        String line = "";
        //last line
        if(right == words.length - 1){
            int space = maxWidth;
            line = words[left];
            space -= line.length();
            for(int i = left + 1; i <= right; ++i){
                line += (" " + words[i]);
                space -= (1 + words[i].length());
            }
            for(int i = 0; i < space; ++i){
                line += " ";
            }
        }
        else{
            int wordLen = 0;
            for(int i = left; i <= right; ++i){
                wordLen += words[i].length();
            }
            line = words[left];
            int spaceLen = maxWidth - wordLen;
            if(left == right){
                for(int i = 0; i < spaceLen; ++i){
                    line += " ";
                }
            }
            else{
                int space = spaceLen / (right - left);
                int longSpaceCount = spaceLen - space * (right - left);
                int count = 0;
                for(int i = left + 1; i <= right; ++i){
                    int len = (count < longSpaceCount)? space + 1: space;
                    for(int j = 0; j < len; ++j){
                        line += " ";
                    }
                    count++;
                    line += words[i];
                }
            } 
        }
        return line;
    }
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0;
        List<String> lines = new ArrayList<>();   
        while(left < words.length){
            int count = words[left].length();
            int right = left + 1;
            while(right < words.length){
                count += (1 + words[right].length());
                if(count > maxWidth || right == words.length){
                    break;
                }
                else{
                    right++;
                }
            }
            right--;
            lines.add(getLine(words, maxWidth, left, right));
            left = right + 1;
        }
        return lines;
    }
 
    public static void main(String[] args){
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        Solution sol = new Solution();

        System.out.println("maxWidth: " + maxWidth);
        System.out.println("words:" + Arrays.toString(words));
        List<String> lines = sol.fullJustify(words, maxWidth);
        System.out.println("lines:" + lines);
    }
}
