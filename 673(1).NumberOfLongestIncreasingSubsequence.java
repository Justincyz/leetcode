/*673. Number of Longest Increasing Subsequence
Medium: 
Given an unsorted array of integers, find the number of longest increasing 
subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and 
[1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, 
and there are 5 subsequences' length is 1, so output 5.

*/

/*解题思路
这道题给了我们一个数组，让我们求最长递增序列的个数。还是先贴一个大神的解释,"我们将dp[i]定义为
以nums[i]为结尾的递推序列的个数的话，再配上这些递推序列的长度，将会比较容易的发现递推关系。
这里我们用len[i]表示以nums[i]为结尾的递推序列的长度，用count[i]表示以nums[i]为结尾的递推
序列的个数，初始化都赋值为1，只要有数字，那么至少都是1。然后我们遍历数组，对于每个遍历到的数
字nums[i]，我们再遍历其之前的所有数字nums[j]，当nums[i]小于等于nums[j]时，不做任何处理，
因为不是递增序列。反之，则判断len[i]和len[j]的关系，如果len[i]等于len[j] + 1，说明nums
[i]这个数字可以加在以nums[j]结尾的递增序列后面，并且以nums[j]结尾的递增序列个数可以直接加
到以nums[i]结尾的递增序列个数上。如果len[i]小于len[j] + 1，说明我们找到了一条长度以nums[i]结尾
更长的递增序列，那么我们此时将len[i]更新为len[j]+1，并且因为更新了最长值，所以原本的递增序列
不能用了，直接将count[j]赋值给count[i]。我们在更新完len[i]和count[i]之后，要更新max和res，
如果max等于len[i]，则把cnt[i]加到res之上；如果mx小于len[i]，则更新max为len[i]，更新结果
res为cnt[i]，参见代码如下：

*/

class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums.length==0) return 0;
        int n = nums.length, res = 0, max = 0;        
        int[] count = new int[n];
        int[] len = new int[n];
        
        
        for(int i=0; i<n; i++){
            count[i] =1;
            len[i] = 1;
            for(int j=0; j<i; j++){
                if(nums[j]>= nums[i]) continue;
                if(len[i]==(len[j]+1)){
                    count[i] +=count[j];
                }else if(len[i] <(len[j]+1)){
                    len[i] = len[j]+1;
                    count[i] = count[j];
                }
            }
            if(max == len[i]) res+=count[i];
            else if(max < len[i]){
                max = len[i];
                res = count[i];
            }
        }

        return res;
    }
}