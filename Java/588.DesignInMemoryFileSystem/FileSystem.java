/* Map: Time:O(nlogn), Space:O(n), where n is number of directories in the path
 * 1. Hava a data structure Inode to represent directory or file
 * 2. In Inode, have a priority queue "fileAndDirs" to sort files and directories in lexicographic order
 * 3. In Inode, have a map "dirs" to record the next-level directories
 * 4. In Inode, have file to store the file content
 */

import java.util.*;

public class FileSystem{
    private class Inode{
        StringBuilder file;
        PriorityQueue<String> fileAndDirs;
        Map<String, Inode> dirs;
        public Inode(){file = new StringBuilder(); fileAndDirs = new PriorityQueue<>(); dirs = new HashMap<>();}
    }
    Inode root;
    
    public FileSystem() {
        root = new Inode();
    }
    
    private Inode dirLookup(String path){
        String[] stack = path.split("/");
        Inode curr = root;
        for(int i = 1; i < stack.length; ++i){
            String dirName = stack[i];
            if(!curr.dirs.containsKey(dirName)){
                curr.dirs.put(dirName, new Inode());
                curr.fileAndDirs.add(dirName);
            }
            curr = curr.dirs.get(dirName);
        }
        return curr;
    }
    
    public List<String> ls(String path) {
        Inode subRoot = dirLookup(path);
        List<String> ret = new ArrayList<>();
        while(!subRoot.fileAndDirs.isEmpty()){
            ret.add(subRoot.fileAndDirs.poll());
        }
        for(String fileAndDir: ret){
            subRoot.fileAndDirs.add(fileAndDir);
        }
        return ret;
    }
    
    public void mkdir(String path) {
        dirLookup(path);
    }
    
    public void addContentToFile(String filePath, String content) {
        Inode subRoot = dirLookup(filePath);
        int lastIndex = filePath.lastIndexOf("/");
        String file = filePath.substring(lastIndex + 1);
        subRoot.file.append(content);
        if(!subRoot.dirs.containsKey(file)){
            subRoot.dirs.put(file, new Inode());
            subRoot.fileAndDirs.add(file);
        }
    }
    
    public String readContentFromFile(String filePath) {
        Inode subRoot = dirLookup(filePath);
        return subRoot.file.toString();
    }
 
    public static void main(String[] args){
        FileSystem sol = new FileSystem();
        String path = "/";
        System.out.println("ls(" + path + "):" + sol.ls(path));
        
        path = "/a/b/c";
        sol.mkdir(path);
        System.out.println("mkdir(" + path +  ")");
        
        path = "/a/b/c/d";
        String content = "hello";
        sol.addContentToFile(path, content);
        System.out.println("addContentToFile(" + path + ", " + content + ")");
        
        path = "/";
        System.out.println("ls(" + path + ")" + sol.ls(path));
        
        path = "/a/b/c/d";
        System.out.println("readContentToFile(" + path + "):" + sol.readContentFromFile(path));
        
    }
}
