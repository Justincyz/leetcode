/*1143. Longest Common Subsequence

Medium:
Given two strings text1 and text2, return the length of their longest common subsequence.

A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.

 

If there is no common subsequence, return 0.

Example 1:
Input: text1 = "abcde", text2 = "ace" 
Output: 3  
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*/ 

/*解题思路
这道题目我们还是用dp来做。我们用一个二维int数组来代表两个字符串的
匹配情况。 dp[i][j]指的是 text1匹配到第i位，text2匹配到第j位时，
可以产生的最长公共子序列有多长。
我们还是通过研究字符串的最后两个char来推到一般情况。
假设 text1的最后一个字符是c1，text2的最后一个字符是c2. 
1. 如果 c1==c2，那么最长公共子序列就在 \
dp[i-1][j-1]中，且要加上最后一对匹配字符。所以是dp[i-1][j-1]+1。
2. 如果 c1 != c2， 那么text1和text2可以匹配的最长公共子序列有两种情况，
第一种是不考虑c1的情况下，text1的前 i-1个字符可以和text2的前j个字符进行
匹配，最大的公共子序列产生于此。。
第二种情况是不考虑c2的情况下，text1的前i个字符可以和text2的前j-1个字符进行
匹配，最大的公共子序列产生于此。
还是要注意边界，比如说这道题我们从i=1和j=1开始取值。

这道题的详细讲解可以参考九章算法视频，在第五课
*/
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1.length()==0 || text2.length()==0) return 0;
        char[] t1 = text1.toCharArray();
        char[] t2 = text2.toCharArray();
        int m = t1.length, n = t2.length;
        int[][] dp = new int[m+1][n+1];
        
        for(int i=1; i<=m; i++){
            for(int j =1; j<=n; j++){
                if(t1[i-1] == t2[j-1]){
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
                dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j], dp[i][j-1]));                
            }
        }
        
        return dp[m][n];
    }
}