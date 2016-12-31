/* Dynamic programming: O(n^2*logn)
 * 1. Devide as two list: positive numbers and negative numbers
 * 2. Sort two list
 * 3. Select one positive, and select one negatives using binary search
 * 4. Select one positive, and select two negatives using binary search
 * 5. Select one negative, and select two positives using binary search
 */

import java.util.*;

public class Solution{
    public boolean binarySearch(List<Integer> Nums, int target ,int start, int end){
        int middle;
        
        while(start <= end){
            middle = (start + end) / 2;
            
            if(Nums.get(middle) == target){
                return true;
            }
            else if(Nums.get(middle) > target){
                end = middle - 1;
            }
            else{
                start = middle + 1;
            }
        }
        
        return false;
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> posNums;
        List<Integer> negNums;
        List<Integer> triplet;
        List<List<Integer>> triplets;
        int zroeCount;
        int x, y, z, w;
        int i, j;
        
        triplets = new ArrayList<List<Integer>>();
        posNums = new ArrayList<Integer>();
        negNums = new ArrayList<Integer>();
        zroeCount = 0;
        
        for(int num: nums){
            if(num == 0){
                zroeCount++; 
            }
            else if(num > 0){
                posNums.add(num);  
            }
            else{
                negNums.add(num);
            }
        }
        
        Collections.sort(posNums);
        Collections.sort(negNums);
        
        if(zroeCount >= 3){
            triplet = new ArrayList<Integer>(3);
            triplet.add(0);
            triplet.add(0);
            triplet.add(0);
            triplets.add(triplet);
        }
        
        if(zroeCount > 0){
            x = 0;
            for(i = 0; i < negNums.size(); ++i){
                y = negNums.get(i);
                if(x == y){
                    continue;
                }   
                else{
                    x = y;
                    if(binarySearch(posNums, 0 - y ,0, posNums.size() - 1)){
                        triplet = new ArrayList<Integer>(3);
                        triplet.add(y);
                        triplet.add(0);
                        triplet.add(0 - y);
                        triplets.add(triplet);
                    }
                }
            }
        }
        
        //two positive
        x = 0;
        for(i = 0; i < negNums.size(); ++i){
            y = negNums.get(i);
            if(x == y){
                continue;
            }
            else{
                z = 0;
                for(j = 0; j < posNums.size(); ++j){
                    w = posNums.get(j);
                    if(z == w){
                        continue;
                    }
                    else{
                        if((w + y < 0) && binarySearch(posNums, 0 - (w + y), j + 1, posNums.size() - 1)){
                            triplet = new ArrayList<Integer>(3);
                            triplet.add(y);
                            triplet.add(w);
                            triplet.add(0 - (w + y));
                            triplets.add(triplet);  
                        }
                    }
                    z = w;
                }
            }
            x = y;
        }
        
        //two negative
        x = 0;
        for(i = 0; i < posNums.size(); ++i){
            y = posNums.get(i);
            if(x == y){
                continue;
            }
            else{
                z = 0;
                for(j = 0; j < negNums.size(); ++j){
                    w = negNums.get(j);
                    if(z == w){
                        continue;
                    }
                    else{
                        if((w + y > 0) && binarySearch(negNums, 0 - (w + y), j + 1, negNums.size() - 1)){
                            triplet = new ArrayList<Integer>(3);
                            triplet.add(y);
                            triplet.add(w);
                            triplet.add(0 - (w + y));
                            triplets.add(triplet);  
                        }
                    }
                    z = w;
                }
            }
            x = y;
        }
        
        return triplets;
    } 

    public static void main(String[] args){
		Solution sol;
		List<List<Integer>> triplets;
		int[] nums = {-1,0,1,2,-1,4};
		
		sol = new Solution();
		
        System.out.println("nums[]: " + Arrays.toString(nums));
		triplets = sol.threeSum(nums);
		
		System.out.println("triplets[][]: ");
		for(List<Integer> triplet: triplets){
			System.out.println(triplet);
		}
	}
}
