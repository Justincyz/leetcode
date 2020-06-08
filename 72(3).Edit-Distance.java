/* 72. Edit Distance
Hard
链接: https://leetcode.com/problems/edit-distance/

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

/* DP
题目让我们将word1转成word2, 在转换的过程中我们可以给word1添加，删除或者替换一个字母使之可以对应上word2上面的每一个单词。题目问，这样的转化最少需要多少次才可以将word1转成word2。
这道题目是一个典型的动态规划的问题。我们可以用一个二维的dp数组来表示当word1的前 i 位匹配想要匹配word2的前 j 位的话，需要最少多少次的转换才可以达成。
举个例子，匹配 horse 和 ros
第一行第一列代表坐标，矩阵里面的数字代表进行到这一步匹配最少需要的次数
举个例子，匹配 horse 和 ros
第一行第一列代表坐标，矩阵里面的数字代表进行到这一步匹配最少需要的次数
      h o r s e
    0 1 2 3 4 5
  ------------------
  0|0 1 2 3 4 5
r 1|1 1 2 2 3 4
o 2|2 2 1 2 3 4
s 3|3 3 2 2 2 3

我们word1代表的是"horse", word2代表的是 "ros"。
首先第一行的意思是，"horse"匹配一个空的字符串的话，在每一个字符时需要多少次转换。对于"horse"想要匹配空的字符串，那么我们在每一位都需要进行一个删除的操作，最后删除完整个字符串总共需要5次的删除操作。
第二行代表的意思是，字符串"horse"要匹配字符串"r"的话，最少需要多少次的操作。首先我们看第一个字符'h'是否可以匹配上'r'。我们很容易的观察到不可以。我们首先来判断一个general的情况下如果确定这一位的值呢？
首先我们不管当前两个字符是否匹配，我们先用我们有的三种使之匹配的方式，==第一种==是replace。我们假设这两个字符之前的字符串都是匹配的。那么如果是replace操作的话，那么就是这两个字符之前的所有操作，加上一个replace的操作。那么累加到当前的操作次数就是dp[i-1][j-1]+1。==第二种== 是insert。意思就是给word1的位置 i 上添加word2的 j 上的这个字符。在这个例子里就是 "[==r==]horse"。那么这一种操作的情况下，就是要获取截止到word1的 i 这一位的操作总次数+1，也就是 dp[i-1][j] +1。==第三种==是delete。意思是为了匹配当前word2上的这一位，我们干脆就删掉word1当中的第 i 位，这样因为word1当中的第 i 位不可以用了。我们直接用dp[i][j-1]+1代表的是word2匹配到word1的上一位的值+1。我们首先忽略当前的两个字符是不是可以匹配，我们在这三种值当中取一个最小值。赋予一个当前位的初始值。
此时第二种情况出现了，就是如果当前 word1 中的 i 可以和 word2 中的 j 匹配的话。那么当前的最小值就会是 word1中的第 i-1 和 word2中的 第 j-1位的值和我们赋予的初始值直接做一个比较。然后更新一下当前的值。
这道题目在九章算法的视频里有，忘记的话可以看一下

TIME & SPACE: O(N)
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
