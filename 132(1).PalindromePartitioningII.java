/*132. Palindrome Partitioning II
Hard: 

Given a string s, partition s such that every substring of the 
partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced 
using 1 cut.

*/

/*解题思路
题目问我们给定一个字符串，问通过最少几次分割可以将其分割为子字符串都是回文形式。这道题目
比较明显是用dp来做，其实方法不难想，重要的是如何缩短查找回文字符串的时间。
首先我们需要一个 min[]来记录从0到s.length以每一个字符结尾的最少切割次数。同时我们
需要一个二维数组记录[i, j]这一段是否为回文字符串。每一次我们都从头遍历到当前字符i。
对于每一个子字符串[j, i]，如果 s.charAt[i] == s.charAt[j]且[i-1, j-1]已经是
回文的话，那么左右加上j和i指向的字符所构成的字符串也会是回文形式。注意判断边界条件，
如果 i和j的长度小于等于2的话直接就判断 charAt(i)和charAt(j)了

*/

class Solution {
    public int minCut(String s) {
        boolean dp[][] = new boolean[s.length()+1][s.length()+1];
        int[] min = new int[s.length()];
        for(int i=0; i< s.length();i++){
            int m = i;
            for(int j=0; j<=i;j++){
                if(s.charAt(i)==s.charAt(j) &&(j+1 >= i || dp[j+1][i-1] == true)){
                    dp[j][i] = true;
                    m = j==0?0 :Math.min(m, min[j-1]+1);
                }
            }
            min[i] = m;
        }
        return min[s.length()-1];
    }
}