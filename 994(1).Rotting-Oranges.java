/*994. Rotting Oranges
链接：https://leetcode.com/problems/rotting-oranges/
Easy: 
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.


Example 1:
Input: [[2,1,1],
		[1,1,0],
		[0,1,1]]
Output: 4
Example 2:

Input: [[2,1,1],
		[0,1,1],
		[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.
*/

/*解题思路
题目给出的二维数组中，1代表新鲜橘子，2代表腐烂橘子，0代表空。腐烂橘子每经过时间1
会腐烂周围(上下左右)四边的橘子，问，最少经过多久可以将二维数组中的新鲜橘子全部腐烂(2 -> 1)。
这道题目就是简单地BFS，要注意的是如果全部都是新鲜橘子，或者BFS没办法囊括
所有腐烂橘子的话，那么也是return -1.

*/


class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int min =0, totalOne = 0;
        for(int i=0; i< grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i, j});
                }else if(grid[i][j]==1){
                    totalOne++;
                }
            }
        }
        
        if(q.size()== 0 && totalOne ==0) return 0;

        int[][] dirs = {{0,1},{1,0},{-1,0},{0, -1}};
        while(!q.isEmpty()){
            int len = q.size();
            min++;
            while(len-- >0){
                int[] p = q.poll();
                for(int[] dir : dirs){
                    int x = p[0]+dir[0];
                    int y = p[1]+dir[1];
                    if(x>=0 && y>=0 && x<grid.length && y < grid[0].length && grid[x][y] == 1){
                        q.add(new int[]{x, y});
                        grid[x][y] = 2;
                        totalOne--;
                    }
                }
            }
        }

        
        return totalOne==0? min-1 : -1;
    }
}