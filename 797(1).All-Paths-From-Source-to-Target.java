/*797. All Paths From Source to Target
Medium
链接: https://leetcode.com/problems/all-paths-from-source-to-target/
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.


*/

/*解题思路
dfs+backtrack来做就好了

*/

//用test case检查了，不会有环的情况出现

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList();
        path.add(0);
        helper(0, graph, res, path);
        return res;
    }
    
    public void helper(int start, int[][] graph, List<List<Integer>> res, List<Integer> path){
        if(start == graph.length-1){
            res.add(new ArrayList<Integer>(path));
            return;
        } 
        
        for(int pos: graph[start]){
            path.add(pos);
            helper(pos, graph, res, path);
            path.remove(path.size()-1);
        }

    }
}