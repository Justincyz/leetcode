/*198. House Robber
Easy

不能取连续两个相邻的nums[]中的数
Given a list of non-negative integers representing the 
amount of money of each house, determine the maximum amount 
of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
*/

/*
有几种方法来做这道题。时间都是很快的
*/

//循环with memorization, 0ms, beats 100%. 时间和空间都是O(n)
class Solution {
    int[] table;
    public int rob(int[] nums) {
        table = new int[nums.length];
        Arrays.fill(table, -1);//要考虑诠释0的情况，只能说nums每一个数字不为负
        return helper(nums, 0);
    }
    
    public int helper(int[] nums, int pos){
        if(pos >=nums.length) return 0;
        if(table[pos] !=-1) return table[pos];
        int res = Math.max(nums[pos]+ helper(nums, pos+2), helper(nums, pos+1));
        table[pos] = res;
        return res;
    }
}

class Solution {
    public int rob(int[] nums) {
        int prevNotRob = 0; 
        int prevRob = 0; 
        
        for(int num:nums){
            // If we rob current cell, previous cell shouldn't be robbed. So, add the current value to previous one.
            int curRob = prevNotRob+num;
            // If we don't rob current cell, then the count should be max of the previous cell robbed and not robbed
            int notRob = Math.max(prevRob, prevNotRob);
            prevNotRob = notRob;
            prevRob = curRob;
        }
        return Math.max(prevNotRob, prevRob);
    }
}
       