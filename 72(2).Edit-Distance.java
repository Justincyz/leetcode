/* 72. Edit Distance
Hard
Given two words word1 and word2, find the minimum number of operations 
required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
*/

/*
举个例子，匹配 horse 和 ros
第一行第一列代表坐标，矩阵里面的数字代表进行到这一步匹配最少需要的次数
      h o r s e
    0 1 2 3 4 5
  ------------------
  0|0 1 2 3 4 5
r 1|1 1 2 2 3 4
o 2|2 2 1 2 3 4
s 3|3 3 2 2 2 3

word1 -> word2.  word1 = (0<i<=n), word2 =(0<j<=m). 所谓table[i][j-1]
指的是insert一个新的char 到word1中以便match第j位的word2. table[i-1][j]指的是直接remove 
word1中的第i位. table[i-1][j-1]指的是在如果上次 word1[i] 和 word2[j] match的话，
对当前位做一个replacement. 我们已经保证了在 i 和 j之前已经做了x 次的match了，所以只要考虑当前次基于
哪一种更快。
这道题目在九章算法的视频里有，忘记的话可以看一下
*/
//时间复杂度 O(n^2), 空间O(n^2). 下面那一种方法可以用滚动数组降维成O(n)空间复杂度
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        
        int[][] res = new int[len1+1][len2+1];
        for(int i = 0; i <= len1; i++) res[i][0] = i;
            
        for(int i = 0; i <= len2; i++) res[0][i] = i;

        for(int i=1; i<= len1; i++){
            for(int j =1; j<= len2; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    res[i][j] = res[i-1][j-1];
                }else{
                    res[i][j] = Math.min(Math.min(res[i-1][j], res[i][j-1]), res[i-1][j-1])+1;
                }
            }
        }
        return res[len1][len2];  
    }
}

//使用了滚动数组降低了一个维度
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[] res = new int[len2+1];
        for(int i = 0; i <= len2; i++){
            res[i] = i;
           
        }
     
        for(int i=1; i<= len1; i++){
            int prev = i;
            for(int j =1; j<= len2; j++){
                int cur;
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    cur = res[j-1];
                }else{
                    cur = Math.min(Math.min(res[j], res[j-1]), prev)+1;
                }
                res[j-1] = prev;
                prev = cur;
            }
            res[len2] = prev;
        }
        return res[len2];
    }
}

//时隔两个月的第三次做法
class Solution {
    public int minDistance(String word1, String word2) {
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int m = w1.length, n = w2.length;
        int[][] dp = new int[n+1][m+1];
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0){
                    dp[i][j] = j;
                    continue;
                }                 
                if(j==0){
                    dp[i][j] = i;
                    continue;
                }
                
                dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;
                if(w2[i-1] == w1[j-1]) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
            }
        }
        
        return dp[n][m];
    }
}
