/* Bit Manupulation: Time:O(n), Space:O(1)
 */         

import java.util.*;

public class Solution {
    private boolean validate(int[] data, int start, int type){
        start++;
        for(int i = 0; i < type; ++i){
            if(start == data.length || (data[start++] & 0xC0) != 0x80){
                return false;
            }
        }
        return true;
    }
    
    public boolean validUtf8(int[] data) {
        int index = 0;
        
        while(index < data.length){
            int header = data[index];
            if((header & 0x80) == 0x0){
                if(!validate(data, index, 0)){
                    return false;
                }
                index += 1;
            }
            else if((header & 0xE0) == 0xC0){
                if(!validate(data, index, 1)){
                    return false;
                }
                index += 2;
            }
            else if((header & 0xF0) == 0xE0){
                if(!validate(data, index, 2)){
                    return false;
                }
                index += 3;
            }
            else if((header & 0xF8) == 0xF0){
                if(!validate(data, index, 3)){
                    return false;
                }
                index += 4;
            }
            else{
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args){
        Solution sol;
        int[] data = {197, 130, 1};
        sol = new Solution();

        System.out.println("data: " + Arrays.toString(data));
        System.out.println("is valid Utf-8: " + sol.validUtf8(data));
    }
}
