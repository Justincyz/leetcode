/*410. Split Array Largest Sum
Medium

For an undirected graph with tree characteristics, we can choose 
any node as the root. The result graph is then a rooted tree. 
Among all possible rooted trees, those with minimum height are 
called minimum height trees (MHTs). Given such a graph, write a 
function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You
 will be given the number n and a list of undirected edges (each 
 edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since 
all edges are undirected, [0, 1] is the same as [1, 0] and thus 
will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
*/

/*解题思路
这道题还是用Graph来解。一开始我的思路是用DFS先找到最长的一个路径，那么
最短的root一定存在在这一条路径的中间一个或者中间两个节点上。为什么呢，因为
直径最长，那么说明半径也还是最长的。那么就说明没有另外一条路径可以比这条半径
还长，所以这个就是最短的路径就是minimum height。找到最长的路径的长度和起始点
不难，但是要找到这个路径就有点麻烦。遂放弃。网上大家推崇的另一种方法就是剪枝法。
与之类似的是Course chedule I/II这两题。我们需要建立一个hashmap，
其中gap.get(i)是hashset(之所以可以用hashset是因为每一个node都是唯一的)，
保存了i节点可以到达的所有节点。我们开始将所有只有一个连接边的节点(叶节点)都存
入到一个队列queue中，然后我们遍历每一个叶节点，通过图来找到和其相连的节点，
并且在其相连节点的hashet中将该叶节点删去，如果删完后此节点也也变成了一个叶节点，
那就加入队列中，再下一轮删除。那么我们删到什么时候呢，当节点数小于等于2时候停止，
此时剩下的一个或两个节点就是我们要求的最小高度树的根节点

*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {       
        List<Integer> list = new ArrayList<>();
        if(n==1){
           list.add(0);
           return list;
        } 
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Queue<Integer> q = new LinkedList();
        for(int[] edge : edges){
            if(!map.containsKey(edge[0])) map.put(edge[0], new HashSet());
            if(!map.containsKey(edge[1])) map.put(edge[1], new HashSet());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        for(int i=0; i<n;i++){
            if(map.get(i).size() ==1) q.add(i);
        }
        
        while(n>2){
            int size = q.size();
            n-=size;//为了保证删除的方式是一圈一圈的
            for(int j=size; j>0; j--){
                int node = q.remove();
                Set<Integer> set= map.get(node);
                for(int neighbor :set){
                    map.get(neighbor).remove(node);
                    if(map.get(neighbor).size() == 1){
                        q.add(neighbor);  
                    } 
                }
            }
        }
        for(int i: q) list.add(i);
        
        return list;
    }
}