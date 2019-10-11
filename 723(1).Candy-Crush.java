/*
链接：
Medium: 
This question is about implementing a basic elimination algorithm for Candy Crush.

Given a 2D integer array board representing the grid of candy, different positive integers board[i][j] represent different types of candies. A value of board[i][j] = 0 represents that the cell at position (i, j) is empty. The given board represents the state of the game following the player's move. Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, "crush" them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. (No new candies will drop outside the top boundary.)
After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (ie. the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the current board.

//图片回原题看
Example:
Input:
board =
[[110,5,112,113,114],[210,211,5,213,214],[310,311,3,313,314],[410,411,412,5,414],[5,1,512,3,3],[610,4,1,613,614],[710,1,2,713,714],[810,1,2,1,1],[1,1,2,2,2],[4,1,4,4,1014]]

Output:
[[0,0,0,0,0],[0,0,0,0,0],[0,0,0,0,0],[110,0,0,0,114],[210,0,0,0,214],[310,0,0,113,314],[410,0,0,213,414],[610,211,112,313,614],[710,311,412,613,714],[810,411,512,713,1014]]

*/

/*解题思路
这道题目是一个没办法考虑时间复杂度的题目，反正逻辑是对的就可以。
这道题就是糖果消消乐，并不是就像游戏中的那样，每次只能点击一个地方，然后消除后糖果落下，这
样会导致一个问题，就是原本其他可以消除的地方在糖果落下后可能就没有了，所以博主在想点击的顺序
肯定会影响最终的 stable的状态，可是题目怎么没有要求返回所剩糖果最少的状态？后来发现，其实这道题一次消除 table 中所有可消除的糖果，然后才下落，形成新的 table，这样消除后得到的结果就是统一的了，这样也大大的降低了难度。下面就来看如何找到要消除的糖果，可能有人会觉得像之前的岛屿的题目一样找连通区域，可是这道题的有限制条件，只有横向或竖向相同的糖果数达到三个才能消除，并不是所有的连通区域都能消除，所以找连通区域不是一个好办法。最好的办法其实是每个糖果单独检查其是否能被消除，然后把所有能被删除的糖果都标记出来统一删除，然后在下落糖果，然后再次查找，直到无法找出能够消除的糖果时达到稳定状态。好，用一个数组来保存可以被消除的糖果的位置坐标，判断某个位置上的糖果能否被消除的方法就是检查其横向和纵向的最大相同糖果的个数，只要有一个方向达到三个了，当前糖果就可以被消除。所以对当前糖果的上下左右四个方向进行查看，用四个变量 up, down, left, right，其中 up 表示上方相同的糖果的最大位置，down 表示下方相同糖果的最大位置，left 表示左边相同糖果的最大位置，right 表示右边相同糖果的最大位置，均初始化为当前糖果的位置，然后使用 while 循环向每个方向遍历，注意并不需要遍历到尽头，而是只要遍历三个糖果就行了，因为一旦查到了三个相同的，就说明当前的糖果已经可以消除了，没必要再往下查了(实际运行中并没有提升时间太快，估计是table比较小)。查的过程还要注意处理越界情况，好，得到了上下左右的最大的位置，分别让相同方向的做差，如果水平和竖直方向任意一个大于3了，就说明可以消除，将坐标加入queue 中。注意这里一定要大于3，是因为当发现不相等退出 while 循环时，坐标值已经改变了，所以已经多加了或者减了一个，所以差值要大于3。遍历完成后，如果queue 为空，说明已经 stable 了，直接 break 掉，否则将要消除的糖果位置都标记为0，然后进行下落处理。下落处理实际上是把数组中的0都移动到开头，那么就从数组的末尾开始遍历，用一个变量t先指向末尾，然后然后当遇到非0的数，就将其和t位置上的数置换，然后t自减1，这样t一路减下来都是非0的数，而0都被置换到数组开头了

*/
/*这种做法并不是最快的，因为对于每一个坐标我们都单独计算上，下，左，右是否大于等于三，所以
有重复的计算，可以再看看下面的那种做法。先验证，然后才将有连通的的区域一次性标记为可清除*/

class Solution {
    public int[][] candyCrush(int[][] board) {
        
        while(true){
            Queue<int[]> q = new LinkedList<>();
            
            for(int i=0; i<board.length; i++){
                for(int j=0; j< board[0].length; j++){
                    if(board[i][j] == 0) continue;
                    int up = i, down = i, left = j, right = j;
                    while(left >=0  && board[i][left] == board[i][j]) left--;
                    while(right <board[0].length  && board[i][right] == board[i][j]) right++;
                    while(up >=0 && board[up][j] == board[i][j]) up--;
                    while(down < board.length  && board[down][j] == board[i][j]) down++;
                    if(right - left >3 || down - up > 3) q.add(new int[]{i, j});
                }
            }
            if(q.isEmpty()) break;
            helper(board, q);
        }
        
        
        return board;
    }
    
    public void helper(int[][] board, Queue<int[]> q){
        for(int[] p : q) board[p[0]][p[1]] =0;
        int m = board.length, n = board[0].length;

        for (int j = 0; j < n; ++j) {
            int t = m - 1;
            for (int i = m - 1; i >= 0; --i) {
                if (board[i][j] ==0) swap(board,t--,j, i,j);   
            }
        }
    }
    
    public void swap(int[][] board, int x1, int y1, int x2, int y2){
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }
}


//这种做法相对来说快很多，因为重复计算的次数变少了


class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        boolean found = true;
        
        while(found) {
            found = false;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) continue;
                    int cur = Math.abs(board[i][j]);
                    if (j < n - 2 && cur == Math.abs(board[i][j+1]) && cur == Math.abs(board[i][j+2])) {
                        found = true;
                        int indx = j;
                        while (indx < n && Math.abs(board[i][indx]) == cur) {
                            board[i][indx] = -cur;
                            indx++;
                        }
                    }
                    if (i < m - 2 && cur == Math.abs(board[i+1][j]) && cur == Math.abs(board[i+2][j])) {
                        found = true;
                        int indx = i;
                        while (indx < m && Math.abs(board[indx][j]) == cur) {
                            board[indx][j] = -cur;
                            indx++;
                        }
                    }
                }
            }
            if (found) {
                for (int j = 0; j < n; j++) {
                    int moveIndx = m-1;
                    for (int i = m-1; i >= 0; i--) {
                        if (board[i][j] > 0)
                            board[moveIndx--][j] = board[i][j];
                    }
                    
                    for (int k = moveIndx; k >= 0; k--) {
                        board[k][j] = 0;
                    }
                }
            } 
        }
        
        return board;
    }
}


