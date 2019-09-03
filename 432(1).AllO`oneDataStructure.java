/*432. All O`one Data Structure
Hard: 
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
*/

/*解题思路
这道题目基本实现思路是参考youtuber 花花的解法: https://www.youtube.com/watch?v=wYqLisoH80w

题目大意是说，我们可以调用Inc()和Dec()两个function用来给指定的字符串增加一
或者减少一。如果一个字符串小于一的话就移除这个结构。同时还要支持两个方法，一个是
快速获取数值最大的字符串，和快速获取数值最小的字符串。这道题目有点像460题的LFU
设计。不过这里的难点就是如何快速获取数值最大和数值最小的字符串。
我们的做法是创建Node节点串联所有存在的数值(各个可能出现的频率)。具体做法详见代码。

*/

class AllOne {
    Node head, tail;
    //字符串和频率
    Map<String, Integer> keyCountMap = new HashMap<>();
    //每个频率对应的字符串有哪些
    Map<Integer, LinkedHashSet<String>> countKeyMap = new HashMap<>();
    //所有频率构成的节点连接在一起
    Map<Integer, Node> countNodeMap = new HashMap<>();;
    /** Initialize your data structure here. */
    public AllOne() {
        
        head = new Node(0);
        tail = new Node(Integer.MAX_VALUE);
        //System.out.println(head.count);
        head.next = tail;
        tail.prev = head;
        countNodeMap.put(0, head);
        countNodeMap.put(Integer.MAX_VALUE, tail);
        
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
    	if(!keyCountMap.containsKey(key)){
    		keyCountMap.put(key,0);
    	}
        //先获取被增值节点的原频率
    	int preCount = keyCountMap.get(key);
    	Node preNode = countNodeMap.get(preCount);

    	int newCount = preCount+1;
        //更新字符串对应的频率
    	keyCountMap.put(key, newCount);
    	
        //如果这个频率在节点中还没有被创建的话，则新建节点
    	if(newCount != preNode.next.count){
    		Node newNode = new Node(newCount);
    		insert(preNode, newNode);
    		countKeyMap.put(newCount, new LinkedHashSet());
    		countNodeMap.put(newCount, newNode);
    	}
    	countKeyMap.get(newCount).add(key);


    	if(preCount >0){
    		Set<String> oldSet = countKeyMap.get(preCount);
    		oldSet.remove(key);
            //如果当前被升级字符串是原来频率中最后一个了，那么原来的要节点移除
    		if(oldSet.isEmpty()){
    			delete(preNode);
    			countKeyMap.remove(preCount);
    			countNodeMap.remove(preCount);
    		}
    	}
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    //基本和inc()一样
    public void dec(String key) {
    	if(!keyCountMap.containsKey(key)) return;

    	int preCount = keyCountMap.get(key);
    	Node preNode = countNodeMap.get(preCount);

    	int newCount = preCount-1;
    	keyCountMap.put(key, newCount);

    	if(newCount !=0){
            //被降级的值没有对应的节点
    		if(newCount != preNode.prev.count){
	    		Node newNode = new Node(newCount);
	    		insert(preNode.prev, newNode);

	    		countKeyMap.put(newCount, new LinkedHashSet());
	    		countNodeMap.put(newCount, newNode);
	    	}
	    	countKeyMap.get(newCount).add(key);
    	}else{
    		keyCountMap.remove(key);
    	}

    	Set<String> oldSet = countKeyMap.get(preCount);
    	oldSet.remove(key);
    	if(oldSet.isEmpty()){
    		delete(preNode);
    		countKeyMap.remove(preCount);
    		countNodeMap.remove(preCount);
    	}
    	
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail) {
            return "";
        }
        
        String res = countKeyMap.get(tail.prev.count).iterator().next();
        return res;
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next == tail) return "";
        String res = countKeyMap.get(head.next.count).iterator().next();
        return res;
    }

    public void insert(Node preNode, Node curNode){
    	curNode.next = preNode.next;
    	curNode.next.prev = curNode;

    	curNode.prev = preNode;
    	preNode.next = curNode;
    }

    public void delete(Node node){
    	node.next.prev = node.prev;
    	node.prev.next = node.next;
    }
 
}

class Node{
    int count; 
    Node prev = null;
    Node next = null;
    public Node(int count){
        this.count = count;
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */