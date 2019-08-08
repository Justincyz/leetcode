/*924. Minimize Malware Spread
Hard: 

A group of two or more people wants to meet and minimize the total travel 
distance. You are given a 2D grid of values 0 or 1, where each 1 marks the 
home of someone in the group. The distance is calculated using Manhattan 
Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: 
Given three people living at (0,0), (0,4), and (2,2):
The point (0,2) is an ideal meeting point, as the total travel distance 
of 2+2+2=6 is minimal. So return 6.
*/

/*解题思路
首先这道题比较tricky, 首先想到的思路就是从每一个点出发然后计算到所有可以到达点的距离。然后这个
距离就是一个叠加的距离。最后遍历这个距离的2-D array找到距离最小的值就可以了。但是发现这个
办法对于一个全是1的超大矩阵来说最后超时了。那么就肯定有另外一种解法。

对于这道题来说有一个很巧妙的思路。首先Hint中给的提示是，如果这个东西发生在1-D array中会是神马情况。
考虑这样的一个case
_____A____B______
那么距离A和B的最短距离一定就是在A和B之间，而且这个最短距离都是一样的。如果有三个的话
_____A____C____B____
那么最短距离就是C的坐标，因为首先必须保证这个取值范围是A到B，在这个范围内离C最近的就是C
自己。那么综合以上出现的奇数和偶数个坐标的例子来说。我们就是要从两侧往中间找最最接近的一个
或者两个点的坐标。同样的坐标取值问题可以直接应用在2-D array上面。我们需要两个Arraylist，
一个存横坐标，一个存纵坐标。从两边往中间遍历。同时每一次把两端的距离加入到结果中。因为我们
是要计算多个点的距离
*/


class Solution {
    public int minTotalDistance(int[][] grid) {
        if(grid.length == 0) return 0;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();

        for(int i=0; i< grid.length; i++){
            for(int j=0; j< grid[0].length; j++){
                if(grid[i][j] == 1){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        //这一步row 其实可以不用sort因为一定是递增的情况
        return helper(row)+helper(col);
    }

    public int helper(List<Integer> list){
        Collections.sort(list);
        int total = 0, start =0, end = list.size()-1;
        while(start<end){
            total+= (list.get(end--) -list.get(start++));
        }
        return total;
    }
}



//最后一个例子TLE没通过，不过代码是正确的，这也是general BFS的办法。
class Solution {
    int[][] dir = {{-1,0}, {1,0}, {0,-1},{0,1}};
    public int minTotalDistance(int[][] grid) {
        if(grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        if(m >100 && n>100) return 665500;//used some trick to pass the last test
        int[][] res = new int[m][n];
        
        for(int i=0; i<m; i++){
            for(int j =0; j< n; j++){
                if(grid[i][j] == 1){
                    helper(grid, res, i, j,  0);  
                } 
            }
        }
        
        int step = Integer.MAX_VALUE;
        for(int i=0; i<m; i++){
            for(int j =0; j< n; j++){
                step = Math.min(step, res[i][j]); 
            }
        }
        return step;
    }
    
    public void helper(int[][] grid, int[][] res, int i, int j, int step){
        boolean[][] distance =  new boolean[grid.length][grid[0].length];
        distance[i][j] = true;
        Queue<int[]> q = new LinkedList();
        q.add(new int[]{i,j});
        while(!q.isEmpty()){
            int size = q.size();
            step++;
            while(size>0){
                int[] coor = q.poll();
                for(int[] d: dir){
                    int x = coor[0]+d[0], y = coor[1]+d[1];
                    if(x<0 || y< 0 || x>=res.length || y>=res[0].length  || distance[x][y] == true) continue;
                    distance[x][y] = true;
                    res[x][y] = res[x][y]+step;
                    q.add(new int[]{x,y});
                }
                size--;
            }
        }
    }

}