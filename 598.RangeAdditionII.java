/*598. Range Addition II
Easy

Given an m * n matrix M initialized with all 0's and several update operations.

Operations are represented by a 2D array, and each operation is represented by 
an array with two positive integers a and b, which means M[i][j] should be added 
by one for all 0 <= i < a and 0 <= j < b.

You need to count and return the number of maximum integers in the matrix after 
performing all the operations.

Example 1:
Input: 
m = 3, n = 3
operations = [[2,2],[3,3]]
Output: 4
Explanation: 
Initially, M = 
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]

After performing [2,2], M = 
[[1, 1, 0],
 [1, 1, 0],
 [0, 0, 0]]

After performing [3,3], M = 
[[2, 2, 1],
 [2, 2, 1],
 [1, 1, 1]]

So the maximum integer in M is 2, and there are four of it in M. So return 4.
*/

/*解题思路
因为是从(0,0)出发的，所以最大的数值的面积一定是所有出现的面积的最小值。这样重叠的次数最多

*/

class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if(ops.length ==0) return m*n; //这个没懂是什么鬼，卡在 3, 3, []上面
        
        int col=Integer.MAX_VALUE, row= Integer.MAX_VALUE;
        for(int[] op: ops){
            col = Math.min(col, op[0]);
            row = Math.min(row, op[1]);
        }
        return col*row;
    }
}