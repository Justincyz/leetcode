/*37. Sudoku Solver
Hard
链接: 

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.

*/

/*解题思路

这道求解数独的题是在之前那道 Valid Sudoku 的基础上的延伸，之前那道题让我们验证给定的数组是否为数独数组，这道让求解数独数组，跟此题类似的有 Permutations，Combinations， N-Queens 等等，其中尤其是跟 N-Queens 的解题思路及其相似，对于每个需要填数字的格子带入1到9，每代入一个数字都判定其是否合法，如果合法就继续下一次递归，结束时把数字设回 '.'，
*/

class Solution {
    HashMap<String, Set<Character>> map;
    
    public void solveSudoku(char[][] board) {
        map = new HashMap<>();
        
        //按照坐标计算每一个元素的所在的方块内，方块编号从左到右，从上到下为0-8。计算方式为 x和y坐标各除以3， 然后X坐标加3再加上y坐标就是
        //属于第几个方块
        int countMiss = 0;
        /*对于每一行，每一列，每一个小方格，我们可以用一个hashmap也可以用三个hashmap来
        代替。那么对于0-8行我们再后面跟上一个 "-X"来区别。对于0-8列我们粘一个"X-"在前面，
        对于0-8小方块我们直接用数字就好了。
        */
        for(int i=0; i<9; i++){
            map.put(String.valueOf(i), new HashSet());
            map.put(String.valueOf(i)+"-X", new HashSet());
            map.put("X-"+String.valueOf(i), new HashSet());
        }
        
        //将数独中已有的元素放入对应的横，纵和小方块的hashset中
        for(int i=0; i<9; i++){
            for(int j =0; j<9; j++){
                if(board[i][j] =='.'){
                    continue;
                } 
                String row = String.valueOf(i)+"-X";
                String col = "X-"+String.valueOf(j);
                String squ = String.valueOf((i/3)*3 +(j/3));

                map.get(row).add(board[i][j]);
                map.get(col).add(board[i][j]);
                map.get(squ).add(board[i][j]);
            }
        }
        //此时我们call helper使用backtrack填充所有的空位。
        helper(board);
    }
    
    public boolean helper(char[][] board){
        
        //对于数独中每个位置，我们都尝试填充1-9个不同的数字
        for(int i=0; i<9; i++){
            for(int j =0; j<9; j++){
                //如果已经被填充过，则不用管它
                if(board[i][j] !='.'){
                    continue;
                } 
                String row = String.valueOf(i)+"-X";
                String col = "X-"+String.valueOf(j);
                String squ = String.valueOf((i/3)*3 +j/3);
                for(char k='1'; k<='9'; k++){
                    //如果横，纵或者小方块内已经包含了当前的数字K,则继续。
                    if(map.get(row).contains(k) ||
                       map.get(col).contains(k) ||
                       map.get(squ).contains(k)) continue;   

                    /*将当前数字k填充在Board[i][j]中，然后加入
                    三个对应的hashset，递归调用helper函数，尝试
                    其他的所有解。
                    */
                    board[i][j] = k;
                    map.get(row).add(k);
                    map.get(col).add(k);
                    map.get(squ).add(k);

                    if(helper(board)){
                        return true;
                    }  
                    //恢复原状
                    map.get(row).remove(k);
                    map.get(col).remove(k);
                    map.get(squ).remove(k);
                    board[i][j] = '.';
                }
                //如果这个格子不能够被放入任何的字母，返回false
               
                return false;
            }
        }
        //当运行到这个true的时候，格子已经是满的了，说明所有的数字填充都合法，return true
        return true;
    }
}


/*
这个方法相对来说会快一些，不用hashset而是对每一个插入的数字，检查
横，纵，和小方块内是否有要被插入的这个数字。比较难想的就是如何遍历
小方块内的数值了。
*/

class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0)
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){//trial. Try 1 through 9
                        if(isValid(board, i, j, c)){
                            board[i][j] = c; //Put c for this cell
                            
                            if(solve(board))
                                return true; //If it's the solution return true
                            else
                                board[i][j] = '.'; //Otherwise go back
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c){
        for(int i = 0; i < 9; i++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; //check row
            if(board[row][i] != '.' && board[row][i] == c) return false; //check column
            if(board[3 * (row / 3) + i / 3][ 3 * (col / 3) + i % 3] != '.' && 
board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false; //check 3*3 block
        }
        return true;
    }
}