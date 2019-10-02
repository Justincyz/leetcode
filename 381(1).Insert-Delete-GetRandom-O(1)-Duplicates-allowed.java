/*381. Insert Delete GetRandom O(1) - Duplicates allowed
Hard:
链接: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/

Design a data structure that supports all following operations in average O(1) time.

Note: Duplicate elements are allowed.
insert(val): Inserts an item val to the collection.
remove(val): Removes an item val from the collection if present.
getRandom: Returns a random element from current collection of elements. The 
probability of each element being returned is linearly related to the number 
of same value the collection contains.

Example:

// Init an empty collection.
RandomizedCollection collection = new RandomizedCollection();

// Inserts 1 to the collection. Returns true as the collection did not contain 1.
collection.insert(1);

// Inserts another 1 to the collection. Returns false as the collection contained 1.
 Collection now contains [1,1].
collection.insert(1);

// Inserts 2 to the collection, returns true. Collection now contains [1,1,2].
collection.insert(2);

// getRandom should return 1 with the probability 2/3, and returns 2 with the probability 1/3.
collection.getRandom();

// Removes 1 from the collection, returns true. Collection now contains [1,2].
collection.remove(1);

// getRandom should return 1 and 2 both equally likely.
collection.getRandom();

*/

/*解题思路
这道题目是 Insert Delete GetRandom-0(1)的拓展，区别是这道题目允许重复数字，而原来的题目不允许。
我们可以建立数字和其所有出现位置的集合之间的映射，虽然写法略有不同，但是思路和之前那题完全一样，都是将数组最后一个位置的元素和要删除的元素交换位置，然后删掉最后一个位置上的元素。对于 insert 函数，我们将要插入的数字val插在list的末尾且将此位置加入 map.get(val)获取的 linkedhashset的末尾，我们判断是否有重复只要看刚才插入元素映射的hashset存在一个值还是有多个值就可以。
remove 函数是这题的难点，我们首先看 HashMap 中是否存在val，没有的话直接返回 false。如果存在的话然后我们先取出 list 的尾元素，将这个元素和要被remove 的元素交换，所谓的交换其实是将要被remove元素的位置覆盖掉就可以。做法就是将被remove元素的位置先定义好，然后将这个位置添加到末尾元素所在的hashset中。 如果被移除的元素是这个元素集合的最后一个，那么我们把这个映射直接删除。


*/



class RandomizedCollection {

    List<Integer> list;
    HashMap<Integer, Set<Integer>> map;
    java.util.Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Set<Integer>>();
        rand = new Random();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if(!map.containsKey(val)) {
            //注意这里使用的是linkedhashset, 因为我们要保证同一元素的位置排列是从小到大的
            map.put(val, new LinkedHashSet<Integer>());
        }
        
        list.add(val);
        map.get(val).add(list.size() - 1);
        return map.get(val).size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }
        
        //我们移除的是val所在集合的第一个
        int removeIndex = map.get(val).iterator().next();
        map.get(val).remove(removeIndex);
        
        int lastVal = list.get(list.size() - 1);
        list.set(removeIndex, lastVal);
        map.get(lastVal).add(removeIndex);
        
        map.get(lastVal).remove(list.size() - 1);
        list.remove(list.size() - 1);
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        int index = rand.nextInt(list.size());
        return list.get(index);
    }
}

//另一种做法，但是不是O(1),思路一样，用了priorityqueue

import java.util.*;

class RandomizedCollection {
    Map<Integer, PriorityQueue<Integer>> map;
    List<Integer> list;
    /** Initialize your data structure here. */
    public RandomizedCollection() {
        map  = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = true;
        if(map.containsKey(val)) flag = false;
        
        if(flag){
            map.put(val,new PriorityQueue<>((a,b) ->b-a));
        }
        
        map.get(val).add(list.size());
        list.add(val);
        return flag;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        
        int lastElem = list.get(list.size()-1);
        int valPos = map.get(val).poll();//remove the val with the largest index
        
        if(valPos == (list.size()-1)){
            list.remove(list.size()-1); //remove the last element from list
            if(map.get(val).size() ==0) map.remove(val);//if the val is the last element from pq
            return true;
        }
        
        if(map.get(val).size() ==0) map.remove(val);//if the val is the last element from pq

            
        map.get(lastElem).poll();
        map.get(lastElem).add(valPos);
        list.set(valPos, lastElem);
        list.remove(list.size()-1); //remove the last element from list
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        double random = Math.random(); //random的范围是 0-1， 所以这一步要用double先存下来
 
        return list.get((int)(random*list.size())); //需要转化成int值
    }
}