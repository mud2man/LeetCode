/* Bit Manupulation: Time:O(n), Space:O(1)
 */         

import java.util.*;

public class Solution {
    private boolean validate(int[] data, int start, int type){
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
            int header = data[index++];
            int payloadLen;
            if((header & 0x80) == 0x0){
                payloadLen = 0;
            }
            else if((header & 0xE0) == 0xC0){
                payloadLen = 1;
            }
            else if((header & 0xF0) == 0xE0){
                payloadLen = 2;
            }
            else if((header & 0xF8) == 0xF0){
                payloadLen = 3;
            }
            else{
                return false;
            }
            
            if(!validate(data, index, payloadLen)){
                return false;
            }
            index += payloadLen;
        }
        return true;
    }

    public static void main(String[] args){
        int[] data = {197, 130, 1};
        Solution sol = new Solution();
        System.out.println("data: " + Arrays.toString(data));
        System.out.println("is valid Utf-8: " + sol.validUtf8(data));
    }
}
