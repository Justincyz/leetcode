/*416. Partition Equal Subset Sum
Medium: 

Given a non-empty array containing only positive integers, find 
if the array can be partitioned into two subsets such that the 
sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
 

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].


Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/

/*解题思路
这道题给了我们一个数组，问我们这个数组能不能分成两个非空子集合，使得两个子集合的元素之和相同.
这是一道经典的dp题目，但是第二次再用recursive来做快很多。可能是因为剪枝的做法。现在把两个
方法都来说一下。
首先来说dp的做法:
我们要先验数组是否可以被分为两边。如果可以的话，那么我们只需要算出原数组的数字之和，然后除以2，
就是我们的target。那么问题就转换为能不能找到一个非空子集合，使得其数字之和为target。们定义
一个一维的dp boolean数组，其中dp[i]表示原数组是否可以取出若干个数字，使其和为i。那么我们最后
只需要返回dp[target]就行了。初始化dp[0]为true，由于题目中限制了所有数字为正数，那么就不用担
心会出现和为0或者负数的情况。关键问题就是要找出状态转移方程了，我们需要遍历原数组中的数字，对于
遍历到的每个数字nums[i]，需要更新dp数组，我们的最终目标是想知道dp[target]的boolean值，就要
想办法用数组中的数字去凑出target。假如现在有一个数字 nums[i]=A, 假设我们要知道能不能组成和为
nums[j]=B的话(A < B < taregt)。那么如果第dp[B-A]是true,换句话说，就是之前的数字组合可以到
达dp[B-A]这一位的话。那么我们就可以在dp[B+A]的基础上继续累加看是否可以达到target。如果之前
dp[j]已经为true了，当然还要保持true，所以还要‘或’上自身，于是状态转移方程如下：

dp[j] = dp[j] || dp[j - nums[i]]    (nums[i] <= j <= target)

这里需要特别注意的是，第二个for循环一定要从target遍历到nums[i]，而不能反过来。因为如果我们从
nums[i]遍历到target的话，假如nums[i]=1的话，那么[1, target]中所有的dp值都是true，因为dp[0]
是true，dp[1]会或上dp[0]，为true，dp[2]会或上dp[1]，为true，依此类推，完全使我们的dp数组失效了。

*/

class Solution {
   public boolean canPartition(int[] nums) {
    int sum = 0;
    
    for (int num : nums) sum += num;
        
    if (sum %2 !=0) return false;
    
    sum /= 2;
    int n = nums.length;
    boolean[] dp = new boolean[sum+1];
    
    dp[0] = true;
    
    for (int num : nums) {
        for (int i = sum; i > 0; i--) {
            if (i >= num) {
                dp[i] = dp[i] || dp[i-num];
            }
        }
    }
    
    return dp[sum];
    }
}
/*
这种back-tracking的办法，对于visit过的位置标记为true。如果加上这一位之后做DFS,无法获得
我们想要的sum的话，就说明这一个dfs不能走。于是将这一位又标回false。代表下一次还可以遍历
*/
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i: nums) sum+=i;
        if(sum%2 !=0) return false;
        Arrays.sort(nums);
        return helper(nums, new boolean[nums.length], 0,nums.length-1, sum/2);
    }
    
    public boolean helper(int[] nums, boolean[] visit, int sum, int index, int target){
        if(index <0 || sum > target || nums[index]>target) return false;
        if(sum == target) return true;
        
        for(int i=index; i>=0; i--){
            if(!visit[i]){
                visit[i] = true;
                if(helper(nums, visit, sum+nums[i], i-1, target)) return true;
                visit[i] = false;
            }
        }
        return false;
    }
}