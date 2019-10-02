/*79. Word Search

链接：https://leetcode.com/problems/word-search/
Medium: 
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

/*解题思路
这道题目就是用dfs+ backtrack来找到是否一个单词出现在board中。

*/

class Solution {
    int[][] dirs = {{1,0},{-1, 0},{0, 1}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for(int i=0; i<m; i++){
            for(int j =0; j< n; j++){
                if(board[i][j] == word.charAt(0)){
                    board[i][j] = '#';
                    if(bfs(board, i, j, word, 1)) return true;    
                    board[i][j] = word.charAt(0);
                } 
            }
        }
        
        return false;
    }
    
    public boolean bfs(char[][] board, int i, int j, String word, int idx){

    	//注意这个结束条件
        if(word.length() == idx) return true;
        
        for(int[] dir: dirs){
            int x = i+ dir[0];
            int y = j+ dir[1];
            if(x <0 || y <0 || x>= board.length || y >= board[0].length || board[x][y] != word.charAt(idx) || board[x][y] == '#') continue;

            char c = board[x][y];
            board[x][y] = '#';
            if(bfs(board, x, y, word, idx+1)) return true;
            board[x][y] = c;
        }
        
        return false;
    }
}