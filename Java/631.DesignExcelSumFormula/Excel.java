/* Topological sort: Time:O(n*m), Space:O(n*m)
 * 1. Have a node array, where node contain parent, child, indegree inorder to construct adjacency list
 * 2. When doing sum and set, need to reconstruct child list of its parent by "changeChild"
 * 3. We can use "parent" map to caculate the current sum, because parent contains weight
 * 4. When node's sum change, invoke propagate to propagate change to child chain, where propagate use topological order
 */

import java.util.*;

public class Excel {
    private class Node{
        int sum;
        int prevSum;
        int idx;
        HashMap<Integer, Integer> parent;
        HashSet<Integer> child;
        int inDegree;
        Node(int i){idx = i; parent = new HashMap<Integer, Integer>(); child = new HashSet<Integer>();};
    }
    
    int h;
    int w;
    Node[] nodes;

    public Excel(int H, char W) {
        h = H;
        w = (int)(W - 'A') + 1;
        nodes = new Node[h*w];
        for(int i = 0; i < (h * w); ++i){
            nodes[i] = new Node(i);
        }
    }
    
    private void changeChild(Set<Integer> parents, int target, boolean isRemove){
        for(int parent: parents){
            if(isRemove){
                nodes[parent].child.remove(target);
            }
            else{
                nodes[parent].child.add(target);
            }
        }
    }
    
    private void dfs(Node source, HashSet<Integer> visited, boolean isReset){
        if(visited.contains(source.idx)){
            return;
        }
        
        visited.add(source.idx);
        if(isReset){
            source.inDegree = 0;
        }
        else{
            source.inDegree++;
            source.prevSum = source.sum;
        }
        
        for(int child: source.child){
            dfs(nodes[child], visited, isReset);
        }
    }
    
    private void propagate(Node source, int diff){
        if(diff == 0){
            return;
        }
        
        //reset indegree
        dfs(source, new HashSet<Integer>(), true);
        
        //set indegree and preSum
        dfs(source, new HashSet<Integer>(), false);
        
        //topological sort
        source.sum = source.sum + diff;
        LinkedList<Node> queue = new LinkedList<Node>();
        queue.add(source);
        while(!queue.isEmpty()){
            Node node = queue.pollFirst();
            diff = node.sum - node.prevSum;
            for(int child: node.child){
                nodes[child].inDegree--;
                nodes[child].sum += diff * nodes[child].parent.get(node.idx);
                if(nodes[child].inDegree == 0){
                    queue.add(nodes[child]);
                }
            }
        }
    }
    
    public void set(int r, char c, int v) {
        int idx = (r - 1) * w + (int)(c - 'A');
        Node source = nodes[idx];
        int diff = v - source.sum;
        changeChild(source.parent.keySet(), idx, true);
        propagate(source, diff);
    }
    
    public int get(int r, char c) {
        int idx = (r - 1) * w + (int)(c - 'A');
        return nodes[idx].sum;
    }
    
    public int sum(int r, char c, String[] strs) {
        int idx = (r - 1) * w + (int)(c - 'A');
        Node source = nodes[idx];
        changeChild(source.parent.keySet(), idx, true);
        source.parent = new HashMap<Integer, Integer>();
        for(String range: strs){
            if(range.indexOf(":") == -1){
                idx = (Integer.parseInt(range.substring(1)) - 1) * w + (int)(range.charAt(0) - 'A');
                source.parent.putIfAbsent(idx, 0);
                source.parent.put(idx, source.parent.get(idx) + 1);
            }
            else{
                String[] corners = range.split(":");
                int start = (Integer.parseInt(corners[0].substring(1)) - 1) * w + (int)(corners[0].charAt(0) - 'A');
                int row = Integer.parseInt(corners[1].substring(1)) - Integer.parseInt(corners[0].substring(1)) + 1;
                int col = (int)(corners[1].charAt(0) - corners[0].charAt(0)) + 1;
                for(int y = 0; y < row; ++y){
                    for(int x = 0; x < col; ++x){
                        idx = start + x;
                        source.parent.putIfAbsent(idx, 0);
                        source.parent.put(idx, source.parent.get(idx) + 1);
                    }
                    start += w;
                }
            }
        }
        changeChild(source.parent.keySet(), (r - 1) * w + (int)(c - 'A'), false);
        
        int v = 0;
        for(Map.Entry<Integer, Integer> entry: source.parent.entrySet()){
            int key = entry.getKey();
            int val = entry.getValue();
            v += nodes[key].sum * val;
        }
        int diff = v - source.sum;
        propagate(source, diff);
        return get(r, c);
    }

    public static void main(String[] args){
        int H = 3;
        char W = 'C';
        Excel excel = new Excel(H, W);
        int r, v;
        char c;
        String[] range;
        

        System.out.println("H:" + H + ", W:" + W);

        r = 1;
        c = 'A';
        v = 2;
        excel.set(r, c, v);
        System.out.println("set(" + r + ", " + c + ", " + v + ")");

        r = 3;
        c = 'C';
        range = new String[]{"A1", "A1:B2"};
        System.out.println("sum(" + r + ", " + c + ", " + Arrays.toString(range) + ") = " + excel.sum(r, c, range));

        r = 2;
        c = 'B';
        v = 2;
        excel.set(r, c, v);
        System.out.println("set(" + r + ", " + c + ", " + v + ")");

        r = 3;
        c = 'C';
        System.out.println("get(" + r + ", " + c + ") = " + excel.get(r, c));
    }
}
