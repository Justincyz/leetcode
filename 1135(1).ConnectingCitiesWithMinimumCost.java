/*1135. Connecting Cities With Minimum Cost
Medium: 
There are N cities numbered from 1 to N.

You are given connections, where each connections[i] = [city1, city2, cost] 
represents the cost to connect city1 and city2 together.  (A connection is 
bidirectional: connecting city1 and city2 is the same as connecting city2 
and city1.)

Return the minimum cost so that for every pair of cities, there exists a 
path of connections (possibly of length 1) that connects those two cities 
together.  The cost is the sum of the connection costs used. If the task 
is impossible, return -1.

Example 1
        1
      /  \
     6    5
    /      \
   3 ---1---2
Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
Output: 6
Explanation: 
Choosing any 2 edges will connect all cities so we choose the minimum 2.

Example 2
1 ----3-----2

3 ----4 -----4
Input: N = 4, connections = [[1,2,3],[3,4,4]]
Output: -1
Explanation: 
There is no way to connect all cities even if all edges are used.

*/

/*解题思路, Union Find
抽象一下题目的意思，给定1--N 总共N个点，两点之间有一个weight, 问如何将所有的点都连在一起
并且总质量最小。假如这样的连接方法不存在(如例子2)则返回-1.这道题目是一道经典的Union Find的题目。
首先题目给定了N个点和这些点之间的链接方法。根据以往的经验我们知道，这个肯定是要构建一个长度
为N+1的array, 用位指针的办法使得每个点都指向这个cluster的一个root。慢慢的扩展成为一个MST。
那么我们先定义一个最小堆。以便保证每次取出来的边都是weight最小的那条。每次对于两个顶点，我们
检查他们是否会到达同一个root,如果是的话，则继续取下一条边。否则就把这条边加入sum中。同时让
source指向的root指向dst指向的root,用来连接这两个cluster。最后要检查是否我们遍历了N-1
条边(连接N个点)，如果不是的话就返回-1.

*/

class Solution {
    public int minimumCost(int N, int[][] connections) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[2]-b[2]);
        int sum =0;
        for(int[] conn: connections) pq.add(conn);
        
        int[] path = new int[N+1];
        for(int i=1; i<=N; i++) path[i] = i;
        
        while(!pq.isEmpty() ){
            int[] conn = pq.poll();
            int src = conn[0], dst = conn[1];   
            if(helper(path, src, dst)) continue;
            else{
            	sum+=conn[2];
            	N--;
            } 
        }  
        
        if(N != 1) return -1;
        
        return sum;
    }
    
    public boolean helper(int[] path, int src, int dst){

        while(src != path[src]){
            path[src] = path[path[src]];
            src = path[src];
        }
    
        while(dst != path[dst]){
            path[dst] = path[path[dst]];
            dst = path[dst];
        }
         
        if(src == dst) return true;
        
        path[src] = dst;
        return false;
    }
}