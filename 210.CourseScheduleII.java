/*210. Course Schedule II
Medium
There are a total of n courses you have to take, 
labeled from 0 to n-1.

Some courses may have prerequisites, for example to take 
course 0 you have to first take course 1, which is expressed as 
a pair: [0,1]

Given the total number of courses and a list of prerequisite 
pairs, return the ordering of courses you should take to finish
 all courses.

There may be multiple correct orders, you just need to return 
one of them. If it is impossible to finish all courses, return
 an empty array.
*/

/*解题思路
这道题和207: Course Schedule I一毛一样。拓补学的问题，只需要在207的基础上增加一个
array作为输出的result就可以了 
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] result = new int[numCourses];//就增加了这一行

        while(!q.isEmpty()){
            int node = q.remove();
            result[count++] = node;  //增加了记录遍历路径result[]
            List<Integer> connect = map.get(node);
            for(int i=0; connect!=null && i< connect.size(); i++){
                int out = connect.get(i);
                indegree[out]--;
                if(indegree[out] ==  0) q.add(out);
            }
        }
        
        return count == numCourses ? result : new int[0]; 
    
    }
}