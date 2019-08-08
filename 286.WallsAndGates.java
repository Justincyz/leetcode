/*286. Walls and Gates
Medium: 
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 
to represent INF as you may assume that the distance to a gate is less than 
2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible 
to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

/*解题思路
这道题目不难，对每一个位置0做一个dfs, 在每一个可以reach的位置更新一下最小的step。注意
用的是BFS不是DFS，不然总是需要确定有没有visit过很麻烦。
有一个点需要特别注意。就是helper 里面for loop最里面的那个if statement. 我们设置
rooms[x][y] <= step有一个很方便的点。
第一，对于从第一个0出发的点来说，不会走回头路，因为新reach的点steps一定比原来的值要大。
第二，对于已经遍历更新过一次的matrix来说，第二次遇到'0'，需要再次更新位置时。如果有节点的
值已经比当前的step小了，那我们就不需要继续遍历了。因为做BFS不可能比原来的值更小。

*/
class Solution {
	int[][] dirs = {{-1, 0},{1,0},{0,1},{0,-1}};
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length == 0) return;
    	int m = rooms.length, n = rooms[0].length;
    	for(int i=0; i<m; i++){
    		for(int j =0; j< n; j++){
    			if(rooms[i][j] == 0) helper(rooms, i, j);
    		}
    	}    
    }

    public void helper(int[][] rooms, int i, int j){
    	Queue<int[]> q= new LinkedList();
    	q.add(new int[]{i,j});
    	int step=0, m = rooms.length, n=rooms[0].length;

    	while(!q.isEmpty()){
    		step++;
    		int size = q.size();
    		while(size-- >0){
    			int[] d = q.remove();
    			for(int[] dir : dirs){
    				int x = d[0]+dir[0];
    				int y = d[1]+dir[1];
    				if(x<0||y<0||x>=m||y>=n||rooms[x][y]==-1|| rooms[x][y] <= step) continue;
    				rooms[x][y] = Math.min(rooms[x][y], step);
    				q.add(new int[]{x,y});
    			}
    		}
    	}

    }
}

//这种DFS的办法很粗暴，但是很有效
class Solution {
    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }
    private void dfs(int[][] rooms, int i, int j, int dis) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < dis) return;
        rooms[i][j] = dis;
        dfs(rooms, i - 1, j, dis + 1);
        dfs(rooms, i + 1, j, dis + 1);
        dfs(rooms, i, j + 1, dis + 1);
        dfs(rooms, i, j - 1, dis + 1);
    }
}
