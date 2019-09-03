/*115. Distinct Subsequences
Hard: 
Given a string S and a string T, count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Example 1:
Input: S = "rabbbit", T = "rabbit"
Output: 3
Explanation:

As shown below, there are 3 ways you can generate "rabbit" from S.
(The caret symbol ^ means the chosen letters)

rabbbit
^^^^ ^^
rabbbit
^^ ^^^^
rabbbit
^^^ ^^^

Example 2:
Input: S = "babgbag", T = "bag"
Output: 5
Explanation:

As shown below, there are 5 ways you can generate "bag" from S.
(The caret symbol ^ means the chosen letters)

babgbag
^^ ^
babgbag
^^    ^
babgbag
^    ^^
babgbag
  ^  ^^
babgbag
    ^^^
*/

/*解题思路
看到有关字符串的子序列或者配准类的问题，首先应该考虑的就是用动态规划 Dynamic Programming 来求解，这个应成为条件反射。而所有 DP 问题的核心就是找出状态转移方程，这道题就是递推一个二维的 dp 数组，其中 dp[i][j] 表示s中范围是 [0, i] 的子串中能组成t中范围是 [0, j] 的子串的子序列的个数。下面我们从题目中给的例子来分析，这个二维 dp 数组应为：


  Ø r a b b b i t
Ø 1 1 1 1 1 1 1 1
r 0 1 1 1 1 1 1 1
a 0 0 1 1 1 1 1 1
b 0 0 0 1 2 3 3 3
b 0 0 0 0 1 3 3 3
i 0 0 0 0 0 0 3 3
t 0 0 0 0 0 0 0 3 

 
首先，若原字符串和子序列都为空时，返回1，因为空串也是空串的一个子序列。若原字符串不为空，而子序列为空，也返回1，因为空串也是任意字符串的一个子序列。而当原字符串为空，子序列不为空时，返回0，因为非空字符串不能当空字符串的子序列。理清这些，二维数组 dp 的边缘便可以初始化了，下面只要找出状态转移方程，就可以更新整个 dp 数组了。我们通过观察上面的二维数组可以发现，当更新到 dp[i][j] 时，dp[i][j] >= dp[i][j - 1] 总是成立，再进一步观察发现，当 T[i - 1] == S[j - 1] 时，dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1]，若不等， dp[i][j] = dp[i][j - 1]，所以，综合以上，递推式为：

dp[i][j] = dp[i][j - 1] + (T[i - 1] == S[j - 1] ? dp[i - 1][j - 1] : 0)

*/


class Solution {
    public int numDistinct(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        int m = s1.length, n = t1.length;
        int[][] dp = new int[n+1][m+1];
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
            	//如果t的长度为0, 则在s中出现一次
                if(i==0){
                    dp[i][j] =1;
                    continue;
                }
                //如果s的长度为0，则t在s中出现一次
                if(j==0){
                    dp[i][j] =0;
                    continue;
                }
                //默认情况是 t[j-1] is not connected with s[i-1]
                dp[i][j] = dp[i][j-1];

                //t[j-1] is connected with s[i-1]
                if(s1[j-1]==t1[i-1]) dp[i][j] += dp[i-1][j-1];  
            }
        }
        
        return dp[n][m];
    }
}


//利用滚动数组降低一个维度
class Solution {
    public int numDistinct(String s, String t) {
       
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        int m = s1.length, n = t1.length;
        int[] dp = new int[m+1];

        
        for(int i=0; i<=n; i++){
            int[] temp = new int[m+1];
            for(int j=0; j<=m; j++){
                if(i==0){
                    temp[j] =1;
                    continue;
                }
                
                if(j==0){
                    temp[j] =0;
                    continue;
                }
                temp[j] = temp[j-1];
                if(s1[j-1]==t1[i-1]) temp[j] += dp[j-1];  
            }
            dp = temp;
        }
        
        return dp[m];
    }
}