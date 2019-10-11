/*713. Subarray Product Less Than K
链接：https://leetcode.com/problems/subarray-product-less-than-k/
Medium: 
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
*/

/*解题思路
这道题给了我们一个数组和一个数字K，让我们求子数组且满足乘积小于K的个数。既然是子数组，那么
必须是连续的，所以肯定不能给数组排序了，这道题好在限定了输入数字都是正数，能稍稍好做一点。我们
使用是一种滑动窗口的解法，维护一个数字乘积刚好小于k的滑动窗口，用变量left来记录其左边界的位置，
右边界i就是当前遍历到的位置。遍历原数组，用 prod 乘上当前遍历到的数字，然后进行 while 
循环，如果 temp 大于等于k，则滑动窗口的左边界需要向右移动一位，删除最左边的数字，那么少了一个数字，乘积就会改变，所以用 temp 除以最左边的数字，然后left右移一位.当我们确定了窗口的大小后，就可以统计子数组的个数了，就是窗口的大小。为啥呢?
我们推倒一下，假设我们设置K为无限大(就是不考虑k的大小而已)。假设数组是[1,2,3,4,5]。
如果是
长度为1，就是1. [1], 一个1 (长度为1)
长度为2，就是2, [1],[2],[1,2]， 两个1， 一个2
长度为3，就是3, [1],[2],[3],[2,3],[1,2],[1,2,3]， 三个1，两个2， 一个3
长度为4，到了4的时候就会增加四个，为[4], [3,4],[2,3,4],[1,2,3,4]

。所以窗口每次向右增加一个数字，然后左边去掉需要去掉的数字后，窗口的大小就是新的子数组的个数，每次加到结果 total 中即可：
*/
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k ==0) return 0;
        int total =0;
        int l = 0, r = 0, n = nums.length;
        
        int temp =1;
        
        while(r < n){
            temp*=nums[r];            
            while(l<=r && temp >=k){
                temp/=nums[l++];
            }
            
            total+=(r-l+1);
            r++;
        }


        return total;
    }
}