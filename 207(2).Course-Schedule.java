/*207. Course Schedule
Medium 拓补学

There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 
you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
is it possible for you to finish all courses?

Example 1:
Input: 2, [[1,0]] 
Output: true
Explanation: 
There are a total of 2 courses to take. To take course 1 you should 
have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. To take course 
1 you should have finished course 0, and to take course 0 you should
 also have finished course 1. So it is impossible.
*/

/*解题思路: https://www.youtube.com/watch?v=zkTOIVUdW-I
这是一道拓补学的问题。摘抄一段网络上的解释.“第一条就告诉我们了这道题的本质就是
在有向图中检测环。 个人认为图这种数据结构相比于树啊，链表啊什么的要更为复杂一些
，尤其是有向图，很麻烦。第二条提示是在讲如何来表示一个有向图，可以用边来表示，
边是由两个端点组成的，用两个点来表示边。第三第四条提示揭示了此题有两种解法，
DFS 和 BFS 都可以解此题。我们先来看 BFS 的解法，我们定义hashmap
来表示这个有向图，一维数组 indegree 来表示每个顶点的入度。我们开始先根据输入来建立这
个有向图，并将入度数组也初始化好。然后我们定义一个 queue 变量，将所有入度为0的
点放入队列中，然后开始遍历队列，从 graph 里遍历其连接的点，每到达一个新节点，
将其入度减一，如果此时该点入度为0，则放入队列末尾。直到遍历完队列中所有的值，
若此时还有节点的入度不为0，则说明环存在，返回 false，反之则返回 true。”
基本意思就是，用hashmap记录每个课程可以给什么课程提供pre-request。然后indegree记录每个课程
需要几门课程先上完才能上。我们必须先从没有任何pre-request的课程出发，然后将可以把
这门课程当做pre-request的所有课程入度-1。如果为0的话那么这门课可以作为其它课程的
prerequest上了。
代码稍微有点复杂，
*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] pre : prerequisites){
            indegree[pre[0]]++;
            if(!map.containsKey(pre[1])) map.put(pre[1], new ArrayList());
            map.get(pre[1]).add(pre[0]);
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(indegree[i] ==0 )q.add(i);
        }
        
        int count =0;
        while(!q.isEmpty()){
            int node = q.remove();
            count++;
            List<Integer> connect = map.get(node);
            for(int i=0; connect!=null && i< connect.size(); i++){
                int out = connect.get(i);
                indegree[out]--;
                if(indegree[out] ==  0) q.add(out);
            }
        }

        //直接检测所有入度为0的课程和总课程数量是否一致就可以了，或者用上面的for loop
        //检测是否有不能上的课
        return count == numCourses; 
    }
}