/* 303.RangeSumQueryImmutable
Easy

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
*/

//用的就是pre-sum的方法
class NumArray {
    int[] table;
    public NumArray(int[] nums) {
        table  = new int[nums.length+1];
        int preSum = 0;
        table[0] = 0;
        for(int i=1; i<= nums.length; i++){
            preSum+=nums[i-1];
            table[i] = preSum;
            
        }
    }
    
    public int sumRange(int i, int j) {
        return table[j+1]-table[i];
    }
}