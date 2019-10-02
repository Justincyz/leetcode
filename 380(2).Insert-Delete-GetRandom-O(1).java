/*380. Insert Delete GetRandom O(1)
Medium
链接: 
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.

Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/

/*解题思路
此题的解法是利用到了一个一维list和一个HashMap，其中list用来保存数字，HashMap 用来建立每个数字和其在数组中的位置之间的映射，对于插入操作，先看这个数字是否已经在 HashMap 中存在，如果存在的话直接返回 false，不存在的话，将其插入到数组的末尾，然后建立数字和其位置的映射。删除操作是比较 tricky 的，还是要先判断其是否在 HashMap 里，如果没有，直接返回 false。由于 HashMap 的删除是常数时间的，而数组并不是，为了使数组删除也能常数级，实际上将要删除的数字和数组的最后一个数字调换个位置，然后修改对应的 HashMap 中的值，这样只需要删除数组的最后一个元素即可，保证了常数时间内的删除。而返回随机数对于数组来说就很简单了，只要随机生成一个位置，返回该位置上的数字即可

进阶题目 381:Insert Delete GetRandom O(1) - Duplicates allowed
*/

import java.util.*;

class RandomizedSet {
    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    /** Initialize your data structure here. */
    public RandomizedSet() {
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) return false;
        map.put(val, list.size());//还没有添加之前的长度，是添加之后的位置
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        
        int removePos = map.get(val);
        int lastElem = list.get(list.size()-1);
        list.set(removePos, lastElem);
        map.put(lastElem, removePos);
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        double random = Math.random();
        return list.get((int)(random*(list.size())));
    }
}
