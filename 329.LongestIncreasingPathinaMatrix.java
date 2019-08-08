/*
Hard: 
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up 
or down. You may NOT move diagonally or move outside of the boundary (i.e. 
wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally 
is not allowed.
*/

/*解题思路
这道题一开始觉得应该可以用DFS来做。大体思路就是从每一个点出发，找到可以连续的最长的递增序列。
每一个visit过的点就用将visit[i][j]设置为true来记忆。如果下一个visit的点小于等于当前的点。
那么就return 累加到目前为止的total-1返回给上一层。
这样做的情况下遇到了一个问题，因为对每一个点遍历的时候都是前后左右去遍历的。而遍历过的点就没办法
visit第二次了。如果遇到如下的matrix，
[7 8 9
 9 7 6
 7 2 3]
那么最长的递增序列就是，[2, 3, 6, 7, 8, 9] = 6，出发点是2
但是如果按照原来的方法做的话，2会先visit周围的[7,7,3]。此时已经把三个点都mark为true了。
当循环到下一位3时，那么3往后的路径就一定没办法经过7了。因为此时已经被mark为true。
那么解决办法就是在call 下一层之后，把这个位置又重新mark为false。代表下一次还可以经过这个点。
但是这并不会使得被遍历过的位置再被遍历一次，因为我们用的是DFS, 会先计算一条连续递增的路径之后，
回溯时再重新mark 为 false。这种办法过了 135/137个 test, 最后TLE了。错误的代码在页面最后。算是
一个思路吧。
那么如何对上面的办法进行加速呢? 我么知道，一个持续递增的数列有这么一个关系，如果我们知道从A
点出发最长的递增数列是k 的话，那么从比这个数小的数 B 到达A的距离再加上k即是从B点出发经过A可以
生成的最长递增数列(因为DFS会自动找到从B到A的最长线路)。之所以B到A点的距离不会和A点最长的距离
产生重合，是因为我们知道B<A，那么B如果直接连接上A之后的路径，那么就会损失掉A以及B到A可能产生大于1
的路径。所以我们可以建立这样一个dp数组path[][]，用来储存每次遍历matrix[i][j]时可以产生的最长路径。
下一次如果遇到被遍历过的路径那么直接调用dp就好了

*/
//DFS
class Solution {
	int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    int[][] path;
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        path = new int[m][n]; 
        int max =0;

        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		max = Math.max(max, helper(matrix, i, j));                
        	}
        }
        return max;
    }

    public int helper(int[][] matrix, int i, int j){
        if(path[i][j] !=0) return path[i][j];
    	int max = 1;
    	for(int[] d: dir){
    		int x = i+d[0], y= j+d[1];
            if(x<0 || y<0 || x>=matrix.length || y>=matrix[0].length|| matrix[x][y] <= matrix[i][j]) continue;
    		int next = 1+helper(matrix, x, y);
    		max = Math.max(max, next);
    	}
        path[i][j] = max;
    	return max;
    }
}

/*
同样的，这道题也有BFS的解法。速度很慢有点奇怪。懒得写了，直接贴一个很好地解释
下面再来看一种BFS的解法，需要用queue来辅助遍历，我们还是需要dp数组来减少重复运算。遍历数
组中的每个数字，跟上面的解法一样，把每个遍历到的点都当作BFS遍历的起始点，需要优化的是，如果
当前点的dp值大于0了，说明当前点已经计算过了，我们直接跳过。否则就新建一个queue，然后把当前
点的坐标加进去，再用一个变量cnt，初始化为1，表示当前点为起点的递增长度，然后进入while循环，
然后cnt自增1，这里先自增1没有关系，因为只有当周围有合法的点时候才会用cnt来更新。由于当前结点
周围四个相邻点距当前点距离都一样，所以采用类似二叉树层序遍历的方式，先出当前queue的长度，然后
遍历跟长度相同的次数，取出queue中的首元素，对周围四个点进行遍历，计算出相邻点的坐标后，要进行合
法性检查，横纵坐标不能越界，且相邻点的值要大于当前点的值，并且相邻点点dp值要小于cnt，才有更新的
必要。用cnt来更新dp[x][y]，并用cnt来更新结果res，然后把相邻点排入queue中继续循环即可，

*/
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
	    if(matrix.length == 0) return 0;
	    int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
	    int m = matrix.length, n = matrix[0].length;
	    int[][] dp = new int[m][n];
	    int res =1;
	    for(int i=0; i<m; i++){
	    	for(int j=0; j<n; j++){
	    		if(dp[i][j] >0) continue;
	    		Queue<int[]> q = new LinkedList();
	    		q.add(new int[]{i,j});
	    		int count =1;
	    		while(!q.isEmpty()){
	    			int size = q.size();
	    			count++;
	    			while(size-- >0){
	    				int[] pos = q.remove();
		    			for(int[] d: dir){
		    				int x = d[0]+pos[0], y = d[1]+pos[1];
		    				if(x<0 || y<0 || x>=m || y>=n|| matrix[x][y] <= matrix[pos[0]][pos[1]] || count <= dp[x][y]) continue;
		    				dp[x][y] = count;
		    				res = Math.max(count, res);
		    				q.add(new int[]{x, y});
		    			}
	    			}
	    		}
	    	}
	    }
	    return res;
    }
}


//一开始TLE的代码
class Solution {
	int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int max =0;

        for(int i=0; i<m; i++){
        	for(int j=0; j<n; j++){
        		max = Math.max(max, helper(matrix, new boolean[m][n], i, j, 1, matrix[i][j]));
        	}
        }
        return max;
    }

    public int helper(int[][] matrix, boolean[][] visit, int i, int j, int total, int prev){
    	if(total !=1 && (matrix[i][j] <= prev)) return total-1;
    	int max = total;
        prev = matrix[i][j];
        visit[i][j] = true;

    	for(int[] d: dir){
    		int x = i+d[0], y= j+d[1];
            if(x<0 || y<0 || x>=matrix.length || y>=matrix[0].length|| visit[x][y]) continue;
    		int next = helper(matrix, visit, x, y, total+1, prev);
            visit[x][y] = false;
    		max = Math.max(max, next);

    	}
    	return max;
    }
}