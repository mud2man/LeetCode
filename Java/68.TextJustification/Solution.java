/* Greedy: Time:O(n)), Space:O(1)
 */

import java.util.*;

public class Solution{
    private void addLine(String[] words, int startIdx, int endIdx, List<String> lines, int maxWidth, boolean noExtarSpace){
        int charCount = 0;
        int wordCount = endIdx - startIdx + 1;
        String line = words[startIdx];
        if(noExtarSpace || wordCount == 1){
            for(int i = startIdx + 1; i <= endIdx; ++i){
                line = line + " " + words[i];
            }
            for(int i = line.length(); i < maxWidth; ++i){
                line += " ";
            }
            lines.add(line);
            return;
        }
        
        for(int i = startIdx; i <= endIdx; ++i){
            charCount += words[i].length();
        }
        
        int spaceCount = wordCount - 1;
        int totalSpaceLength = maxWidth - charCount;
        int shortSpaceLength = totalSpaceLength / spaceCount;
        int longSpaceCount = totalSpaceLength - shortSpaceLength * spaceCount;
        String shortSpace = "";
        for(int i = 0; i < shortSpaceLength; ++i){
            shortSpace += " ";
        }

        for(int i = 0; i < longSpaceCount; ++i){
            line = line + shortSpace + " " + words[++startIdx];
        }
        
        for(int i = 0; i < (spaceCount - longSpaceCount); ++i){
            line = line + shortSpace + words[++startIdx];
        }
        lines.add(line);
    }
    
    public List<String> fullJustify(String[] words, int maxWidth) {
        int startIdx = 0;
        int endIdx = 0;
        List<String> lines = new ArrayList<String>();
        
        while(endIdx < words.length){
            int charCount = words[startIdx].length();
            while(charCount < maxWidth && endIdx < (words.length - 1)){
                charCount = charCount + words[++endIdx].length() + 1;
            }
            
            if(charCount > maxWidth){
                charCount = charCount - words[endIdx].length() - 1;
                addLine(words, startIdx, endIdx - 1, lines, maxWidth, (charCount == maxWidth));
                startIdx = endIdx;
            }
            else{
                addLine(words, startIdx, endIdx, lines, maxWidth, true);
                startIdx = ++endIdx;
            }
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
