/*53. Maximum Subarray
Easy: 
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

/*解题思路
这道题让我们求最大子数组之和，并且要我们用两种方法来解，分别是O(n)的解法，还有用分治法Divide and Conquer Approach，这个解法的时间复杂度是O(nlgn)，那我们就先来看O(n)的解法，定义两个变量res和curSum，其中res保存最终要返回的结果，即最大的子数组之和，curSum初始值为0，每遍历一个数字num，比较curSum + num和num中的较大值存入curSum，然后再把res和curSum中的较大值存入res，以此类推直到遍历完整个数组，可得到最大子数组的值存在res中，

*/

//这个相当于利用了滚动数组的算法
class Solution {
    public int maxSubArray(int[] nums) {
        int curSum = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0; i<nums.length; i++){
            curSum = Math.max(curSum+nums[i],nums[i]);
            max = Math.max(max, curSum);
        }
        return max;
    }
}


//这个是标准的dp做法,但是改变了原来数组的元素
class Solution {
    public int maxSubArray(int[] nums) {
        int max= nums[0];
        for(int i=1; i< nums.length; i++){
            nums[i] = Math.max(nums[i], nums[i] + nums[i-1]);
            if(nums[i]>max){
                max = nums[i];
            }
        }
       
        return max;
    }
}



/*
题目还要求我们用分治法Divide and Conquer Approach来解，这个分治法的思想就类似于二分搜索法，我们需要把数组一分为二，分别找出左边和右边的最大子数组之和，然后还要从中间开始向左右分别扫描，求出的最大值分别和左右两边得出的最大值相比较取最大的那一个：
*/

public class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);
        int rmax = helper(nums, mid + 1, right);
        int mmax = nums[mid], t = mmax;
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
}