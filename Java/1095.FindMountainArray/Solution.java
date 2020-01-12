/* BinarySearch: Time:O(logn), Space:O(1)
 * 1. Use binary search to find the index of mountain top "topIdx"
 * 2. Try to binary search the "target" from left slope [0,..., topIdx]
 * 3. If fail, binary search the "target" from right slope [topIdx,..., length - 1]
 */

import java.util.*;


public class Solution{
    interface MountainArray {
        public int get(int index);
        public int length();
    }

    private int findTopIdx(MountainArray mountainArr){
        int start = 0;
        int end = mountainArr.length() - 1;
        while(start <= end){
            int mid = (start + end) / 2;
            int midValue = mountainArr.get(mid);
            int leftValue = (mid > 0)? mountainArr.get(mid - 1): Integer.MIN_VALUE;
            int rightValue = (mid + 1 < mountainArr.length())? mountainArr.get(mid + 1): Integer.MIN_VALUE;
            if(midValue >= leftValue && midValue >= rightValue){
                return mid;
            }else if(rightValue > midValue){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return -1;
    }
    
    private int binarySearch(int start, int end, MountainArray mountainArr, int target, boolean isAccending){
        while(start <= end){
            int mid = (start + end) / 2;
            int midValue = mountainArr.get(mid);
            if(midValue == target){
                return mid;
            }else{
                if((isAccending && midValue > target) || (!isAccending && midValue < target)){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
    
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int topIdx = findTopIdx(mountainArr);
        int idx = binarySearch(0, topIdx, mountainArr, target, true);
        return (idx != -1)? idx: binarySearch(topIdx, mountainArr.length() - 1, mountainArr, target, false);
    }
}
