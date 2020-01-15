/*51. N-Queens
链接：https://leetcode.com/problems/n-queens/
Hard: 
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
//-------此处应该有图，可回原网页查看

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
*/

/*解题思路 backtrack
这道题目让与国际象棋有关，国际象棋中的皇后可以横着走，竖着走，斜着走都可以，是最强的
棋子吧。这个题目让我们找到将N个皇后放在NxN的棋盘当中，每个皇后之间都互相不干扰。最后
输出所有的放置办法。这道题目说简单就是一个backtrack的问题，主要问题是如何快速判断某一个
位置能否放置皇后。
第一个办法就是对于每一个位置，O(N)时间检查这个位置的行和列还有两条对角线
当中有没有放置皇后，如果没有的话说明这是一个valid posiiton。这个方法5ms, beats 50%

*/


class Solution {
    List<List<String>> res;
    int n;
    public List<List<String>> solveNQueens(int n) {
        res = new ArrayList<>();
        this.n = n;
        int[][] table = new int[n][n];
        helper(table, 0);
        return res;
    }
    
    public void helper(int[][] table, int r){
        if(r == n){
            convert(table);
            return;
        }
        
        for(int i=0; i<n; i++){
            if(table[r][i] == 1 || !check(table, r, i) ) continue;
            table[r][i] = 1;
            helper(table, r+1);
            table[r][i] = 0;
        }
        
    }
    
    public boolean check(int[][] table, int r, int c){
        for(int i=0; i< n; i++){
            if(table[r][i] == 1 || table[i][c] == 1) return false;
            if(r+i < n && c+i < n && table[r+i][c+i] ==1) return false;
            if(r-i >=0 && c+i < n && table[r-i][c+i] ==1) return false;
            if(r+i < n && c-i >=0 && table[r+i][c-i] ==1) return false;
            if(r-i >=0 && c-i >=0 && table[r-i][c-i] ==1) return false;
        }    
        return true;
    }
    
    
    public void convert(int[][] table){
        List<String> t = new ArrayList<>();
        for(int i=0; i<n;i++){
            StringBuffer sb = new StringBuffer();
            for(int j=0; j< n; j++){
                if(table[i][j] == 0) sb.append(".");
                else sb.append("Q");
            }
            t.add(sb.toString());
        }
        res.add(t);
    }
}


/*
第二个办法就是用时间换空间的做法，我们设置行，列，两个斜对角线数组，来标记
已经被放置皇后的位置。这样每一次检查就是O(1)的时间。
难点还是在如何找到每个位置的对角线。我们这样来想这个问题。
对于每一个矩阵中的点来说，我们照着这个点花两条对角线。一条是从左上到右下的，
一条是从左下到右上的。对于左上到右下的这条线来说，他的横坐标加纵坐标总是
相等的，因为这相当于一个等腰直角三角形，斜边上每一个点映射到X和Y轴上的坐标
的和总是相等的。同样的道理也适用于另外的一边，但是就变成x-y+n-1这样子。利用这个
性质我们就可以在O(1)的时间内找到两条对角线是否存在别的皇后了
*/

public class Solution {
    // 2019-05-20 xs
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] row = new int[n];
        int[] col = new int[n];
        int[] pDiag = new int[n*2 - 1];
        int[] nDiag = new int[n*2 - 1];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append('.');
        }
        dfs(0, n, row, col, pDiag, nDiag, sb, res, new ArrayList<>());
        return res;
    }
    
    private void dfs(int x, int num, int[] row, int[] col, int[] pDiag, int[] nDiag, StringBuilder sb, List<List<String>> res, List<String> temp) {
        if(x == num) {
            res.add(new ArrayList<>(temp));
            return;
        }
        
        for(int i = 0; i < num; i++) {
            int p = getPDiag(x, i), n = getNDiag(x, i, num);
            if(col[i] == 0 && row[x] == 0 && pDiag[p] == 0 && nDiag[n] == 0) {
                String str = buildString(i, sb);
                temp.add(str);
                col[i]++;
                row[x]++;
                pDiag[p]++;
                nDiag[n]++;
                dfs(x+1, num, row, col, pDiag, nDiag, sb, res, temp);
                col[i] = 0;
                row[x] = 0;
                pDiag[p] = 0;
                nDiag[n] = 0;
                temp.remove(temp.size() - 1);
            }
        }
    }
    
    private int getPDiag(int x, int y) {
        return x + y;
    }
    
    private int getNDiag(int x, int y, int n) {
        return x-y+n-1;
    }
    
    private String buildString(int y, StringBuilder sb) {
        sb.setCharAt(y, 'Q');
        String str = sb.toString();
        sb.setCharAt(y, '.');
        return str;
    }
}