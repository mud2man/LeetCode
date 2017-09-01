/* O(n)
 * 1. Use a list of iterators to record the current index of all lists
 * 2. Delete the iterator if the index reach to the end
 */

import java.util.*; // Stack

public class ZigzagIterator {
    private class ListIterator{
        List<Integer> list;
        int length;
        int currIdx;
        ListIterator(List<Integer> l, int len, int c){list = l; length = len; currIdx = c;}
    }
    private List<ListIterator> listIterators;
    private Iterator<ListIterator> listIterator;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        listIterators = new ArrayList<ListIterator>();
        if(!v1.isEmpty()){
            listIterators.add(new ListIterator(v1, v1.size(), 0));
        }
        
        if(!v2.isEmpty()){
            listIterators.add(new ListIterator(v2, v2.size(), 0));
        }
        
        listIterator = listIterators.iterator();
    }

    public int next() {
        if(!listIterator.hasNext()){
            listIterator = listIterators.iterator();
        }
        
        ListIterator itr = listIterator.next();
        int nextVal = itr.list.get(itr.currIdx++);
        if(itr.currIdx == itr.length){
            listIterator.remove();
        }
        return nextVal;
    }

    public boolean hasNext() {
        return !(listIterators.isEmpty());
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
