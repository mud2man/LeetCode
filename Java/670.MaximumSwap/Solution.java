/* Bucket sort: Time:O(n), Space: O(1)
 * 1. Bucket sort number with all digits, e.g., buckets[i] = the numebr of digit = i
 * 2. Traverse from MSB, and find the digit which is not the biggest so far, tegn reset it with the biggest one "bucketIndex"
 * 3. Remember the swapped digit from the previous step
 * 4. Traverse from LSB, and reset it("swapSource") with "swapTarget"
 */         

import java.util.*;

public class Solution {
    public int maximumSwap(int num) {
        LinkedList<Integer> numberList = new LinkedList<Integer>();
        int[] buckets = new int[10];
        
        while(num > 0){
            int digit = num % 10;
            buckets[digit]++;
            numberList.addFirst(digit);
            num = num / 10;
        }
        
        int swapSource = -1;
        int swapTarget = -1;
        int bucketIndex = 9;
        for(int i = 0; i < numberList.size(); ++i){
            while(buckets[bucketIndex] == 0){
                bucketIndex--;
            }
            buckets[bucketIndex]--;
            
            if(bucketIndex != numberList.get(i)){
                swapSource = bucketIndex;
                swapTarget = numberList.get(i);
                numberList.set(i, swapSource);
                break;
            }
        }
        
        for(int i = numberList.size() - 1; i >= 0; --i){
            if(numberList.get(i) == swapSource){
                numberList.set(i, swapTarget);
                break;
            }
        }
        
        int swappedNum = 0;
        for(int i = 0; i < numberList.size(); ++i){
            swappedNum = swappedNum * 10 + numberList.get(i);
        }
        
        return swappedNum;
    }
 
    public static void main(String[] args){
        Solution sol;
        int num = 2736;
        
        sol = new Solution();
        System.out.println("num before swap: " + num);
        System.out.println("nums after swap: " + sol.maximumSwap(num));
    }
}
