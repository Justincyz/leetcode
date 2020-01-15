/*45. Jump Game II
Hard
链接: https://leetcode.com/problems/jump-game-ii/
Given an array of non-negative integers, you are initially positioned at 
the first index of the array.

Each element in the array represents your maximum jump length at that 
position.

Your goal is to reach the last index in the minimum number of jumps.

Example:
Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.

*/

/*解题思路
这道题让我们找从第一个元素跳到最后一个元素的最小跳跃次数，数组中每一个数字代表在
这一位上最远的跳跃距离。这道题一开始我想的是用dp来做，对于每一位数我们都往前去
搜索所有的位，如果某一位可以跳跃到当前位的话，就更新当前位的跳跃次数。这样的话
时间复杂度是O(N^2),结果TLE了。
这道题最优解是O(N),思路不难，就是要想得到才可以。我们从第一位出发，检查在弹跳
一次之后，可以跳跃到最远的距离是多少。然后这一个范围之内的所有跳跃点，都是当前
跳跃区间可以够到的，那在这一个跳跃区间内再计算下一个最远的跳跃点，每一次到达了
当前跳跃点的边界时，我们就更新一下jump++，并且将下一个跳跃范围赋值给当前跳跃范围。
这里为了加速可以判断一下，如果下一个跳跃范围已经超过了数组长度，那就直接返回jump
就可以了

*/

class Solution {
    public int jump(int[] nums) {
        int n = nums.length, curRange =0, nextRange = 0, jump = 0;
       
        for(int i=0; i<n-1; i++){
            nextRange = Math.max(nextRange, nums[i]+i);
            if(curRange == i){
                jump ++;
                curRange = nextRange;
                if(curRange >= n) return jump;
                nextRange = 0;
            }
        }
        return jump;
    }
}


//这是我一开始想的办法，
class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
       
        dp[0] = 0;
        for(int i=1; i<n-1; i++){
            for(int j=0; j<i; j++){
                if(nums[j]+j>=i){
                    dp[i] = Math.min(dp[i], dp[j]+1);
                    
                } 
                
            }
        }
        return dp[n-1];
    }
}
