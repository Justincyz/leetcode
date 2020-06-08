/*1466. Reorder Routes to Make All Paths Lead to the City Zero
Medium
链接:  https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/


There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It’s guaranteed that each city can reach the city 0 after reorder.

Example 1:
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0


Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).

Example 3:
Input: n = 3, connections = [[1,0],[2,0]]
Output: 0


Constraints:
2 <= n <= 5 * 10^4
connections.length == n-1
connections[i].length == 2
0 <= connections[i][0], connections[i][1] <= n-1
connections[i][0] != connections[i][1]
*/



/*解题思路 DP
题目告诉我们有n个城市，每两个城市之间只有一条道路相连，并且这一条道路是一条有方向的道路。现在我们要让所有的城市都可以到达城市0 (到达指的是可以经过0个或者多个有向道路到达)。问，我们需要将多少条城市道路的方向调转过来才可以达到这样的目的。题目当中可以保证结果可以出现。

因为题目中说，我们保证两个城市之间只有一条路可以到达(这一条路可以经过多个城市)。也就是说这里其实是一个有向无环的树。我们要做的就是从这棵树的root (也就是城市0)出发，经过所有的子节点。如果在这个过程当中父节点到子节点的路径和给出的connections路径相反的话，那么我们就需要转换一次方向使得路径是从父节点往子节点方向去的。每一次转换我们都将转换的总次数加一，这样我们最后就会构成一个从root 为0的树，方向为父节点指向子节点。因为我们最后想要的结果是所有的子节点指向父节点的一棵树，所以我们最后还要用edge的总和，也就是n-1, 减去我们累加出来的结果。才是将所有节点指向root的需要转换的次数。

为了构成这棵树，我们首先构建一个无向图。这个无向图我们使用hashmap来构造。两个点之间互相有所连接。同时为了在遍历这个无向图的时候快速定位这个方向和connections里面的方向是不是对的。我们再使用一个hashset，里面存上城市原道路的方向 。这样在遍历我们无向图的时候就可以快速的定位了。同时为了避免形成环，我们再使用一个长度为n的数组记录我们已经遍历过的节点。

做完这些之后我们从0节点出发。每一次我们都从map当中获取从source节点可以到达的所有children节点。如果这个节点已经被visit过了，则跳过。否则如果当前我们遍历的两个节点的方向没有出现在direction hashset中，则增加total的值。

最后是返回n-1-total的值

TIME & SPACE: O(n)
*/

class Solution {
    int total =0;
    public int minReorder(int n, int[][] connections) {
        //注意不能添加新的edge
        Map<Integer, List<Integer>>  map = new HashMap<>();
        Set<String> direction = new HashSet<>();
        
        for(int[] connection : connections){
            int a = connection[0], b = connection[1];
            direction.add(a+"-"+b);

            if(!map.containsKey(b)) map.put(b, new ArrayList());
            if(!map.containsKey(a)) map.put(a, new ArrayList());
            map.get(a).add(b);
            map.get(b).add(a);
        }
        
        dfs(0, map, direction, new int[n]);
        return n-1-total;
    }
    
    public void dfs(int source, Map<Integer, List<Integer>> map, Set<String> direction, int[] visited){
        if(visited[source]==1) return;
        visited[source] = 1;
        
        for(int child : map.get(source)){
            if(visited[child]==1) continue;
            if(!direction.contains(source+"-"+child)){
                total++;
            }
            dfs(child, map, direction, visited);
        }
    }
}