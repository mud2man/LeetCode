/* Binary search + Two pointers: Time:O(logn + k), Space:O(1)
 * 1. Binary search the starting pointer, and have two pointers head and tail on its left and right side
 * 2. Compare the difference between the arr[head] and arr[tail], and chooce the closer one
 * 3. Put the numbers between head and tail into the list kClosest
 */

import java.util.*;

public class Solution{
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> kClosest = new ArrayList<Integer>();
        
        int head = 0;
        int tail = 0;
        int startIndex = Arrays.binarySearch(arr, x);
        if(startIndex >= 0 && arr[startIndex] == x){
            k--;
            head = startIndex - 1;
            tail = startIndex + 1;
        }
        else{
            startIndex = -(startIndex + 1);
            head = startIndex - 1;
            tail = startIndex;
        }

        while(k > 0){
            if(head < 0){
                tail++;
            }
            else if(tail >= arr.length){
                head--;
            }
            else if((x - arr[head]) <= (arr[tail] - x)){
                head--;
            }
            else{
                tail++;
            }
            k--;
        }
        
        for(int i = head + 1; i < tail; ++i){
            kClosest.add(arr[i]);
        }
        return kClosest;
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;
        int x = 3;
        
        System.out.println("arr: " + Arrays.toString(arr));
        System.out.println("k: " + k);
        System.out.println("x: " + x);
        List<Integer> kClosest = sol.findClosestElements(arr, k, x);
        System.out.println("kClosest: " + kClosest);
    }
}
