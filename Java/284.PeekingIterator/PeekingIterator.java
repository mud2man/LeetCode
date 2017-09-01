/* O(1)
 * 1. Use a list of Integer next to store the next object
 * 2. If "next: is not empty, return it when invoking next() or peek()
 */

import java.util.*; // Stack

public class PeekingIterator implements Iterator<Integer>  {
    private Iterator<Integer> iterator;
    private List<Integer> next;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        this.next = new ArrayList<Integer>();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        Integer peekVal;
        
        if(next.isEmpty()){
            peekVal = this.iterator.next(); 
            next.add(peekVal);
        }
        else{
            peekVal = next.get(0);
        }
        return peekVal;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if(next.isEmpty()){
            return this.iterator.next();
        }
        else{
            Integer nextVal = next.get(0);
            next.remove(0);
            return nextVal;
        }
    }

    @Override
    public boolean hasNext() {
        return (this.iterator.hasNext() | !(this.next.isEmpty()));
    }
    
    public static void main(String[] args){
        Integer[] array= new Integer[] { 1, 2, 3 };
        List<Integer> list = Arrays.asList(array);

        System.out.println("list: " + list);

        System.out.println("iterate: ");
        PeekingIterator i = new PeekingIterator(list.iterator());
        while (i.hasNext()){
            System.out.print(i.peek() + ", ");
            System.out.print(i.next() + ", ");
        }
        System.out.println("");
    }
}
