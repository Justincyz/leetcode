/*1314. Matrix Block Sum
链接：https://leetcode.com/problems/matrix-block-sum/
Medium: 
Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 

Example 1:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]

Example 2:
Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
*/

/*解题思路 matrix pre-sum
题目有点难读懂。给我们一个二维matrix, 然后给我们一个K值，让我们找到每一
个坐标(i,j), 以坐标(i,j)为中心，长度为2K+1的正方形
用example 1举个例子
[1,2,3]
[4,5,6]
[7,8,9]
K = 1；
对于坐标为(0,0)的1来说，计算方式是
[1+2+4+5] = 12。
对于坐标为(0,1)的2来说，计算方式是
[1+2+3+4+5+6] = 21
对于坐标为(1,1)的5来说，计算方式是
[1+2+3+4+5+6+7+8+9+] = 45
可以看出来，就是以坐标(i,j)为核心，长宽为2K+1的正方形集合。

这道题目用pre-sum的思路来解就可以。我们新建一个长宽为(m+1)(n+1)
的矩阵用来存pre-sum,要注意坐标转换。

我们计算每个坐标的方法可以在纸上画一下，是三个正方形做加减，再加上当前坐标的值。
同理，后面计算每个坐标的正方形值的总和是，是四个正方形做加减。
*/


class Solution {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        int[][] preSum = new int[m+1][n+1];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                preSum[i+1][j+1] = mat[i][j]+preSum[i+1][j]+preSum[i][j+1]-preSum[i][j];
            }
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int x_upperLeft = i-K <0 ? 0: i-K, y_upperLeft = j-K <0 ?0: j-K;
                int x_lowerRight = i+K+1 >m ? m: i+K+1, y_lowerRight = j+K+1 >n ? n: j+K+1;
                mat[i][j] = preSum[x_lowerRight][y_lowerRight] + preSum[x_upperLeft][y_upperLeft]
                            - preSum[x_lowerRight][y_upperLeft] - preSum[x_upperLeft][y_lowerRight];
            }
        }
        return mat;
    }
}