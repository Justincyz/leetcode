/*48. Rotate Image
Medium: 
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Note:
You have to rotate the image in-place, which means you have to 
modify the input 2D matrix directly. DO NOT allocate another 2D 
matrix and do the rotation.

Example 1:
Given input matrix = [
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]

Example 2:
Given input matrix =[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

/*解题思路
这道题目要求我们做in-place replacement。其实方法很巧妙。
如果是向右旋转九十度，那么其实是先沿着左上到右下的对角线先交换一次。然后沿着竖直中间线
左右再交换一次。
假设输入是第一个例子，第一次对折后。
[1,4,7]
[2,5,8]
[3,6,9]
第二次对折后
[7,4,1]
[8,5,2]
[9,6,3]
就变成向右旋转90度了

拓展一下，假设是向左旋转90度，那就是先沿着左上到右下的对角线对折一次，然后再沿着水平
中间线上下对折一次。

*/

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        for(int i=0; i< n; i++){
            for(int j = i; j<n;j++){
                int change = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = change;
            }
        }
        
        for(int i=0; i<n;i++){
            for(int j=0; j<n/2 ;j++){
                int change = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = change;
            }
        }
    }
}


