/*268. Missing Number
链接：https://leetcode.com/problems/missing-number/
Easy: 
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

/*解题思路
我用的是最简单的办法，就是把所有的元素放到自己正确的位置上，如果某一个元素
的值大于数组的长度(不应存在在原有完美对应的数组中)，我们就不管他。那么最后
遍历一次数组，第一个位置和元素大小不一样的数字就是我们要找的missing number


*/
class Solution {
    public int missingNumber(int[] nums) {
        for(int i=0; i<nums.length; i++){
            dfs(nums, nums[i]);
        }
        
        for(int i=0; i<nums.length; i++){
            if(nums[i] != i) return i;
        }
        return nums.length;
    }
    
    public void dfs(int[] nums, int num){
        if(num >= nums.length || nums[num] == num) return;
        int t = nums[num];
        nums[num] = num;    
        dfs(nums, t);
    }    
}

/*
这个解法比较屌
The basic idea is to use XOR operation. We all know that a^b^b =a, which means two xor operations with the same number will eliminate the number and reveal the original number.
In this solution, I apply XOR operation to both the index and value of the array. In a complete array with no missing numbers, the index and value should be perfectly corresponding( nums[index] = index), so in a missing array, what left finally is the missing number.
*/

public int missingNumber(int[] nums) {
	int res = nums.length;
    for(int i=0; i<nums.length; i++){
        res = res ^ i ^ nums[i]; // a^b^b = a
    }
    return res;
}