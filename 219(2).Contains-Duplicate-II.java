/*219. Contains Duplicate II
链接：https://leetcode.com/problems/contains-duplicate-ii/submissions/
Easy: 
Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false
*/

/*解题思路
这道题目是217. Contains Duplicate 这道题目的follow-up。也是easy的
题目。问我们在一个数组中，是否存在nums[i]和nums[j]是一样的数字，且
i和j的距离小于等于k. 
我一开始就是简单地用HashMap来做，key是数值，value是位置，那么每一次
我们遇到一个新的数的时候，我们就检查Map中是否已经存在了这个数，如果有的话
那么位置是多少。位置小于K就返回结果，位置大于K的话就更新一下这个位置，这样
每次遇到新的数字往前都可以找到最近的。

在网上看到了另外一个解法，都甚至不需要用hashmap, 而是用一个hashset就可以，
我们只存每个数字往前的K个元素，超过k的就从hashset中删除。这样就可以省下在
hashmap中查找的时间。

*/


class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i-map.get(nums[i]) <=k) return true;
                else map.put(nums[i], i);
            }else{
                map.put(nums[i], i);
            }
        }
        return false;
    }
}


class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
                Set<Integer> set = new HashSet();
        for(int i = 0; i < nums.length; ++i) {
            if (i - k > 0) {
                set.remove(nums[i - k - 1]);
            }

            if (!set.add(nums[i])) {
                return true;
            }
        }

        return false;
    }
}