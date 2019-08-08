/*198. House Robber
Easy
You are a professional robber planning to rob houses along a 
street. Each house has a certain amount of money stashed. All
 houses at this place are arranged in a circle. That means the 
 first house is the neighbor of the last one. Meanwhile, adjacent 
 houses have security system connected and it will automatically 
 contact the police if two adjacent houses were broken into on the
  same night.

Given a list of non-negative integers representing the amount of 
money of each house, determine the maximum amount of money you can
rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house
3 (money = 2),because they are adjacent houses.

Example 2:
Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 
(money = 3). Total amount you can rob = 1 + 3 = 4.
*/

/*解法
其实这道题就是house robber I 的条件下加上了一个首尾不能同时取，因为所有
房子相连成环。抢了最后一个房子也就不能抢第一个了。所以解法其实非常简单，
把这个环拆成两段就可以了。从0到house.length-2和1到house.length-1。这样相当于
两条无环的直线。运算两次然后比较每一次的最大值就可以了。 runtime beats 100%
*/

class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int maxRob =0;
        
        maxRob = helper(nums, 0, nums.length-1);
        maxRob = Math.max(maxRob, helper(nums, 1, nums.length));
        return maxRob;
    }
    
    public int helper(int[] nums, int start, int end){
        int preRob=0, preNotRob = 0;
        
        for(int i=start; i<end; i++){
            int curRob = preNotRob+nums[i];
            int curNotRob = Math.max(preRob, preNotRob);
            preRob = curRob;
            preNotRob = curNotRob;
        }
        return Math.max(preRob, preNotRob);
    }
}


