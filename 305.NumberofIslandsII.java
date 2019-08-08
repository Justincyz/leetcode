/*305. Number of Islands II
Hard: Union Find, DFS

A 2d grid map of m rows and n columns is initially filled with water. We may 
perform an addLand operation which turns the water at position (row, col) into 
a land. Given a list of positions to operate, count the number of islands after
 each addLand operation. An island is surrounded by water and is formed by 
 connecting adjacent lands horizontally or vertically. You may assume all four 
 edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]

Explanation:
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0

*/

/*解题思路
经典的Union Find, 当然也可以用DFS来解。
网上讲解的很到位:
此题跟经典的UF使用场景有一点点的区别，因为一般的场景中两个个体之间只有两种关系，属于一个群组或
者不属于同一个群组，而这道题里面由于water的存在，就多了一种情况，我们只需要事先检测一下当前位置
是不是岛屿就行了，总之问题不大。一般来说我们的root数组都是使用一维数组，方便一些，那么这里就可
以将二维数组降维为一维的，于是我们需要一个长度为m*n的一维数组来标记各个位置属于哪个岛屿，我们假设
每个位置都是一个单独岛屿，岛屿编号可以用其坐标位置表示，但是我们初始化时将其都赋为-1，这样方便我们
知道哪些位置尚未变成岛屿。然后我们开始遍历陆地数组，将其岛屿编号设置为其坐标位置，然后岛屿计数加1，
我们此时开始遍历其上下左右的位置，遇到越界或者岛屿标号为-1的情况直接跳过，现在知道我们初始化为-1
好处了吧，遇到是water的地方直接跳过。否则我们用findIsland来查找邻居位置的岛屿root，同时也用
findIsland来查找当前点的编号，这一步就是经典的UF算法的操作了，因为当前这两个land是相邻的，
它们是属于一个岛屿，所以其getRoot函数的返回值suppose应该是相等的，但是如果返回值不同，
说明我们需要合并岛屿，将两个返回值建立关联，并将岛屿计数cnt减1。当我们遍历完当前点的所有邻居时，
该合并的都合并完了，将此时的岛屿计数cnt存入结果中，


举一个例子，是使用被comment掉的print()打印出来的结果
m = 5
n = 5
[[0,0],[0,1],[0,2],[0,3],[1,0],[1,3]]

roots[0] = 0   :    
roots[1] = 0   :    roots[0] = 0
roots[2] = 0   :    roots[0] = 0
roots[0] = 0   :    
roots[3] = 0   :    roots[0] = 0
这是每一次遍历之后的值，可以发现最后所有能链接在一起的岛屿都指向了同一个root

*/



class Solution {
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if(m <= 0 || n <= 0) return result;

        int count = 0;                      // number of islands
        int[] roots = new int[m * n];       // one island = one tree
        Arrays.fill(roots, -1);            

        for(int[] p : positions) {
            int root = n * p[0] + p[1];     // assume new point is isolated island

            if (roots[root] != -1) {    // 如果有重复输入的position，为了不重复计算
                    result.add(count);
                    continue;
            }
            roots[root] = root;             // add new island
            count++;

            for(int[] dir : dirs) {
                int x = p[0] + dir[0]; 
                int y = p[1] + dir[1];
                int nb = n * x + y;
                if(x < 0 || x >= m || y < 0 || y >= n || roots[nb] == -1) continue;

                int rootNb = findIsland(roots, nb);
                //System.out.println();
                /*这里还考虑到了，如果两个独立的两个岛之间增加一个，那么应该左右两个岛会被链接
                成为一个岛屿，所以每一次要更新一下root, 使新的root变为原先island的根*/
                if(root != rootNb) {        // if neighbor is in another island
                    roots[root] = rootNb;   // union two islands 
                    root = rootNb;          // current tree root = joined tree root
                    count--;               
                }
            }
            result.add(count);
        }
        return result;
    }

    public int findIsland(int[] roots, int id) {
        //System.out.print("roots["+ id+"]" +" = "+roots[id]+"   :    ");
        while(id != roots[id]){
            id = roots[id];
            //System.out.print("roots["+ id+"]" +" = "+roots[id]);
        } 
        return id;
    }
}
