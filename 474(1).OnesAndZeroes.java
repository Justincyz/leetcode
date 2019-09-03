/*474. Ones and Zeroes
Medium: 
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:

The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.

Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 

Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
*/

/*解题思路
这道题目的详细讲解可以参考九章算法DP章节的视频。题目告诉我们在一个字典中，
字典内所有字符串都是由0和1组成的。让我们利用有限
的字符0和字符1，看最多可以利用这些0和1构成多少个字典内的字符串。
我们建立一个三维的dp数组，三维代表前i个字符串使用j个0个p个1可以最多组成
字典内字符串的个数。还是比较显而易见的。

*/
//时间复杂度O(kmn)，空间复杂度O(kmn)
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        int[][][] dp = new int[k+1][m+1][n+1];
        
        for(int i=1; i<=k;i++){
            int zeros =0, ones = 0;
            String s = strs[i-1];

            //计算这一个string 可以产生的0的数目和1的数目
            for(int j=0; j<s.length();j++){
                if(s.charAt(j)=='0') zeros++;
                else ones++;
            }
           
            for(int j =0; j<=m; j++){
                for(int p=0; p<= n; p++){
                    dp[i][j][p] = dp[i-1][j][p]; //先假设不选当前这个单词

                    if(j < zeros || p < ones) continue;
            
                    dp[i][j][p] = Math.max(dp[i][j][p], dp[i-1][j-zeros][p-ones]+1);
                }
            }

        }
        return dp[k][m][n];
    }
}


//时间复杂度O(kmn)，空间复杂度优化到O(mn)

class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        int[][][] dp = new int[2][m+1][n+1]; //第一维降为成2了
        
        int now = 0, old =0;
        for(int i=1; i<=k;i++){
            old = now;
            now = 1-now;
            
            int zeros =0, ones = 0;
            String s = strs[i-1];
            for(int j=0; j<s.length();j++){
                if(s.charAt(j)=='0') zeros++;
                else ones++;
            }
           
            for(int j =0; j<=m; j++){
                for(int p=0; p<= n; p++){
                    dp[now][j][p] = dp[old][j][p];
                    if(j < zeros || p < ones) continue;
            
                    dp[now][j][p] = Math.max(dp[now][j][p], dp[old][j-zeros][p-ones]+1);
                }
            }

        }
        return dp[now][m][n];
    }
}