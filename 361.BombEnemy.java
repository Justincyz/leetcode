/* 361. Bomb Enemy
Medium: DP
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' 
(the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted 
point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,
0 E 0 0 
E 0 W E 
0 E 0 0
Placing a bomb at (1,1) kills 3 enemies.

*/

/*解题思路
这道题就是用的dp, 一开始没想出来如何遍历一次解决问题。觉得可能需要对每一行每一列
都做累加。看了网上的答案发现果然有人就是这样写的而且也能过case。虽然这种算法也是O(n^2)的。
但是需要的空间到了 4*(O(n^2)).代码没有什么出奇的，但是有一个细节需要特别注意。我们累加遍历
的时候是遇到E的时候，当前位就要加一。一开始考虑到如果当前行只有一个enemy,这样的话从左往右和
从右往左累加的时候当前位不就变成2了吗？其实的确是这样，但是我们不能取enemy这个位置，只能取
空位。
假设第一行是这样的，     [0, E, 0, 0, 0] 
那么从左往右累加是这样的，[0, 1, 1, 1, 1]
那么从右往左累加是这样的，[1, 1, 0, 0, 0]
所以虽然当前位是2，但是如果是从左到右遍历的时候enemy的右边空位都是1。而此时从右往左
遍历的array因为还没有遇到enemy，所以空位区域都是0。这样累加的时候除去enemy那一位，其余位置都是
正常的。
后面还有简洁的方法
*/
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length ==0 || grid[0].length==0) return 0;
        int n = grid.length, m = grid[0].length;
        
        int[][] v1 = new int[n][m], v2 = new int[n][m], v3 = new int[n][m], v4 = new int[n][m];
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int val = (j==0 || grid[i][j] == 'W')? 0 : v1[i][j-1];
                
                v1[i][j] = (grid[i][j] == 'E')? val+1 : val;
                if(i==0) System.out.print( v1[i][j]+"  ");
            }
            
            for(int j=m-1; j>=0; j--){
                int val = (j==(m-1) || grid[i][j] == 'W')? 0 : v2[i][j+1];
                v2[i][j] = (grid[i][j] == 'E')? val+1 : val;
                if(i==0) System.out.print( v2[i][j]+"  ");
            }
        }
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int val = (j==0 || grid[j][i] == 'W')? 0 : v3[j-1][i];
                v3[j][i] = (grid[j][i] == 'E')? val+1 : val;
            }
            for(int j=n-1; j>=0; j--){
                int val = (j==n-1 || grid[j][i] == 'W')? 0 : v4[j+1][i];
                v4[j][i] = (grid[j][i] == 'E')? val+1 : val;
            }
        }
        int res = 0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j]=='0')
                res = Math.max(res, v1[i][j]+v2[i][j]+v3[i][j]+v4[i][j]); 
            }
        }
        return res;
    }
}

/*
这种算法其实和上面一种大同小异，主要是节约在空间复杂度上。所以速度很快。
对于每一个坐标来说，rowSum是当前坐标j可以接触的范围内横轴的所有enermy的数量。
colSum[j]是当前坐标j在纵轴上面的enermy数量总和。在除了每一次开头和遇到Wall的情况下，
都不需要重新计算colSum和rowSum[]的值。如果遇到了需要重新计算的情况的话再进入if statement
重新计算。还是要注意，只有当当前点为'0'的时候，才可以计算放置炸弹的点位。
*/
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid.length == 0 || grid[0].length==0) return 0;
    	int m = grid.length, n = grid[0].length;
    	int[] colSum = new int[n];
    	int rowSum =0, res = 0;
    	for(int i=0; i< m; i++){
    		for(int j=0; j< n; j++){
    			if(j==0 || grid[i][j-1] == 'W'){
    				int rsum = 0;
    				for(int k = j; k<n && grid[i][k]!='W';k++){
    					rsum+= (grid[i][k] == 'E')? 1: 0;
    				}
    				rowSum = rsum;
    			}

    			if(i==0 || grid[i-1][j] =='W'){
    				colSum[j] =0;
    				for(int k=i; k<m && grid[k][j]!='W'; k++){
    					colSum[j] += (grid[k][j]=='E')? 1 : 0;
    				}
    			}
    			if(grid[i][j] == '0') res = Math.max(res, colSum[j]+rowSum);
    		}
    	}
    	return res;
  	}
}
