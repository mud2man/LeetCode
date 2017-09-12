/* Stack: O(n), where n = string length
 * 1. Use stack to record the path
 */

import java.util.*;

public class Solution {
    private int getDepth(String fileAndFolder){
        int depth = 0;
        int ptr = 0;
        while(fileAndFolder.charAt(ptr) == '\t'){
            ptr = ptr + 1;
            depth++;
        }
        return depth;
    }
    
    private boolean isFolder(String fileAndFolder){
        int dotPtr =  fileAndFolder.indexOf('.');
        return (dotPtr == -1);
    }
              
    public int lengthLongestPath(String input) {
        String[] filesAndFolders = input.split("\n");
        int currentlength = 0;
        int maxLength = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        
        for(String fileAndFolder: filesAndFolders){
            int depth = getDepth(fileAndFolder);
            while(stack.size() > depth){
                currentlength = currentlength - stack.getLast();
                stack.pollLast();
            }
            
            int fileLength = fileAndFolder.length() - depth;
            currentlength = currentlength + fileLength;
            if(isFolder(fileAndFolder)){
                currentlength++;
                stack.add(fileLength + 1);
            }
            else{
                maxLength = Math.max(maxLength, currentlength);
                stack.add(fileLength);
            }
        }
            
        return maxLength;
    }

    public static void main(String[] args){
        Solution sol;
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" ;

        sol = new Solution();
        System.out.println("input: " + input);
        System.out.println("lengthLongestPath: " + sol.lengthLongestPath(input));
    }
}
