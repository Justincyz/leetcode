/*52. N-Queens II
Hard
链接: https://leetcode.com/problems/n-queens-ii/
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.
Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]


*/

/*解题思路 backtrack
这道题是之前那道 N-Queens 的延伸，N皇后指的是同一行，同一列和两个对角线都没有其他的皇后，那么
这个位置就可以摆放一个皇后。N-Queens比
这道题还要稍稍复杂一些，两者本质上没有啥区别，都是要用回溯法 Backtracking 来解，如果
理解了之前那道题的思路，此题只要做很小的改动即可，不再需要求出具体的皇后的摆法，只需要
每次生成一种解法时，计数器加一即可，

*/

class Solution {
    int total =0;
    public int totalNQueens(int n) {
        boolean[][] table = new boolean[n][n];
        //对全局的每一个点都做dfs + backtrack
        helper(table, 0, n);
        return total;
    }
    
    
    public void helper(boolean[][] table, int row, int n){
        if(row == n){
            //只要row能达到n，那么说明其他的在这之前都检查过没有问题，也可以达到n
            total++;
            return;
        } 
       
        for(int i=0; i<n; i++){
            if(check(table, row, i)){
                table[row][i] = true;
                helper(table, row+1, n);
                table[row][i] = false;
            }
        }

    }
    
    public boolean check(boolean[][] table, int row, int col){
        int n = table.length;
        //row为横坐标，col为纵坐标
        for(int i=0; i< n; i++){
            if(table[row][i]) return false;
            else if(table[i][col]) return false;
            //检查两个对角线
            //这个点往右下延伸
            else if(row+i < n && col +i < n && table[row+i][col+i]) return false;
            //这个点往左上延伸
            else if(row-i >=0 && col -i >=0 && table[row-i][col-i]) return false;
            //这个点往左下延伸
            else if(row+i < n && col -i >=0 && table[row+i][col-i]) return false;
            //这个点往右上延伸
            else if(row-i >=0 && col +i < n && table[row-i][col+i]) return false;
        }
        return true;
    }
}


/*这道题目还有一个更加快速简单的解法
其实我们并不需要知道每一行皇后的具体位置，而只需要知道会不会产生冲突即可。对于每行要
新加的位置，需要看跟之前的列，对角线，及逆对角线之间是否有冲突，所以我们需要三个布尔
型数组，分别来记录之前的列 cols，对角线 diag，及逆对角线 anti_diag 上的位置，其中
cols 初始化大小为n，diag 和 anti_diag 均为 2n。列比较简单，是哪列就直接去 cols 中查找
，而对角线的话，需要处理一下，如果我们仔细观察数组位置坐标的话，可以发现所有同一条主对角
线(左上到右下)的数，其纵坐标减去横坐标再加n，一定是相等的。同理，同一条逆对角线(左下到右上0上的数字，其横纵坐标
之和一定是相等的，根据这个，就可以快速判断主逆对角线上是否有冲突。任意一个有冲突的话，直
接跳过当前位置，否则对于新位置，三个数组中对应位置都赋值为 
true，然后对下一行调用递归，递归返回后记得还要还原状态，
*/

class Solution {
    int total =0;
    public int totalNQueens(int n) {
        //对角线需要长度为2n，因为每一个对角线的顶点会是n+n，比如说右下角
        boolean[] cols = new boolean[n], diag = new boolean[2*n], antiDiag = new boolean[2*n];
        helper(n, 0,  cols, diag, antiDiag);
        return total;
    }
    
    
    public void helper(int n, int row, boolean[] cols, boolean[] diag, boolean[] antiDiag){
        if(row == n){
            total++;
            return;
        }
        
        for(int col=0; col<n; col++){
            int idx1 = row+col, idx2 = col-row+n;
            if(cols[col] || diag[idx1] || antiDiag[idx2]) continue;
            cols[col] = true; diag[idx1] = true; antiDiag[idx2] = true;
            helper(n, row+1, cols, diag, antiDiag);
            cols[col] = false; diag[idx1] = false; antiDiag[idx2] = false;
        }
    }
}