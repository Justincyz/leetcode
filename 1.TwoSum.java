/*1. Two Sum
Easy

Given an array of integers, return indices of the two numbers 
such that they add up to a specific target.

You may assume that each input would have exactly one solution, 
and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
*/

/*解题思路
像这种简单的题目一点要注意细节，比如说这是返回indices而不是number, 
所以要用hashmap而不是hashset

*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i=0; i< nums.length; i++){
            if(map.containsKey(target - nums[i])){
                int j = map.get(target-nums[i]);
                 return new int[]{j, i};
            }
            map.put(nums[i], i);
        }
        return new int[2];
    }
}
