/*532. K-diff Pairs in an Array
链接：https://leetcode.com/problems/k-diff-pairs-in-an-array/
Easy: 
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].
*/

/*解题思路
这道题让我们找到数组中所有距离等于K的pair有多少对。这里有几个细节要注意，
第一是不能重复计算一样的。第二是当K等于0的时候，就是计算一下每一个数字
和自己相等的个数是否大于一个。还有一个要注意的就是k是不能小于0的，因为
k是两个数字之间的绝对值。

*/

class Solution {
    public int findPairs(int[] nums, int k) {
        if(k < 0) return 0;
        //map有两个作用，第一个是去重，第二个是计算重复的个数
        Map<Integer, Integer> map  = new HashMap<>();
      
        for(int n: nums){
            map.put(n, map.getOrDefault(n, 0)+1);
        }
        int count=0;

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
        	//两种情况
            if(k!=0){
                if(map.containsKey(entry.getKey()+k)){
                    count++;
                } 
            }else{
                if(map.get(entry.getKey())>1){
                    count++;
                } 
            }
        }
        return count;
    }
}