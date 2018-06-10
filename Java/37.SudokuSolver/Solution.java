/* Backtrace: Time:O(9^n), where n is the number of blank. However, leetcode has a shorter answer
 * 1. Have candidate sets for every row, column, and block
 * 2. Have a list of blank node, and link the associated candidate sets to the node
 * 3. Use "backtrack" method to visit the list
 * 4. During backtrack, select the intersection of the three candidates set, and traverse the intersection
 * 5. Remove the intersect candidate from the three candidates set, and put it back when the recursive backtrack return
 */

import java.util.*;


//Definition for singly-linked list.
public class Solution{
    private class Node{
        int[] pos;
        Set<Character> rowCandidate;
        Set<Character> colCandidate;
        Set<Character> blkCandidate;
        char c; 
        Node(int[] p, Set<Character> row, Set<Character> col, Set<Character> blk){
            pos = p; 
            rowCandidate = row; 
            colCandidate = col; 
            blkCandidate = blk;
            c = '.';
        }
    }
    
    private void reverse(Set<Character> candidate){
        Set<Character> newSet = new HashSet<Character>();
        char base = '1';
        for(int i = 0; i < 9; ++i){
            if(!candidate.contains((char)((int)base + i))){
                newSet.add((char)((int)base + i));
            }   
        }
        candidate.clear();
        candidate.addAll(newSet);
    }
    
    private boolean backtrack(List<Node> list, int index){
        if(index == list.size()){
            return true;
        }
        
        List<Character> intersect = new ArrayList<Character>();
        for(Character candidate: list.get(index).rowCandidate){
            if(list.get(index).colCandidate.contains(candidate) && list.get(index).blkCandidate.contains(candidate)){
                intersect.add(candidate);
            }
        }
        
        for(Character candidate: intersect){
            list.get(index).rowCandidate.remove(candidate);
            list.get(index).colCandidate.remove(candidate);
            list.get(index).blkCandidate.remove(candidate);
            list.get(index).c = candidate;
            if(backtrack(list, index + 1)){
                return true; 
            }
            list.get(index).rowCandidate.add(candidate);
            list.get(index).colCandidate.add(candidate);
            list.get(index).blkCandidate.add(candidate);
        }
        return false;
    }
    
    public void solveSudoku(char[][] board) {
        List<Set<Character>> rowCandidates = new ArrayList<Set<Character>>();
        List<Set<Character>> colCandidates = new ArrayList<Set<Character>>();
        List<Set<Character>> blkCandidates = new ArrayList<Set<Character>>();
        
        for(int i = 0; i < 9; ++i){
            rowCandidates.add(new HashSet<Character>());
            colCandidates.add(new HashSet<Character>());
            blkCandidates.add(new HashSet<Character>());
        }
        
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                char c = board[y][x];
                if(c != '.'){
                    rowCandidates.get(y).add(c);
                    colCandidates.get(x).add(c);
                    blkCandidates.get((y / 3) * 3 + (x / 3)).add(c);
                }
            }
        }
         
        for(int i = 0; i < 9; ++i){
            reverse(rowCandidates.get(i));
            reverse(colCandidates.get(i));
            reverse(blkCandidates.get(i));
        }
        
        //build grapgh
        List<Node> list = new ArrayList<Node>();
        for(int y = 0; y < board.length; ++y){
            for(int x = 0; x < board[0].length; ++x){
                char c = board[y][x];
                if(c == '.'){
                    list.add(new Node(new int[]{y, x}, rowCandidates.get(y), colCandidates.get(x), 
                                      blkCandidates.get((y / 3) * 3 + (x / 3))));
                }
            }
        }
        
        backtrack(list, 0);
        
        for(Node node: list){
            board[node.pos[0]][node.pos[1]] = node.c;
        }
    }

    public static void main(String[] args){
        Solution sol = new Solution();
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},
                          {'6','.','.','1','9','5','.','.','.'},
                          {'.','9','8','.','.','.','.','6','.'},
                          {'8','.','.','.','6','.','.','.','3'},
                          {'4','.','.','8','.','3','.','.','1'},
                          {'7','.','.','.','2','.','.','.','6'},
                          {'.','6','.','.','.','.','2','8','.'},
                          {'.','.','.','4','1','9','.','.','5'},
                          {'.','.','.','.','8','.','.','7','9'}};
 
        System.out.println("before: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }

        sol.solveSudoku(board);

        System.out.println("after: ");
        for(char[] row: board){
            System.out.println(Arrays.toString(row));
        }
    }
}
