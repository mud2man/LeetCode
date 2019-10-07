/* Design: Time:O(logn) Space:O(n), where the average of levels# is logn
 * 1. Have a queue to present the first value
 * 2. When search, return the target node at level1
 * 3. When add, have a stack to record the searching path until we reach level1, and do appending with its left, right, bottom, then grow up if randVal < 0.5
 * 4. When erase, find the target node at level1, and remove the whole column while dealing with edge cases like deleting node is first value at level1 
 */

import java.util.*;

public class Skiplist{
    private class Node{
        int val;
        Node next;
        Node prev;
        Node bottom;
        Node top;
        Node(int v){val = v;}
    }
    Deque<Node> levels;
    Random rand;
    public Skiplist() {
        levels = new LinkedList<>();
        rand = new Random();
    }
    
    private Node searchHelper(int target){
       if(levels.isEmpty()){
            return null;
        }else{
            Node itr = levels.peekLast();
            while(itr != null){
                if(itr.val == target){
                    Node bottom = itr.bottom;
                    while(bottom != null){
                        itr = bottom;
                        bottom = bottom.bottom;
                    }
                    return itr;
                }
                itr = (itr.next == null || itr.next.val > target)? itr.bottom: itr.next;
            }
            return null;
        }
    }
    
    public boolean search(int target) {
        return (searchHelper(target) != null);
    }
    
    private void append(Node left, Node newNode, Node bottom){
        if(left != null){
            Node right = left.next;
            newNode.prev = left;
            newNode.next = right;
            left.next = newNode;
            if(right != null){
                right.prev = newNode;
            }
        }
        newNode.bottom = bottom;
        if(bottom != null){
            bottom.top = newNode;
        }
    }
    
    public void add(int num) {
        if(levels.isEmpty()){
            levels.add(new Node(num));
        }else{
            if(num < levels.peekFirst().val){
                int nextAddNum = levels.peekFirst().val;
                for(Node level: levels){
                    level.val = num;
                }
                add(nextAddNum);
            }else{
                Deque<Node> stack = new LinkedList<>();
                Node itr = levels.peekLast();
                while(itr != null){
                    while(itr.next != null && itr.next.val <= num){
                        itr = itr.next;
                    }
                    stack.add(itr);
                    itr = itr.bottom;
                }
                Node bottom = null;
                double randVal = 0;
                while(!stack.isEmpty() && randVal < 0.5){
                    Node node = new Node(num);
                    Node top = stack.pollLast();
                    append(top, node, bottom);
                    bottom = node;
                    randVal = rand.nextDouble();
                }
                //increase levels
                if(stack.isEmpty()){
                    Node node = new Node(levels.peekLast().val);
                    append(null, node, levels.peekLast());
                    levels.add(node);
                }
            }
        }
    }
    
    private void removeFromBottom(Node deleteNode){
        if(deleteNode == null){
            return;
        }
        Node left = deleteNode.prev;
        Node right = deleteNode.next;
        left.next = right;
        if(right != null){
            right.prev = left;
        }
        removeFromBottom(deleteNode.top);
    }
    
    public boolean erase(int num) {
        Node deleteNode = searchHelper(num);
        if(deleteNode != null){
            if(deleteNode == levels.peekFirst()){
                if(deleteNode.next == null){
                    levels = new LinkedList<>();
                    return true;
                }else{
                    int secondVal = deleteNode.next.val;
                    deleteNode = deleteNode.next;
                    for(Node level: levels){
                        level.val = secondVal;
                    }
                    removeFromBottom(deleteNode);
                }
            }else{
                removeFromBottom(deleteNode);
            }
            //reduce levels
            Node itr = levels.peekLast();
            Node bottom = itr.bottom;
            while(bottom.next == null){
                levels.pollLast();
                itr = bottom;
                bottom = bottom.bottom;
            }
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args){
        Skiplist sol = new Skiplist();
        sol.add(1);
        System.out.println("sol.add(1)");
        sol.add(2);
        System.out.println("sol.add(2)");
        sol.add(3);
        System.out.println("sol.add(3)");
        System.out.println("sol.search(0):" + sol.search(0));
        sol.add(4);
        System.out.println("sol.add(4)");
        System.out.println("sol.search(1):" + sol.search(1));
        System.out.println("sol.erase(0):" + sol.erase(0));
        System.out.println("sol.erase(1):" + sol.erase(1));
        System.out.println("sol.search(1):" + sol.search(1));
    }
}
