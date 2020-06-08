/*1035. Uncrossed Lines
Medium
链接: https://leetcode.com/problems/uncrossed-lines/

We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

Example 1:
Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.

Example 2:
Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3

Example 3:
Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2
 

Note:
1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
*/



/*解题思路  DP
这道题目挺有意思的，给我们两个数组，让我们给两个数组中相同的元素进行连线，每一个元素最多可以被连一次。同时这些连线不可以交叉，比如说 [1,2,3] 和[3,2,1] 两个数组，当两个数组当中的1都链接在一起之后，2和3就不可以互相连线了。因为这样会导致他们和两边1的连线交叉。同理，如果2或者3和对方连线了，那么其余的也不可以连线了。因为这样他们也交叉了。

一开始我想的办法特别的复杂。因为两边都需要保持不交叉的连线。我就使用了两个指针。这样会有三种不同的情况出现。
(1) 我们选择数组A中的 A[idxA]元素作为基准，然后在数组B当中找到在idxB之后，且位置最小的，和A[idxA]相同的元素。
(2) 以数组B 中的A[idxB]元素作为基准，然后同样的方法看能不能在A中找到符合条件的元素。
(3) A和B中的元素我们都不选，而是直接将两个指针向后移动一位，相当于忽略了这两个元素。

但是后来转念一想，看了一眼自己的草稿。发现实际上这不就是找到两个数组中最长的common subsequence吗？这样既满足了相同的元素，也满足了不交叉的限制。

这样一想那么代码就简单很多了，就是一个典型的DP问题。
我们创建一个二维数组，长宽就是两个数组的长度+1

(1)不管能帮否match上，dp[i][j]等于max(dp[i-1][j], dp[i][j-1])。就是上一次match A中的上一位元素或者上一次match B中上一位元素的较长的那一个。
(2)如果A[i]可以和B[j]对应的话，我们看看在这之前match的，也就是dp[i-1][j-1]+1和当前dp[i][j]比较会不会更长一些。

最后返回dp[m][n]的值。

TIME & SPACE: O(NM)
*/

class Solution {
    
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        
        for(int i=1; i<=m ; i++){
            for(int j=1; j <= n; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if(A[i-1] == B[j-1]){
                    dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i][j]);
                }
            }
        }
        return dp[m][n];
    }
}

/*
我们观察到，每一行都只和上一行有关系。所以我们可以用滚动数组的办法进一步的压缩空间。
*/

class Solution {
    
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[] dp = new int[n+1];
        
        for(int i=0; i<m ; i++){
            int prevRowPrevCol = 0;
            int prevRow = 0;
            
            for(int j=0; j < n; j++){
                prevRowPrevCol = prevRow;
                prevRow = dp[j+1];
                dp[j+1] = Math.max(dp[j], prevRow);
                
                if(A[i] == B[j]){
                    dp[j+1] = Math.max(prevRowPrevCol+1, dp[j]);
                }
               
            }
        }
        return dp[n];
    }
}