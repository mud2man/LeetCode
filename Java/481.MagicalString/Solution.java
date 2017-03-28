/* O(n)
 * 1. Create two pointers. ptr0 is pointed to the last index of creater
 * 2. The other one ptr1 is pointed to the last index of createe
 * 3. Loop until ptr1 is larger than n
 *
 * EX: n = 4 
 * time[0]: ptr0 = 0, ptr1 = 0, magicStr = [1, 2] 
 * time[1]: ptr0 = 1, ptr1 = 2, magicStr = [1, 2, 2] 
 * time[2]: ptr0 = 2, ptr1 = 4, magicStr = [1, 2, 2, 1, 1] 
 */

import java.util.*; // Stack

public class Solution {
    public int magicalString(int n) {
        int[] magicStr;
        int count;
        int i;
        int repeatNum;
        /* 1: current element is 1; -1: current element is 2 */
        int flag;
        /* the last index of creater */
        int ptr0;
        /* the last index of createe */
        int ptr1;
        
        if(n == 0){
            return 0;
        }
        
        magicStr = new int[n + 2];
        magicStr[0] = 1;
        magicStr[1] = 2;
        
        ptr0 = 0;
        ptr1 = 0;
        flag = -1;
        count = 1;
        repeatNum = 0;
        
        while(ptr1 < (n - 1)){
            ptr0++;
            repeatNum = magicStr[ptr0];
            if(repeatNum == 1){
                if(flag == 1){
                    count++;
                }
            }
            else{
                if(flag == 1){
                    count = count + 2;
                }
            }
            for(i = 1; i <= repeatNum; ++i){
                magicStr[i + ptr1] = (flag == 1)? 1 : 2;
            }
            ptr1 += repeatNum;
            flag = -flag;
        }

        if(repeatNum == 2 && flag == -1 && ptr1 > (n - 1)){
                count--;
        }
        return count;
    }
 
    public static void main(String[] args){
        Solution sol;
        int n ; 

        sol = new Solution();
        n = 4;
        
        System.out.println("The number length magic string: " + n);
        System.out.println("The number of 1s in magic string: " + sol.magicalString(n));
    }
}
