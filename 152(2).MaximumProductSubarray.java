/*152. Maximum Product Subarray
Medium: 
Given an integer array nums, find the contiguous subarray within an 
array (containing at least one number) which has the largest product.

Example 1:
Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:
Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

/*解题思路
题目的意思就是要我们找到一个连续的sub-array, 使得这个连续的sub-array所有的值乘积最大。
这道题目做了两次，属于很经典的一个DP题目。可以参考DP_lecture 1.pdf 第55页。对于数组
中的每一个数字a[j]来说，假设包括这个数字后乘积最大且当前数字是sub-array中的最后一个数。那么
分为两种情况，第一，这个数字本身就是最大的sub-array。 第二，假设sub-array长度大于1，那么
肯定需要包含a[j-1].假设a[j]是正数，那么我们希望以a[j-1]结尾的连续子子序列乘积最大。相反，
如果a[j]是负数，那么我们希望以a[j-1]结尾的连续子序列乘积最小。这个问题于是就转换成了两个问题:
求以a[j]结尾的连续子序列的最大乘积和以a[j]结尾的连续子序列的最小乘积。两种情况都需要求以
a[j-1]结尾的乘积最大/最小连续子序列。所以我们需要两个数组，代表最大序列和最小序列的数组。

mx[i] = Math.max(nums[i], Math.max(nums[i]*mx[i-1], nums[i]*mn[i-1]));
 |                 |							|
以a[j]结尾的       情况1: 子序列					情况2: 以a[j-1]结尾的连续
连续子序列的		  就是a[j]本身					子序列的最大/最小乘积, 乘上a[j]
最大乘积


对于mn[i]数组同理
*/


class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        int n = nums.length, res =nums[0];
        int[] mx = new int[n], mn = new int[n];//代表最大序列和最小序列的数组
        mx[0] = nums[0];
        mn[0] = nums[0];
        
        for(int i=1; i<n; i++){
            mx[i] = Math.max(nums[i], Math.max(nums[i]*mx[i-1], nums[i]*mn[i-1]));
            mn[i] = Math.min(nums[i], Math.min(nums[i]*mx[i-1], nums[i]*mn[i-1]));
            res = Math.max(res, mx[i]);
        }
        
        return res;
    }
}

/*
我们发现每一次其实只对前一位数有所依赖，所以可以把两个array压缩成两个变量
*/

class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, tempMax = 1, tempMin = 1;
        
        for(int i: nums){
            int temp = tempMax;
            tempMax = Math.max(Math.max(tempMax * i, tempMin *i), i);
            tempMin = Math.min(Math.min(temp * i, tempMin* i), i);
            max = Math.max(max, tempMax);
        }
        
        return max;
    }
}
