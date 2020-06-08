/*169. Majority Element
Easy
链接: https://leetcode.com/problems/majority-element/

Given an array of size n, find the majority element. The majority element is the 
element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist 
in the array.

Example 1:
Input: [3,2,3]
Output: 3

Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2


*/

/*解题思路
这道题虽然是easy的题目，但是还是很有意思的。因为题目保证了数组中一定有一个majority
元素，所以我们就带着majority这个元素一直往下走就可以了，如果当前元素和major不一样，就把
count -1, 如果一样就加一。如果count 变成0的时候，说明在这之前所有的元素已经抵消掉了。
那我们就重新开始计算。

*/

class Solution {
    public int majorityElement(int[] nums) {
        int major = nums[0], count =1;
        
        for(int i=1; i< nums.length; i++){
            if(count == 0){
                major = nums[i];
                count++;
                continue;
            }
            if(major == nums[i]){
                count++;
            }else{
                count --;
            }
        }
        return major;
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        int num =nums[0], count = 0;
        for(int i=0; i<nums.length; i++){
            if(num == nums[i]) count++;
            else{
                count--;
                if(count == 0){
                    if(i+1 < nums.length){
                        num = nums[i+1];
                    }
                }
            }
        }
        return num;
    }
}

//用hashmap会比较慢，但是可以针对题目中不一定有major元素的情况
class Solution {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0)+1);
            if(map.get(num) > nums.length/2) return num;
        }
        return -1;
    }
}