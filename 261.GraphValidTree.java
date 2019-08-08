/*212. Word Search II
Given n nodes labeled from 0 to n-1 and a list of undirected edges 
(each edge is a pair of nodes), write a function to check whether these 
edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all 
edges are undirected, [0,1] is the same as [1,0] and thus will not appear together 
in edges.

*/

/*解题思路
思路要证明，第一，这个是一个无环图。第二每一个vertex都可以被连接上。也就是说 vertexs的数量是edge-1
第一个做法是做DFS。从vertex X出发，可以到达的每一个节点都标记在array上。比如说 [[0,1], [0,2],
[0,3],[3,1]], 首先把每一个空位填-1。
加入edge[0,1], [1,-1,-1,-1], 表示从0为可以到达1
加入edge[0,2], [1,2,-1,-1], 表示从0位出发，可以到达1，1又可以到达2.所以[0,2也被串连起来了]
加入edge[0,3], [1,2,3,-1], 表示从0出发，一路上可以经过 1,2 到达第三位。此时第三位是 -1. 到目前为止都没问题
加入edge[3,1], 此时从1可以到达最后一个node是3， 3可以到达的最后一个Node也是3.说明从1已经可以到
3了。那么 [3,1]如果再加上的话就会成环
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        if(edges.length != n - 1) return false;
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        
        // perform union find
        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            
            // if two vertices happen to be in the same set
            // then there's a cycle
            if (x == y) return false;
            
            // union
            nums[x] = y;
        }
        
        return true;
    }
    
    int find(int nums[], int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}

/*这个方法也很不错，先把每一个node可以到的直接节点作为这个Node的list,然后从这个节点出发只要
做一次DFS就可以发现有没有环。把每一次经过的节点放到一个HashSet里面。*/
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        // initialize adjacency list.
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        Set<Integer> visited = new HashSet<>();
        visited.add(0);

        // do DFS from vertex 0, after one round DFS, if there is no loop and visited contains all the vertexs,
        // it is a tree.
        boolean res = helper(-1, 0, visited, graph);
        if (!res) return res;
        return visited.size() == n ? true : false;
    }

    public boolean helper(int parent, int vertex, Set<Integer> visited, List<List<Integer>> graph) {
        List<Integer> subs = graph.get(vertex);
        for (int v : subs) {
            // if v is vertex's parent, continue.
            if (v == parent) continue; 
            // if v is not vertex's parent, and v is visited. that presents there is a cycle in this graph.
            if(visited.contains(v)) return false;
            visited.add(v);
            boolean res = helper(vertex, v, visited, graph);
            if (!res) return false;
        }
        return true;
    }
}
