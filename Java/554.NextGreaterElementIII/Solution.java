/* Merge sort: Time:O(n) Space:O(n), where n is the number of digit
 * 1. Have a list numberList to store all digits
 * 2. Trace from LSB until reach the swap index, where previousDigit > currentDigit
 * 3. Trace again from LSB, and swap the digits upon find a digit bigger than the swapped one
 * 4. Reverse the digits between index 0 and index swapIndex - 1
 * 5. Transfrom the list back into integer
 */

import java.util.*;

public class Solution{
    private void swap(List<Integer> numberList, int i, int j){
        int temp = numberList.get(i);
        numberList.set(i, numberList.get(j));
        numberList.set(j, temp);
    }
    
    public int nextGreaterElement(int n) {
        List<Integer> numberList = new ArrayList<Integer>();
        int previousDigit = -1;
        int swapIndex = -1;
        int index = 0;
        while(n > 0){
            int currentDigit = n % 10;
            n = n / 10;
            numberList.add(currentDigit);
            if(previousDigit > currentDigit && swapIndex == -1){
                swapIndex = index;
            }
            previousDigit = currentDigit;
            index++;
        }
        
        if(swapIndex == -1){
            return -1;
        }

        for(int i = 0; i < swapIndex; ++i){
            if(numberList.get(i) > numberList.get(swapIndex)){
                swap(numberList, i, swapIndex);
                break;
            }
        }

        int ptr0 = 0;
        int ptr1 = swapIndex -1;
        while(ptr0 < ptr1){
            swap(numberList, ptr0++, ptr1--);
        }
        
        long ret = 0;
        for(int i = numberList.size() - 1; i >= 0; --i){
            ret = ret * 10 + numberList.get(i);
        }
        return (ret > (long)Integer.MAX_VALUE)? -1: (int)ret;
    }
 
    public static void main(String[] args){
        int n = 121421;
        Solution sol = new Solution();
        System.out.println("n: " + n);
        System.out.println("next greater number " + sol.nextGreaterElement(n));
    }
}
