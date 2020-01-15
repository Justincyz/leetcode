/*https://leetcode.com/problems/3sum-closest/
Medium
链接: https://leetcode.com/problems/3sum-closest/

Given an array nums of n integers and an integer target, find three 
integers in nums such that the sum is closest to target. Return the 
sum of the three integers. You may assume that each input would have 
exactly one solution.

Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).


*/

/*解题思路
这道题相对于找到3-sum的那道题目来说简单很多，因为题目告诉我们肯定只有一个答案，
所以也不用担心像3-sum里面去重的问题一样。只需要在最终结果之间上下波动就可以了。

*/

class Solution {
    public int threeSumClosest(int[] nums, int target) {
       
        Arrays.sort(nums);
        int res =nums[0]+nums[1]+nums[2];
        for(int i=0; i< nums.length-2; i++){
            int l = i+1, r = nums.length-1;
            while(l < r){
                int total = nums[i]+nums[l]+nums[r];
                if(Math.abs(target-total) < Math.abs(target-res)){
                    res = total;
                }
                
                if(total > target){
                    r--;
                }else{
                    l++;
                }
            }
        }
        return res;
    }
}