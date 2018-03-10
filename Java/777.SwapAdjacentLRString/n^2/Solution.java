/* Math: Time:O(n^2), Space:O(n), LeetCode has O(n) solution
 * 1. There are 6 cases which need to operate replacement
 * 2. case1: startArray[i] = 'X', endArray[i] = 'L' => find the first index of 'L' in start with ignoring 'X', and swap
 * 2. case2: startArray[i] = 'R', endArray[i] = 'L' => nothing can be done
 * 2. case3: startArray[i] = 'X', endArray[i] = 'R' => nothing can be done
 * 2. case4: startArray[i] = 'L', endArray[i] = 'R' => nothing can be done
 * 2. case5: startArray[i] = 'R', endArray[i] = 'X' => find the first index of 'X' in start with ignoring 'R', and swap
 * 2. case6: startArray[i] = 'L', endArray[i] = 'X' => nothing can be done
 */

import java.util.*;

public class Solution{
    private int getIndex(char[] array, int start, char target, char source){
        for(int i = start; i < array.length; ++i){
            if(array[i] == target){
                return i;
            }
            else if(array[i] == source){
                continue;
            }
            else{
                return -1;
            }
        }
        return -1;
    }
    
    private void swap(char[] array, int sourceIndex, int targetIndex){
        char temp = array[sourceIndex];
        array[sourceIndex] = array[targetIndex];
        array[targetIndex] = temp;
    }
    
    public boolean canTransform(String start, String end) {
        char[] startArray = start.toCharArray();
        char[] endArray = end.toCharArray();
        
        if(startArray.length != endArray.length){
            return false;
        }
        
        for(int i = 0; i < startArray.length; ++i){
            if(startArray[i] != endArray[i]){
                int targetIndex = -1;
                if(startArray[i] == 'X' && endArray[i] == 'L'){
                    targetIndex = getIndex(startArray, i + 1, 'L', 'X');
                }
                else if(startArray[i] == 'R' && endArray[i] == 'X'){
                    targetIndex = getIndex(startArray, i + 1, 'X', 'R');
                }
                else{
                    return false;
                }
                
                if(targetIndex != -1){
                    swap(startArray, i, targetIndex);
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        String start = "RXXLRXRXL";
        String end = "XRLXXRRLX";

        sol = new Solution();
        System.out.println("start: " + start);
        System.out.println("end: " + end);
        System.out.println("can transform?: " + sol.canTransform(start, end));
    }
}
