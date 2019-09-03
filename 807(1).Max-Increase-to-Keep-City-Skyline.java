/*807. Max Increase to Keep City Skyline

Medium: 
链接: https://leetcode.com/problems/max-increase-to-keep-city-skyline/

In a 2 dimensional array grid, each value grid[i][j] represents the 
height of a building located there. We are allowed to increase the height 
of any number of buildings, by any amount (the amounts can be different for 
different buildings). Height 0 is considered to be a building as well. 

At the end, the "skyline" when viewed from all four directions of the grid, 
i.e. top, bottom, left, and right, must be the same as the skyline of the 
original grid. A city's skyline is the outer contour of the rectangles formed 
by all the buildings when viewed from a distance. See the following example.

What is the maximum total sum that the height of the buildings can be increased?

Example:
Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
Output: 35
Explanation: 
The grid is:
[ [3, 0, 8, 4], 
  [2, 4, 5, 7],
  [9, 2, 6, 3],
  [0, 3, 1, 0] ]

The skyline viewed from top or bottom is: [9, 4, 8, 7]
The skyline viewed from left or right is: [8, 7, 9, 3]

The grid after increasing the height of buildings without affecting skylines is:

gridNew = [ [8, 4, 8, 7],
            [7, 4, 7, 7],
            [9, 4, 8, 7],
            [3, 3, 3, 3] ]

*/

/*解题思路
一道比较简单的题目。题目问我们，在不遮挡天际线的情况下，最多可以为matrix中的楼房
增高多少。天际线的定义就是从左右，上下看过去最高的楼组成的天际线。

这道题目没有捷径，必须要O(MN)的时间复杂度计算每一行每一列的最大值，然后储存在
两个一维数组中。对于矩阵中其余的楼房高度来说，两个一维数组中较小的那一位决定了
楼房的上限。所以第二次便利数组的时候我们最多可以为matrix[i][j]楼房增加的高度就是
横纵轴天际线较小的值减去原本matrix[i][j]中的值

*/
//1ms beats100%
class Solution {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] row = new int[m], col = new int[n];
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                col[j] = Math.max(col[j], grid[i][j]);
                row[i] = Math.max(row[i], grid[i][j]);
            }
        }
        int res = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                res+= Math.min(row[i], col[j])-grid[i][j];
            }
        }
        return res;
    }
}