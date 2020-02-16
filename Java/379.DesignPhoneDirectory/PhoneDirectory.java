/* HashSet: O(1)
 * 1. Use hashset to lookup the available numbers
 * 2. Use linked list to return the available number
 */

import java.util.*;

public class PhoneDirectory {
    private Deque<Integer> avaNumQue;
    private HashSet<Integer> avaNumTbl;
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        int i;
        
        avaNumQue = new LinkedList<Integer>();
        avaNumTbl = new HashSet<Integer>();
        
        for(i = 0; i < maxNumbers; ++i){
            avaNumQue.add(i);
            avaNumTbl.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int num;
        
        if(avaNumTbl.isEmpty()){
            num = - 1;
        }
        else{
            num = avaNumQue.poll();
            avaNumTbl.remove(num);
        }
        return num;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return avaNumTbl.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!avaNumTbl.contains(number)){
            avaNumQue.add(number);
            avaNumTbl.add(number);
        }
    }

    public static void main(String[] args){
        PhoneDirectory obj = new PhoneDirectory(3);
        int num;
        
        num = obj.get();
        System.out.println("get number:" + num);
        
        num = obj.get();
        System.out.println("get number:" + num);
        
        num = 2;
        System.out.println("check number " + num + " :" + obj.check(num));
        
        num = obj.get();
        System.out.println("get number:" + num);
        
        num = 2;
        System.out.println("check number " + num + " :" + obj.check(num));
        
        num = 2;
        obj.release(num);
        System.out.println("release number " + num);
        
        num = 2;
        System.out.println("check number " + num + " :" + obj.check(num));
    }
}
