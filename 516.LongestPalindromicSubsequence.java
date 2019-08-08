/* 516.Longest PalindromicS ubsequence
Medium
Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".
*/
/*讲解: https://www.youtube.com/watch?v=_nCsPn7_OgI
table[i][j] 指的是string s 从第i位到第j位这个范围之内可以取得的最大
回文长度
*/
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] table = new int[n][n];
       
        for(int len=1; len<=n; len++){ //注意这里的是长度
            for(int i=0; i<=n-len;i++){
                int j = i+len-1;
                if(i==j){  //代表了单个的字符串
                    table[i][j] = 1;
                    continue;
                }
                if(s.charAt(i)==s.charAt(j)){
                    table[i][j] = table[i+1][j-1]+2;
                }else{
                    /*这里指的是i,j不匹配，那么到i,j这一位最长的回文结构要不就是
                    出现在从i+1到j这一段，要不就是i到j-1这一段*/
                    table[i][j] = Math.max(table[i][j-1], table[i+1][j]);
                }
            }
        }
        return table[0][n-1];
    }
}
/*这个使用了滚动数组，是最优解, 之所以要从后往前遍历可以看二维的图。
例子: agbdba, longest: abdba

     a g b d b a 
     0 1 2 3 4 5
-----------------
 0 | 1 1 1 1 3 4
 1 |   1 1 1 3 3
 2 |     1 1 3 3
 3 |       1 1 1
 4 |         1 1
 5 |           1

因为左下部分是空的，所以从下往上遍历起来是最快的。 这也是为什么i要从
s.length()-1 开始往上。
  */
class Solution {
    public int longestPalindromeSubseq(String s) {
    int[] dp = new int[s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
        dp[i] = 1;
        int pre = 0;
        for (int j = i + 1; j < s.length(); j++) {
            int tmp = dp[j];
            if (s.charAt(i) == s.charAt(j)) {
                dp[j] = pre + 2;
            }
            else {
                dp[j] = Math.max(dp[j], dp[j - 1]);
            }
            pre = tmp;
        }
    }

    return dp[s.length() - 1];
    }
}

