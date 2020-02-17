/*238. Product of Array Except Self
链接：https://leetcode.com/problems/product-of-array-except-self/submissions/
Medium: 
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:(其实还是需要一个int[n]的extra space,只不过把这个当做output而已)
Could you solve it with constant space complexity? 
(The output array does not count as extra space for 
the purpose of space complexity analysis.)
*/

/*解题思路
这道题让我们得到这样一个新的数组，数组中每个元素都是原数组中除当前位以外的
所有数的乘积。比如题目当中给出的例子
Input:  [1,2,3,4]
Output: [24,12,8,6]
第一个24 = 2x3x4
第二个12 = 1x3x4
第三个9 = 1x2x4
第四个6 = 1x2x3
首先我们可以用类似于preSum的办法来做这道题目
新建一个数组，数组中每一位数都是当前位以前所有数的乘积。我们再从后往前
用同样的方法来错位获得当前数从后往前累计的乘积。与原数组相乘就可以获得
*/


class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] dp = new int[nums.length];
        
        int presum = nums[0];
        dp[0] =1;
        for(int i=1; i<nums.length; i++){
            dp[i] = presum;
            presum*=nums[i];
        }
        presum = 1;
        for(int i=nums.length-1; i>=0; i--){
            dp[i] *= presum;
            presum*=nums[i];
        }
        
        return dp;
    }
}
//也可以这样写，看起来比较简单
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] list = new int[nums.length];
        int total = 1;
        for(int i=0; i< nums.length; i++){
            total*= nums[i];
            list[i]  = total;
        }
        
        total = 1;
        //注意因这里i>0, 最后处理第0位
        for(int i=nums.length-1; i>0; i--){
            int t = nums[i];
            nums[i] = total*list[i-1];
            total*=t;
        }
        nums[0] = total;
        return nums;
    }
}