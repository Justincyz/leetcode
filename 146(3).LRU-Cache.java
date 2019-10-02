/*146. LRU Cache
Medium
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache(2 capacity );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);        returns 1
cache.put(3, 3);     evicts key 2
cache.get(2);        returns -1 (not found)
cache.put(4, 4);     evicts key 1
cache.get(1);        returns -1 (not found)
cache.get(3);        returns 3
cache.get(4);        returns 4

*/

/*解题思路
用HashMap可以获得get O(1)的时间。用double LinkedList
可以让插入变成O(1). 有几个点需要注意一下，head 和 tail一直是两个
pointer, 不会改变，插入的值都是在head 和tail之间的。每一次get一个值，
将它拿出来然后放在list的最前面。这样可以保证最后面的 removeLast()一定是最
不常用的那一个。同时要考虑重复的值，有可能Key一样但是value不一样，需要更新

*/

class LRUCache {
    HashMap<Integer, Node> map;
    Node head, tail;
    int cap;
    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
        cap = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            addFirst(node);
            return node.value;
        }
        return -1 ;      
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));
            Node node = new Node(key, value);
            map.put(key, node);
            addFirst(node);
            return;
        }
        Node node = new Node(key, value);
        if(map.size()>=cap){
            removeLast();
        }
       
        map.put(key, node);
        addFirst(node);  
    }
    
    public void remove(Node node){
        Node p = node.prev;
        Node n = node.next;
        node.next =null;
        node.prev = null;
        p.next = n;
        n.prev  = p;
    }
    
    public void removeLast(){
        Node pp = tail.prev.prev;
        Node p = tail.prev;
        p.next = null;
        p.prev = null;
        pp.next = tail;
        tail.prev = pp;
        map.remove(p.key);
    }
    
    public void addFirst(Node node){
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }
    
}

class Node{
    int value, key;
    Node next, prev;
    public Node(int key, int value){
        this.value = value;
        this.key = key;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

//时隔三个月写出更加简短的版本

class LRUCache {
    Map<Integer, Node> map;
    int capacity;
    Node head = new Node(), tail = new Node();
    public LRUCache(int capacity) {
        map = new HashMap<>();
        this.capacity = capacity;

        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        insert(key, node.val);
        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));    
        } 
        insert(key, value); 
        //先插入再删除比较不会容易出错
        if(map.size() > capacity) remove(tail.prev);
    }
    
    public void insert(int key, int value){
        Node n = new Node();
        n.key = key;
        n.val = value;

        n.next = head.next;
        head.next = n;
        n.next.prev = n;
        n.prev = head;
        map.put(key, n);
    }
    
    public void remove(Node n){
        Node prev = n.prev, next = n.next;

        prev.next = next;
        next.prev = prev;
        map.remove(n.key);
    }
}

class Node{
    Node next, prev;
    int key;
    int val;
    public Node(){}
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */