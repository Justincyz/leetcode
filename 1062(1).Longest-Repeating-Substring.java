/*1062. Longest Repeating Substring
Medium
链接: https://leetcode.com/problems/longest-repeating-substring/

Given a string S, find out the length of the longest repeating substring(s). 
Return 0 if no repeating substring exists.

 

Example 1:
Input: "abcd"
Output: 0
Explanation: There is no repeating substring.

Example 2:
Input: "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.

Example 3:
Input: "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.

Example 4:
Input: "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.
 

Note:
The string S consists of only lowercase English letters from 'a' - 'z'.
1 <= S.length <= 1500

*/

/*解题思路
这个题目比较有意思，居然可以用dp来解。首先我们注意到，这个重复的部分是可以重叠的。
比如"aaaaa"当中，"aaaa"和"aaaa"是互相重叠的。我们利用dp的性质，i 和 j分别代表
字符串S在第i位结尾和第j位结尾的地方，如果这两个地方结尾的字符相同，那么我们就看前一位
的字符是否相同，相同的话前一位就不为0，如果前一位不相同的话在之前的计算当中前一位就
为0，所以我们dp[i][j] = dp[i-1][j-1]+1的意思就是说，最长重复子串的长度又增加了
1个。因为 i, j的值只增不减，所以对于每一位的dp[i][j]来说我们不用和他自己原来的值
相比较。

*/
class Solution {
    public int longestRepeatingSubstring(String S) {
        int n = S.length();
        int[][] dp = new int[n+1][n+1];   
        int res = 0;
        for(int i=1; i<= n; i++){
            for(int j=i+1; j<=n; j++){
                if(S.charAt(i-1) == S.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1]+1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        
        return res;
    }
}


/*这个是自己一开始洗的办法，也是O(N^2)的办法，而且是worst case是O(N^2), 但是估计是在
计算哈希值的时候花费太多时间，只是将将过了时间而已
*/
class Solution {
    public int longestRepeatingSubstring(String S) {

        
        for(int len=S.length()-1; len>=1; len--){
            Set<String> set = new HashSet<>();
            for(int j=0; j<=S.length()-len; j++){
                String sub = S.substring(j, j+len);
                if(set.contains(sub)) return sub.length();
                else set.add(sub);
            }
        }
        
        return 0;
    }
}