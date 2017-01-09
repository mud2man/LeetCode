/* O(n)
 * 1. Use ptr1 and ptr2 to track the current positions of v1 and v2
 */

import java.util.*; // Stack

public class ZigzagIterator {
    private int ptr1;
    private int ptr2;
    private int owner; //v1: 1, v2: 2
    List<Integer> v1;
    List<Integer> v2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.ptr1 = -1;
        this.ptr2 = -1;
        this.owner = 1;
    }

    public int next() {
        
        while(true){
            if(owner == 1){
                owner = 2;
                if((ptr1 + 1) < v1.size()){
                    return v1.get(++ptr1);
                }
            }
        
            if(owner == 2){
                owner = 1; 
                if((ptr2 + 1) < v2.size()){
                    return v2.get(++ptr2);
                }
            }
        }
    }

    public boolean hasNext() {
        if((ptr1 + ptr2 + 2) < (v1.size() + v2.size())){
            return true;
        }
        else{
            return false;
        }
    }

    public static void main(String[] args){
		ZigzagIterator sol;
		List<Integer> v1;
		List<Integer> v2;

		v1 = new ArrayList<Integer>();
		v2 = new ArrayList<Integer>();
		ZigzagIterator i = new ZigzagIterator(v1, v2);
			
		v1.add(1);
		v1.add(2);
		v2.add(3);
		v2.add(4);
		v2.add(5);
		v2.add(6);
		
		System.out.println("v1: " + v1);
		System.out.println("v2: " + v2);

		System.out.println("iterate: ");
 		while (i.hasNext()){
			System.out.print(i.next() + ", ");
		}
		System.out.println("");
	}
}
