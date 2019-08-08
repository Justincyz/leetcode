/*130. Surrounded Regions
Medium: 
Given a 2D board containing 'X' and 'O' (the letter O), capture all 
regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X

简单来说就是如果和最外面一层边有相连的O就不能被翻转。因为没有完全被包围住
*/

/*解题思路
这道题有两种解题思路
第一种是自己写的。简单来说就是每一次遇到'O'的时候还是先做DFS， 但是
会一路上用一个queue记录被flip过的坐标。如果在做DFS的过程中，任意一个方向遇到了边，那么就返回false。此时
主函数就要根据queue中的坐标再对原图做一次恢复。如果没有做DFS的时候碰到边的话就直接过。

第二种做法是网上看到别人做的。先对四条边上的'O'做DFS。将和四条边上'O'连通的'O'先全部变成一个
特殊的字符'Q'。那么此时我们再循环 board的时候，遇到'Q'的话我们要变成'O',遇到第一次DFS够不到的
'O'的话就将它变成'X'
*/
class Solution {
    int[][] dirs = {{-1,0},{1,0},{0,1},{0,-1}};
    public void solve(char[][] board) {
        if(board.length <= 2 || board[0].length<=2) return;
        int m = board.length, n = board[0].length;
        for(int i=1; i<m; i++){
        	for(int j=0; j< n; j++){
                if(board[i][j] == 'O'){
                    Queue<int[]> q = new LinkedList();
                    if(!helper(board, q, i, j)){
                        while(!q.isEmpty()){
                            int[] pos = q.remove();
                            board[pos[0]][pos[1]] = 'O';
                        }
                    }
                }
        	}
        }
    }
    
    public boolean helper(char[][] board, Queue<int[]> q, int i, int j){
        if((i<=0||j<=0||i==(board.length-1)||(j==board[0].length-1))&& board[i][j]=='O') return false;
        if(board[i][j]=='X') return true;
        
        q.add(new int[]{i, j});
        board[i][j] = 'X';
        for(int[] dir: dirs){
            if(!helper(board, q, i+dir[0],j+dir[1])) return false;
        }
        return true;
    }
}



class Solution {
    public void solve(char[][] board) {
        if(board == null|| board.length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for(int i =0; i< m; i++){
            if(board[i][0] == 'O') bfs(board, i, 0);
            if(board[i][n-1] == 'O') bfs(board, i, n-1);
        }
        for(int i= 0; i<n; i++){
            if(board[0][i] == 'O') bfs(board, 0, i);
            if(board[m-1][i] == 'O') bfs(board, m-1, i);
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }else if(board[i][j] =='Q'){
                    board[i][j] = 'O';
                }
            }
        }
        return ;
    }
    
    private void bfs(char[][] board, int row, int col){
        if(row <0 || col <0 || row >= board.length || col >= board[0].length || board[row][col] != 'O')
            return;
        board[row][col] = 'Q';
        bfs(board, row+1, col);
        bfs(board, row-1, col);
        bfs(board, row, col+1);
        bfs(board, row, col-1);
    }
}
