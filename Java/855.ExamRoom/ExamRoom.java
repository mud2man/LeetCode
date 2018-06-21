/* HashMap: seat:O(n), leave:O(1), Space:O(n). LeetCode has solution with O(logn) of seat and  O(n) of leave
 * 1. Have an list "students" composed of Node
 * 2. Have a map to store key:p-value:node mapping
 * 3. In seat, traverse list and find the insertion point with longest distance
 * 4. In leave, remove the node from list "students", and remove it form map
 */

import java.util.*;

public class ExamRoom {
    private class Node{
        int id;
        Node prev;
        Node next;
        Node(int i){id = i;}
    }
    int n;
    Node students;
    HashMap<Integer, Node> map;

    public ExamRoom(int N) {
        n = N;
        students = null;
        map = new HashMap<Integer, Node>();
    }

    public int seat() {
        if(students == null){
            students = new Node(0);
            map.put(0, students);
            return 0;
        }
        else{
            int longestDis = 0;
            int id = 0;
            Node p = null;
            Node curr = students;
            Node next = curr.next;
            while(next != null){
                int dis = (next.id - curr.id) / 2;
                if(dis > longestDis){
                    longestDis = dis;
                    p = curr;
                    id = p.id + dis;
                }
                curr = curr.next;
                next = next.next;
            }
            
            if(students.id >= longestDis){
                longestDis = students.id; 
                p = null;
                id = 0;
            }
            
            if((n - 1 - curr.id) > longestDis){
                longestDis = n - 1 - curr.id; 
                p = curr;
                id = n - 1;
            }
            
            Node newStudent = new Node(id);
            if(p == null){
                newStudent.next = students;
                students.prev = newStudent;
                students = newStudent;
            }
            else{
                next = p.next;
                newStudent.next = next;
                newStudent.prev = p;
                p.next = newStudent;
                if(next != null){
                    next.prev = newStudent;
                }
            }
            map.put(id, newStudent);
            return id;
        }
    }
    
    public void leave(int p) {
        Node oldStudent = map.get(p);
        map.remove(p);
        if(oldStudent.next == null && oldStudent.prev == null){
            students = null;
        }
        else if(oldStudent.next == null){
            oldStudent.prev.next = null;
        }
        else if(oldStudent.prev == null){
            students = oldStudent.next;
            students.prev = null;
        }
        else{
            oldStudent.prev.next = oldStudent.next;
            oldStudent.next.prev = oldStudent.prev;
        }
    }

    public static void main(String[] args){
        ExamRoom sol = new ExamRoom(10);
        System.out.println("seat(): " + sol.seat());
        System.out.println("seat(): " + sol.seat());
        System.out.println("seat(): " + sol.seat());
        System.out.println("seat(): " + sol.seat());
        sol.leave(4);
        System.out.println("leave(4)");
        System.out.println("seat(): " + sol.seat());
    }
}
