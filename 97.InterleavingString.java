/* 97. Interleaving String
Hard
Given s1, s2, s3, find whether s3 is formed by the interleaving 
of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
*/

/*
这道题目还是用dp的思路来做。
首先需要一个二维boolean数组，横轴和纵轴分别是 s1.length+1 和 s2.length+1。
因为最后是要返回一个boolean, 所以 table[][] 是一个二维的boolean数组。
table[i][j] 指的是 s1到第i-1位 和s2到第j-1位可不可以组合成
s3的前i+j位。判断的条件是s1的第i-1或者s2的第j-1位是否和s3的第i+j位一样。
我们只需要知道目前 s1和s2遍历到了哪一位就知道s3应该遍历到哪一位了，因为
最后s3 = s1+s2，长度是一直相等的

举个例子
Given:
s1 = "aabcc",
s2 = "dbbca",
完整的算法，
When s3 = "aadbbbaccc", return F.
0123456789
aadbbbccac

       a  a  b  c  c  
    0  1  2  3  4  5
  0[T  T  T  F  F  F]
d 1[F  F  T  T  F  F]
b 2[F  F  T  T  F  F]
b 3[F  F  T  T  T  T]
c 4[F  F  F  T  T  F]
a 5[F  F  F  F  T  T]

 time : O(m * n)
 space : O(m * n)

九章算法中有详细讲解，第五课
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if ((s1.length()+s2.length())!=s3.length()) return F;

        int m = s1.length();
        int n = s2.length();
        boolean[][] table = new boolean[m+1][n+1];
        table[0][0] = T;
        for(int i=1; i<= m;i++){
            if(s1.charAt(i-1) == s3.charAt(i-1)) table[i][0] = true;    
            else break;
        }

        for(int i=1; i<= n;i++){
            if(s2.charAt(i-1) == s3.charAt(i-1))  table[0][i] = true;   
            else break;
        }

        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                int p = i+j-1; // pointer for the s3 string
                /*如果我们要取 s1的(i-1)位的话，那么要看s1的前(i-2)位和s2的前(j-10位能否组合成为s3的前i+j-1位*/
                if(s1.charAt(i-1) == s3.charAt(p)&& table[i-1][j] == true) table[i][j] = true;
                if(s2.charAt(j-1)== s3.charAt(p) && table[i][j-1] == true) table[i][j] = true;
            }
        }
        return table[m][n];
    }
}



class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        int m = str1.length;
        int n = str2.length;
        if((m+n) != s3.length()) return false;
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        
        
        for(int i=0; i<=m; i++){
            for(int j=0; j<=n; j++){

                if(i>0 && str1[i-1] == str3[i+j-1]){
                    dp[i][j] |= dp[i-1][j];
                }
                
                if(j>0 && str2[j-1] == str3[i+j-1]){
                    dp[i][j] |= dp[i][j-1];
                }
            }
        }
    
        
        return dp[m][n];
    }
}