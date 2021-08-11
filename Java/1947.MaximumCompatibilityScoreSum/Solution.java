/* Backtrack: Time:O(n!), Space:O(n ^ 2)
 */

import java.util.*; // Stack


public class Solution {
    private void backtrack(int[] max, int student, Deque<Integer> mentorQueue, 
                           int score, int[][] studentMentorScore){
        if(mentorQueue.isEmpty()){
            max[0] = Math.max(max[0], score);
            return;
        }
        for(int i = 0; i < mentorQueue.size(); i++){
            int mentor = mentorQueue.pollFirst();
            backtrack(max, student + 1, mentorQueue, score + studentMentorScore[student][mentor], studentMentorScore);
            mentorQueue.add(mentor);
        }
    }
    
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int[][] studentMentorScore = new int[students.length][mentors.length];
        Deque<Integer> mentorQueue = new LinkedList<>();
        for(int i = 0; i < students.length; i++){
            for(int j = 0; j < mentors.length; j++){
                for(int k =0; k < students[0].length; k++){
                    studentMentorScore[i][j] += (students[i][k] == mentors[j][k])? 1: 0;
                }
            }
            mentorQueue.add(i);
        }    
        int[] max = new int[1];
        backtrack(max, 0, mentorQueue, 0, studentMentorScore);
        return max[0];
    }
  
    public static void main(String[] args){
        Solution sol = new Solution();
        int[][] students = {{1, 1, 0}, {1, 0, 1}, {0, 0, 1}};
        int[][] mentors = {{1, 0, 0}, {0, 0, 1}, {1, 1, 0}};
        System.out.print("students:");
        for(int[] row: students){
            System.out.print(Arrays.toString(row) + ",");
        }
        System.out.print("\nmentors:");
        for(int[] row: mentors){
            System.out.print(Arrays.toString(row) + ",");
        }
        System.out.println("\nmax score:" + sol.maxCompatibilitySum(students, mentors));
    }
}
