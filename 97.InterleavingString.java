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
首先需要一个二维数组，横轴和纵轴分别是 s1 length+1 和 s2 length+1。
因为最后是要返回一个boolean, 所以 table[][] 是一个二维的boolean数组。
table[i][j] 指的是 s1.charAt(i-1) 和s2.charAt(j-1）到这一步可不可以组合成
s3.substring(0,i+j)。可不可以要看s1和s2在i和j上的char 是否和s3在(i+j-1)
上的char 是一样的。如果s1.charAt(i-1)== s3.charAat(i+j-1)。那么我们要
查找在

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
