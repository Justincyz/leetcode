/*323. Number of Connected Components in an Undirected Graph
Medium

Given n nodes labeled from 0 to n - 1 and a list of undirected edges 
(each edge is a pair of nodes), write a function to find the number of 
connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1

Note:
You can assume that no duplicate edges will appear in edges. Since all 
edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear 
together in edges.


*/

/*解题思路， 两种方法，第一种比较容易理解
(1)建立一个Boolean array, 给每个节点都有个flag标记其是否被访问过。从每一个未访问过的节点开始进行DFS
搜索。将所有可以被连通的节点标记为true。在遍历每个节点的时候，如果碰到 visit[i]=false，代表这肯定是
一个新的连通区域。以此类推直至所有的节点都被访问过了，那么此时我们也就求出来了连通区域的个数。
*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        boolean[] visit = new boolean[n];
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<n; i++) list.add(new ArrayList());

        for(int[] edge: edges){
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }   

        int count = 0;
        for(int i=0; i<n; i++){
            if(!visit[i]){
                helper(list, i, visit);
                count++;
            }
        }
        return count;
    }  

    public void helper(List<List<Integer>> list, int index, boolean[] visit){
        if(visit[index]) return;
        visit[index] = true;
        List<Integer> edge = list.get(index); 
        for(int i=0; i< edge.size(); i++){
            helper(list, edge.get(i), visit); //这里是继续call 自己，做的是dfs
        }
    }
}
/*
2) 首先假设每个vertex都是独立的，对应n个vetex 我们创建一个 roots[]。如果 i 和 roots[i]
是一样的话，那么说明这个区间是独立的。否则说明这个区间已经和某一个区间连通在了一起。我们不断遍历
edges[][]中的edge, 我们可以确定每一条edge的连接的两个端点一定是属于一类的。如果当前点对应的值不是
自己的值的话，那么说明已经这个vertex已经被囊括在某一个vertex可以reach的点之内了。
*/
class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for(int i = 0; i < n; i++) roots[i] = i; 

        for(int[] e : edges) {
            int root1 = find(roots, e[0]);
            int root2 = find(roots, e[1]);

            /*本来这两个应该属于一个group，但是最后可以reach到的值如果不一样的话，那么说明新增加的
            egde还没有和原来的连通起来。我们使之连通，那么就可以减少一个group。*/
            if(root1 != root2) {  
                roots[root1] = root2;  // union
                n--;
            }
        }
        return n;
    }

    public int find(int[] roots, int id) {
        while(roots[id] != id) {
            roots[id] = roots[roots[id]];  // optional: path compression
            id = roots[id];
        }
        return id;
    }
}

/*
5
[[0,1],[0,2],[1,2],[2,3],[2,4]]

[0,1,2,3,4] [0,1]
[1,1,2,3,4]
4
[1,1,2,3,4] [0,2]
[1,2,2,3,4]
3
[1,2,2,3,4] [1,2]
3
[1,2,2,3,4] [2,3]
[1,2,3,3,4]
2
*/
