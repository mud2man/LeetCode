/* Stack + Map: Time:O(1), Space:O(n)
 * 1. Have "size2Increments" to remember when to add size2Increments.get(stack.size()) to currIncrement
 * 2. In push, put currIncrement to size2Increments with key = stack.size(), and reset currIncrement
 * 3. In pop: accumulate currIncrement with size2Increments.get(stack.size())
 * 4. in increment, update size2Increments 
 */     

import java.util.*; // Stack

public class CustomStack {
    Deque<Integer> stack = new LinkedList<>();
    int maxSize;
    Map<Integer, Integer> size2Increments;
    int currIncrement;

    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        this.size2Increments = new HashMap<>();
    }
    
    public void push(int x) {
        if(stack.size() < maxSize){
            if(currIncrement != 0){
                size2Increments.put(stack.size(), size2Increments.getOrDefault(stack.size(), 0) + currIncrement);
            }
            currIncrement = 0;
            stack.add(x);
        }
    }
    
    public int pop() {
        if(stack.isEmpty()){
            return -1;
        }
        
        if(size2Increments.containsKey(stack.size())){
            currIncrement += size2Increments.get(stack.size());
            size2Increments.remove(stack.size());
        }
        
        int top = stack.pollLast() + currIncrement;
        currIncrement =(stack.isEmpty())? 0: currIncrement;
        return top;
    }
    
    public void increment(int k, int val) {
        int targetSize = Math.min(stack.size(), k);
        size2Increments.put(targetSize, size2Increments.getOrDefault(targetSize, 0) + val);
    }
 
    public static void main(String[] args){
        int maxSize = 3;
        System.out.println("maxSize:" + maxSize);
        CustomStack sol = new CustomStack(maxSize);
        sol.push(1);
        System.out.println("push(1)");
        sol.push(2);
        System.out.println("push(2)");
        System.out.println("pop():" + sol.pop());
        sol.push(2);
        System.out.println("push(2)");
        sol.push(3);
        System.out.println("push(3)");
        sol.push(4);
        System.out.println("push(4)");
        sol.increment(5, 100);
        System.out.println("increment(5, 100)");
        sol.increment(2, 100);
        System.out.println("increment(2, 100)");
        System.out.println("pop():" + sol.pop());
        System.out.println("pop():" + sol.pop());
        System.out.println("pop():" + sol.pop());
        System.out.println("pop():" + sol.pop());
    }
}
