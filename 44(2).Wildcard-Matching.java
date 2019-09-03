/*44. Wildcard Matching
Hard: 
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:
Input:
s = "acdcb"
p = "a*c?b"
Output: false
*/

/*解题思路  相似题目: 10, Regular Expression Matching
首先这道题目在九章dp里面有详细的讲解。
相比较于Regular Expression Mathcing这道题来说，这个简单了不少。我们还是通过
分析两个string最后两个字符来推倒可能出现的一般情况。如果S和P的最后两个字符可以
match，或者P的最后一个字符是?. 那么我们直接查看S[n-1]和P[k-1]是否是true就可以了。
如果P的最后一个字符是星号的话，那么default就假设我们不要这个星，因为星是可以match 0
个字符到无限多的字符的。所以是dp[i][j] |= dp[i][j-1]。除此之外加入星号是要匹配当前
s的最后一个字符的话，那么如果s的前一个字符可以被匹配成功，那当前位也可以被匹配成功。如果
前一位本身就无法匹配，那当前位即使可以匹配，到前一位时也断掉了。最后返回的是dp[m][n]

*/



class Solution {
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();
        
        int m = str.length, n = pat.length;
        boolean[][] dp = new boolean[m+1][n+1];
        
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(i==0 && j==0){
                    dp[i][j] = true;
                }
                
                if(j==0) continue;
                
                if(pat[j-1] !='*'){
                    if(i>0 && (str[i-1] == pat[j-1] || pat[j-1]=='?')){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }else{
                    dp[i][j] |= dp[i][j-1];
                    //还是要注意判断这个边界问题
                    if(i>0)
                        dp[i][j] |= dp[i-1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}