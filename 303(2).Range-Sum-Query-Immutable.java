/*303. Range Sum Query - Immutable
链接：https://leetcode.com/problems/range-sum-query-immutable/
Easy: 
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.
*/

/*解题思路
这一道题的考察点就是pre-sum的用法，注意这里的区间范围是左右闭合的。
每一个值都要取到

*/

class NumArray {
    int[] preSum;
    public NumArray(int[] nums) {
        preSum = new int[nums.length+1];
        
        for(int i=1; i<= nums.length; i++){
            preSum[i]= (preSum[i-1]+nums[i-1]);        
        }
    }
    
    public int sumRange(int i, int j) {
        return preSum[j+1]-preSum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */