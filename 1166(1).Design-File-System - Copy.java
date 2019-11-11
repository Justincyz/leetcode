/*1166. Design File System
Medium
链接: https://leetcode.com/problems/design-file-system/
You are asked to design a file system which provides two functions:

createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

Implement the two functions.
Please refer to the examples for clarifications.

Example 1:
Input: 
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output: 
[null,true,1]
Explanation: 
FileSystem fileSystem = new FileSystem();
fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1


Example 2:
Input: 
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output: 
[null,true,true,2,false,-1]
Explanation: 
FileSystem fileSystem = new FileSystem();
fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.

*/

/*解题思路
题目让我们做一个比较简单的file system, 另外有一道升级版的file system
的hard题目588. Design In-Memory File System.
这道题目简单之处在于可以直接把所有出现的path存在hashmap中，而不是用
trie结构将每一个path节点存下来。我一开始用的就是trie结构，没想到会
比较慢。第一个答案就是网上的最速解法。很简单明了

*/
class FileSystem {

    HashMap<String, Integer> map;
    public FileSystem() {
        map = new HashMap<String, Integer>();
        map.put("", 0);
    }
    
    public boolean createPath(String path, int value) {
        int lastSlashIndex = path.lastIndexOf("/");//直接找到最后一个文件节点位置
        String parent = path.substring(0, lastSlashIndex);
        if(!map.containsKey(parent) || map.containsKey(path)) return false;
        
        map.put(path, value);
        return true;
    }
    
    public int get(String path) {
        if (!map.containsKey(path)) {
            return -1;
        }
        return map.get(path);
    }
}

//一开始自己写的，比较慢，但是是很完整的解法且占的内存空间较少

class FileSystem {
    Node root;
    public FileSystem() {
        root = new Node();
    }
    
    public boolean createPath(String path, int value) {
        path = path.substring(1);
        String[] route = path.split("/");
        Node dummy = root;
        for(int i=0; i<route.length-1; i++){
            if(!dummy.child.containsKey(route[i])) return false;
            else dummy = dummy.child.get(route[i]);
        }
        if(dummy.child.containsKey(route[route.length-1])) return false;
        else dummy.child.put(route[route.length-1], new Node());
        dummy = dummy.child.get(route[route.length-1]);
        dummy.val = value;
        return true;
    }
    
    public int get(String path) {
        path = path.substring(1);
        String[] route = path.split("/");
      
        Node dummy = root;
        for(int i=0; i<route.length; i++){
            if(!dummy.child.containsKey(route[i])) return -1;
            else dummy = dummy.child.get(route[i]);
        }
        return dummy.val;
    }
}

class Node{
    Map<String, Node> child = new HashMap<>();
    int val;
   
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */