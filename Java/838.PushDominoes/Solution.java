/* BFS: Time:O(n), Space:O(n)
 * 1. Have a array with object Node to record the direction
 * 2. Put the node inclined to left to lQueue, and the one inclined to right to rQueue
 * 3. If node.toL == true, and node.toR == true. Keep the state to '.'
 * 4. Otherwise, change the state to left or right and add it to lQueue or rQueue
 */         

import java.util.*;

public class Solution {
    private class Node{
        int idx;
        boolean toL;
        boolean toR;
        Node(int i, boolean l, boolean r){idx = i; toL = l; toR = r;}
    }
    
    public String pushDominoes(String dominoes) {
        StringBuilder state = new StringBuilder(dominoes);
        Node[] array = new Node[dominoes.length()];
        LinkedList<Node> lQueue = new LinkedList<Node>();
        LinkedList<Node> rQueue = new LinkedList<Node>();
        for(int i = 0; i < state.length(); ++i){
            char c = state.charAt(i);
            if(c == 'L'){
                array[i] = new Node(i, true, false);
                lQueue.add(array[i]);
            }
            else if(c == 'R'){
                array[i] = new Node(i, false, true);
                rQueue.add(array[i]);
            }
            else{
                array[i] = new Node(i, false, false);
            }
        }
        
        //BFS
        while(!lQueue.isEmpty() || !rQueue.isEmpty()){
            int size = lQueue.size();
            for(int i = 0; i < size; ++i){
                Node node = lQueue.pollFirst();
                if(node.toR != true){
                    state.setCharAt(node.idx, 'L');
                    if(node.idx > 0 && array[node.idx - 1].toR == false){
                        array[node.idx - 1].toL = true;
                        lQueue.add(array[node.idx - 1]);
                    }
                }
            }
            
            size = rQueue.size();
            for(int i = 0; i < size; ++i){
                Node node = rQueue.pollFirst();
                if(node.toL != true){
                    state.setCharAt(node.idx, 'R');
                    if(node.idx < (state.length() - 1)){
                        array[node.idx + 1].toR = true;
                        rQueue.add(array[node.idx + 1]);
                    }
                }
            }
        }
        
        return state.toString();
    }
 
    public static void main(String[] args){
        Solution sol = new Solution();
        String dominoes = ".L.R...LR..L..";
        System.out.println("dominoes: " + dominoes);
        System.out.println("final state: " + sol.pushDominoes(dominoes));
    }
}
