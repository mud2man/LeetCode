/* O(n)
 * 1. Use a list of iterators to record the current index of all lists
 * 2. Delete the iterator if the index reach to the end
 */

import java.util.*; // Stack

public class ZigzagIterator {
    private List<Iterator<Integer>> iterators;
    private Iterator<Iterator<Integer>> globalIterator;
    private Iterator<Integer> localIterator;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iterators = new ArrayList<Iterator<Integer>>();
        localIterator = null;
        iterators.add(v1.iterator());
        iterators.add(v2.iterator());
        globalIterator = iterators.iterator();
    }

    public int next() {
        return localIterator.next();
    }

    public boolean hasNext() {
        while(globalIterator.hasNext()){
            Iterator<Integer> iterator = globalIterator.next();
            if(iterator.hasNext()){
                localIterator = iterator;
                return true;
            }
            else{
                globalIterator.remove();
            }
        }
        
        //roll back
        globalIterator = iterators.iterator();
        
        while(globalIterator.hasNext()){
            Iterator<Integer> iterator = globalIterator.next();
            if(iterator.hasNext()){
                localIterator = iterator;
                return true;
            }
            else{
                globalIterator.remove();
            }
        }
        
        return false;
    }
 
    public static void main(String[] args){
        ZigzagIterator sol;
        List<Integer> v1;
        List<Integer> v2;

        v1 = new ArrayList<Integer>();
        v2 = new ArrayList<Integer>();
        v1.add(1);
        v1.add(2);
        v2.add(3);
        v2.add(4);
        v2.add(5);
        v2.add(6);
        System.out.println("v1: " + v1);
        System.out.println("v2: " + v2);

        System.out.println("iterate: ");
        ZigzagIterator i = new ZigzagIterator(v1, v2);
         while (i.hasNext()){
            System.out.print(i.next() + ", ");
        }
        System.out.println("");
    }
}
