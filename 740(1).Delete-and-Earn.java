/*740. Delete and Earn
Medium
链接: https://leetcode.com/problems/delete-and-earn/

Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. 
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.

You start with 0 points. Return the maximum number of points you can earn by 
applying such operations.

Example 1:
Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
 
Example 2:
Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
 

Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].

*/

/*解题思路
我们首先要找到数组中最大的数字，然后按照最大数字的大小建立一个array， 这样的话我们
就可以迅速的知道当我们取了一个数字v之后，v-1和v+1都是不能取的。其次还有一个好处，
对于重复的数字，我们也需要计算这个数字出现的次数，这样我们可以一次性的将这个数字加入
最终的结果中，而不是每次只取一个。接下来的做法其实就和house robber一模一样了，
对于目前添加到的数字i来说，最大数值就是第i-2位的值加上i位的值，或者是i-1位的值。
注意如果要加上当前值得时候，dp[i]位储存的是数字i出现的次数，所以是i*dp[i].

相对于house robber，我们同样也可以将最后的array优化成两个常量代表前i-2位的和的最大值和前i-1
位的和的最大值。

*/

class Solution {
    public int deleteAndEarn(int[] nums) {
        if(nums.length ==0 ) return 0;
        int max = -1;
        for(int num: nums){
            max = Math.max(max, num);
        }    
        int[] dp = new int[max+1];
        for(int i=0; i< nums.length; i++){
            dp[nums[i]]++;
        }
        
        int p1 = 0, p2 = dp[1];
        for(int i=2; i<=max; i++){
            int p3 = Math.max(dp[i]*i+p1, p2);
            p1 = p2;
            p2 = p3;
        }
        
        return p2;
    }
}

class Solution {
    public int deleteAndEarn(int[] nums) {
        if(nums.length ==0 ) return 0;
        int max = -1;
        for(int num: nums){
            max = Math.max(max, num);
        }    
        int[] dp = new int[max+1];
        for(int i=0; i< nums.length; i++){
            dp[nums[i]]++;
        }
        
        int[] res = new int[max+1];
        res[1] = dp[1];
       
        for(int i=2; i<=max; i++){
            res[i] = Math.max(dp[i]*i+res[i-2], res[i-1]);
   
        }
        
        return res[max];
    }
}



