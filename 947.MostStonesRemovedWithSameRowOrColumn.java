/*947. Most Stones Removed with Same Row or Column
Medium: Union Find, DFS
On a 2D plane, we place stones at some integer coordinate points.  
Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with 
another stone on the grid.

What is the largest possible number of moves we can make?

Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5

Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3

Example 3:
Input: stones = [[0,0]]
Output: 0
*/

/*解题思路
这道题也是可以用Union Find做法来做的。但是稍微思路有点不同。在这里把stones坐标总数当做一个array[]的大小
在如果坐标 [x,y]和坐标[i,j]在相同的col或者row上的话，那么就说明这两个可以归为一类。比如对于例子1来说
stones =  [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
parent =  [  0     1,    2,    3,    4,    5  ] 每个坐标占一个位置
然后连接parent里面的坐标。就变成了一个经典的Union Find

*/

class Solution {
    public int removeStones(int[][] stones) {
        if (stones == null || stones.length <= 1);
        int n = stones.length;
        
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int component = n;
        for (int i = 0; i < n - 1; i++) { //每个点都要和所有的点进行比对
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] != stones[j][0] && stones[i][1] != stones[j][1]) continue;
                
                /*if two stones have the same row or same col, and they are not connected yet..
                connect those two stones, and decrement the total component */
                int rootI = findRoot(parent, i);
                int rootJ = findRoot(parent, j);
                if (rootI != rootJ){
                    parent[rootI] = rootJ;
                    component--;
                }
            }
        }
        return n - component;
    }
    
    private int findRoot(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];//路径压缩
            i = parent[i];
        }
        return i;
    }       
}


