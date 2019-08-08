/*460. LFU Cache
Hard: 
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 capacity  );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

/*解题思路
这道题目看起来和之前的LRU题目有点像，其实不太一样。LRU要我们当储存满了的时候把最长时间没有
被使用的缓存淘汰掉。而这里是让我们把最少用到的缓存淘汰掉。这道题比之前那道LRU的题目还要麻烦一
些，因为那道题只要用个list把数字按时间顺序存入，链表最后的位置总是最久未被使用的，每次删除底最后
的值即可。而这道题不一样，由于需要删除最少次数的数字，那么我们必须要统计每一个key出现的次数，
所以我们用两个哈希表keyToValue，keyToFreq来记录当前数据{key, value}和其出现次数之间的映射，
这样还不够，为了方便操作，我们需要把相同频率的key都放到一个list中，那么需要另一个哈希表freqKeyPos
来建立频率和当前频率指向的list中所有key之间的映射。由于题目中要我们在O(1)的时间内完成操作了。
最后当然我们还需要两个变量capacity和minFreq，分别来保存cache的大小，和当前最小的频率。
这里面又学到了一个新的数据结构，LinkedHashSet<>。这个数据结构其实就是有序的HashSet,所有
元素按照插入时间排序。如果插入重复的元素的话还是直接略过。同时查找时间还是O(1).
回到问题本身来，网上有一个很好地例子说明这个LFU是如何操作的。https://www.cnblogs.com/grandyang/p/6258459.html

*/

class LFUCache {
    int minFreq;
    int capacity;
    Map<Integer, Integer> keyToValue = new HashMap<>();
    Map<Integer, Integer> keyToFreq = new HashMap<>();
    Map<Integer, LinkedHashSet<Integer>> freqKeyPos = new HashMap<>();
    public LFUCache(int capacity) {
        minFreq = 1;//因为最低的频率就是1
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!keyToValue.containsKey(key)) return -1;
        int count = keyToFreq.get(key);//先获取这个Key的频率，然后升级一下
        freqKeyPos.get(count).remove(key);
        
        if(freqKeyPos.get(minFreq).size() == 0) minFreq++;//如果这个key出现的频率最小，且频率最小的元素只有这一个Key。升级minFreq
        
        insert(key, count+1);//调用insert()方法，插入到keyToFreq 和freqKeyPos中
        return keyToValue.get(key);
        
    }
    
    public void put(int key, int value) {
        if(capacity <=0) return;

        //题目要求是，如果已经有这个Key了，那么首先更新一下value, 然后调用get()方法升级频率
        if(keyToValue.containsKey(key)){
            keyToValue.put(key, value);
            get(key); //update the key
            return;
        } 
        /*如果超过了capacity，那么首先删除最小频率中的第一个数.
        因为hashset无法按位获取元素，所以要用Iterator()的办法取到
        链表的第一位
        */    
        if(keyToValue.size() >= capacity){
            removeLeastFreq(freqKeyPos.get(minFreq).iterator().next());
        }
        //新添加元素所以重置minFreq
        minFreq = 1;
        insert(key, minFreq);
        keyToValue.put(key, value);
    }
    
    //将频率最小的Key删除
    public void removeLeastFreq(int key){
        freqKeyPos.get(minFreq).remove(key);
        keyToValue.remove(key);
        keyToFreq.remove(key);
    }
    
    
    public void insert(int key, int count){
        //如果挡墙频率还没有被创建，则先创建
        if(!freqKeyPos.containsKey(count)) freqKeyPos.put(count, new LinkedHashSet());
        freqKeyPos.get(count).add(key);
        keyToFreq.put(key, count);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */