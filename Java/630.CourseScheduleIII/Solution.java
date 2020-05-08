/* Greedy: Time:O(nlogn), Space:(n)
 * 1. Sort courses by closed day course[1]
 * 2. We concatenate all selected course intervals which they are before the i-th course's closed day
 * 3. If i-th course canot put into heap, we see if the longest interval is longer than i-th course.
 * 4. If so, we replace it, since it can reduce total length while keep the same number of course
 */

import java.util.*;
import java.util.stream.*;

public class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (x, y) -> (x[1] - y[1]));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> (y - x));
        int end = 0;
        for(int[] course: courses){
            if(course[1] - end >= course[0]){
                maxHeap.add(course[0]);
                end += course[0];
            }else if(!maxHeap.isEmpty() && maxHeap.peek() > course[0]){
                int removeInterval = maxHeap.poll();
                end -= removeInterval;
                maxHeap.add(course[0]);
                end += course[0];
            }
        }
        return maxHeap.size();
    } 

    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] courses = {{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};
        System.out.println("courses:");
        for(int[] course: courses){
            System.out.println(Arrays.toString(course));
        }
        System.out.println("maximum unconflicted courses:" + sol.scheduleCourse(courses));
    }
}
