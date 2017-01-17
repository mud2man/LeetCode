/* MinHeap: O(klogk)
 * 1. Store all pairs (nums1[i], nums2[0]) in MinHeap;
 * 2. Get and remove the minimum pair p=(nums1[i], nums2[j) from the MinHeap
 * 3. Add the pair p'=(nums1[i], nums2[j+1]) to MinHeap
 * 4. Repeat step2 and step3 k times
 *
 * ex: nums1 = {1, 7, 11}, nums2 = {2, 4, 6}
 *
 * time[0]: kPairs{}, MinHeap{(1, 2), (7, 2), (11, 2)}
 * time[1]: kPairs{(1, 2)}, MinHeap{(1, 4), (7, 2), (11, 2)}
 * time[2]: kPairs{(1, 2), (1, 4)}, MinHeap{(1, 6), (7, 2), (11, 2)}
 * time[3]: kPairs{(1, 2), (1, 4), (1, 6)}, MinHeap{(7, 2), (11, 2)}
 * time[4]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2)}, MinHeap{(7, 4), (11, 2)}
 * time[5]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2), (7, 4)}, MinHeap{(11, 2),(7, 6)}
 * time[6]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2), (7, 4), (11, 2)}, MinHeap{(7, 6), (11, 4)}
 * time[7]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2), (7, 4), (11, 2), (7, 6)}, MinHeap{(11, 4)}
 * time[8]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2), (7, 4), (11, 2), (7, 6), (11, 4)}, MinHeap{(11, 6)}
 * time[9]: kPairs{(1, 2), (1, 4), (1, 6), (7, 2), (7, 4), (11, 2), (7, 6), (11, 4), (11, 6)}, MinHeap{}
 */

import java.util.*;
 
public class Solution{
    private class Pair{
        int idx1;
        int idx2;
        int sum;
        Pair(int i, int j, int k){idx1 = i; idx2 = j; sum = k;}
    }
    
    private class sumComparator implements Comparator<Pair>{
        @Override
        public int compare(Pair o1, Pair o2){
            return o1.sum - o2.sum;
        }
    }
    
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Pair> minHeap;
        List<int[]> kPairs;
        int i;
        Pair pair;
        int[] p;
        
        minHeap = new PriorityQueue<Pair>(new sumComparator());
        kPairs = new ArrayList<int[]>();
        
        for(i = 0; (i < nums1.length) && (nums2.length > 0); ++i){
            pair = new Pair(i, 0, nums1[i] + nums2[0]);
            minHeap.add(pair);
        }
        
        for(i = 0; (i < k) && (!minHeap.isEmpty()); ++i){
            pair = minHeap.poll();
            p = new int[2];
            p[0] = nums1[pair.idx1];
            p[1] = nums2[pair.idx2];
            kPairs.add(p);
            
            if(pair.idx2 != (nums2.length - 1)){
                pair.idx2 = pair.idx2 + 1;
                pair.sum = nums1[pair.idx1] + nums2[pair.idx2];
                minHeap.add(pair); 
            }
        }
        
        return kPairs;
    }

    public static void main(String[] args){
        Solution sol;
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        List<int[]> kPairs;
        int k;

        k = 3;
        sol = new Solution();
        
        System.out.println("nums1: " + Arrays.toString(nums1));
        System.out.println("nums2: " + Arrays.toString(nums2));
        System.out.println("k: " + k);
        
        kPairs = sol.kSmallestPairs(nums1, nums2, k);
        
        System.out.print("kPairs: ");
        for(int i = 0; i < k; ++i){
            System.out.print(Arrays.toString(kPairs.get(i)) + ", ");
        }
        System.out.println("");
    }
}
